package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.service.impl.BudgetFacadeImpl;
import ma.emsi.expensebackend.service.impl.UserFacadeImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BudgetFacadeImpl.class);

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
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
    
    @GetMapping("/getBudgetByUser/{userId}")
    public ResponseEntity<Budget> getBudgetByUser(@PathVariable("userId") Long userId) {
        Budget existingBudget = budgetFacadeImpl.findBudgetByUserId(userId);
        if (existingBudget != null) {
            return ResponseEntity.ok(existingBudget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
    

}
