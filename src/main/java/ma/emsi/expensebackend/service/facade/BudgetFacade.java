package ma.emsi.expensebackend.service.facade;

import java.util.List;
import java.util.Optional;

import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.entity.User;

public interface BudgetFacade {
    void deleteBudget(Long budgetId);
    Budget updateBudget(Budget budget);
    List<Budget> getAllBudgets(); 
    Budget ajouterBudget(Budget budget);

    Budget findFirstByOrderById();
	Budget findBudgetByUserId(long id);
    
}