package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.utils.PasswordHashingService;
import ma.emsi.expensebackend.service.impl.UserFacadeImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    public final UserFacadeImpl userFacadeImpl;

    private final PasswordHashingService passwordHashingService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserFacadeImpl userFacadeImpl, PasswordHashingService passwordHashingService) {
        this.userFacadeImpl = userFacadeImpl;
        this.passwordHashingService = passwordHashingService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            String hashedPassword = passwordHashingService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            User savedUser = userFacadeImpl.saveUser(user);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userFacadeImpl.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        if (!userId.equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User updatedUser = userFacadeImpl.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAll(){
       return userFacadeImpl.getAll();
    }
    @GetMapping("/secure-resource")
    public ResponseEntity<String> secureResource(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non authentifié");
        } else {
            return ResponseEntity.ok("Ressource sécurisée accessible");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user, HttpSession session) {
        Optional<User> optionalUser = userFacadeImpl.getUserByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            logger.info("User found: {}", user.getUsername());
            User existingUser = optionalUser.get();
            try {
                if (passwordHashingService.verifyPassword(user.getPassword(), existingUser.getPassword())) {
                    session.setAttribute("user", existingUser);
                    logger.info("session: {}", session.getAttribute("user"));
                    Map<String, Object> responseData = new HashMap<>();
                    responseData.put("user", existingUser);
                    responseData.put("message", "Connexion réussie");
                    return ResponseEntity.ok(responseData);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
                }
            } catch (NoSuchAlgorithmException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur de hachage du mot de passe");
            }
        } else {
            logger.info("No user found with username: {}", user.getUsername());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Déconnexion réussie");
    }

}
