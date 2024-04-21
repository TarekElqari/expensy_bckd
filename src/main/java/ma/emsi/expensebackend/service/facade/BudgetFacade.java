package ma.emsi.expensebackend.service.facade;

import java.util.List;
import java.util.Optional;

import ma.emsi.expensebackend.entity.Budget;

public interface BudgetFacade {

	List<Budget> getAllBudgets();

	Optional<Budget> getBudgetById(Long id);

	Budget saveBudget(Budget budget);

	void deleteBudget(Long id);

}
