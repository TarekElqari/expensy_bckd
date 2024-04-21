package ma.emsi.expensebackend.service.impl;


import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.service.facade.BudgetFacade;
import ma.emsi.expensebackend.repository.BudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BudgetFacadeImpl implements BudgetFacade {

    private final BudgetRepository budgetRepository;

    public BudgetFacadeImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
