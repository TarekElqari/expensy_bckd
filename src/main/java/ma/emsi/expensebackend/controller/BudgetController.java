package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.service.facade.BudgetFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetFacade budgetFacade;

    public BudgetController(BudgetFacade budgetFacade) {
        this.budgetFacade = budgetFacade;
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetFacade.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetFacade.getBudgetById(id);
        return budget.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget newBudget = budgetFacade.saveBudget(budget);
        return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        Optional<Budget> existingBudget = budgetFacade.getBudgetById(id);
        if (existingBudget.isPresent()) {
            Budget updatedBudget = budgetFacade.saveBudget(budget);
            return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        Optional<Budget> existingBudget = budgetFacade.getBudgetById(id);
        if (existingBudget.isPresent()) {
            budgetFacade.deleteBudget(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
