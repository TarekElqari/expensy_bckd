package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.service.impl.BudgetFacadeImpl;
import ma.emsi.expensebackend.service.impl.UserFacadeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    @Autowired
    public BudgetFacadeImpl budgetFacadeImpl;
    
    @Autowired
    private UserFacadeImpl userFacadeImpl;


    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget, HttpSession session) {
        // Récupérer l'ID de l'utilisateur à partir de la session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Récupérer l'utilisateur correspondant à l'ID
        Optional<User> userOptional = userFacadeImpl.getUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        User user = userOptional.get(); // Extraire l'utilisateur de l'Optional

        // Affecter l'utilisateur au budget
        budget.setUser(user);

        // Initialiser la date de dépôt à la date actuelle si elle n'est pas définie
        if (budget.getDateDepot() == null) {
            budget.setDateDepot(LocalDate.now());
        }

        // Enregistrer le budget
        Budget savedBudget = budgetFacadeImpl.ajouterBudget(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable("id") Long budgetId) {
        budgetFacadeImpl.deleteBudget(budgetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable("id") Long budgetId, @RequestBody Budget budget) {
        if (!budgetId.equals(budget.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Budget updatedBudget = budgetFacadeImpl.updateBudget(budget);
        return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
    }

    @GetMapping("/budgets")
    public List<Budget> getAllBudgets(){
       return budgetFacadeImpl.getAllBudgets();
    }
    @PostMapping("/ajouter")
    public ResponseEntity<Budget> ajouterBudget(@RequestBody Budget budget) {
        // Ajouter le budget à la base de données
        Budget nouveauBudget = budgetFacadeImpl.ajouterBudget(budget);
        return ResponseEntity.ok().body(nouveauBudget);
    }

    @GetMapping("/getFirstByOrder")
    public Budget getFirstByOrderById(){
        return budgetFacadeImpl.findFirstByOrderById();
    }

}
