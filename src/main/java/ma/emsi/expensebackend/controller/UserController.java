package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.service.impl.PasswordHashingService;
import ma.emsi.expensebackend.service.impl.UserFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    public UserFacadeImpl userFacadeImpl;

    @Autowired
    private PasswordHashingService passwordHashingService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            // Hashage du mot de passe avant de l'enregistrer
            String hashedPassword = passwordHashingService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            
            // Enregistrer l'utilisateur avec le mot de passe hashé
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
            // Accéder à la ressource sécurisée
            return ResponseEntity.ok("Ressource sécurisée accessible");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpSession session) {
        Optional<User> optionalUser = userFacadeImpl.getUserByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            try {
                if (passwordHashingService.verifyPassword(user.getPassword(), existingUser.getPassword())) {
                    // Maintenant que le mot de passe est vérifié, récupérez l'ID de l'utilisateur
                    Long userId = existingUser.getId();
                    if (userId != null) {
                        // Définir l'ID de l'utilisateur dans la session
                        session.setAttribute("userId", userId);
                        return new ResponseEntity<>("Connexion réussie", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("ID de l'utilisateur non trouvé", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    return new ResponseEntity<>("Mot de passe incorrect", HttpStatus.UNAUTHORIZED);
                }
            } catch (NoSuchAlgorithmException e) {
                return new ResponseEntity<>("Erreur de hachage du mot de passe", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.info("No user found with username: " + user.getUsername());
            return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        // Invalide la session
        return ResponseEntity.ok("Déconnexion réussie");
    }

}
