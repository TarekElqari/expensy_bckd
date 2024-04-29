package ma.emsi.expensebackend.service.facade;

import java.util.List;

import ma.emsi.expensebackend.entity.Budget;

public interface BudgetFacade {
    Budget saveBudget(Budget budget);
    void deleteBudget(Long budgetId);
    Budget updateBudget(Budget budget);
    List<Budget> getAllBudgets(); 
}