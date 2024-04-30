package ma.emsi.expensebackend.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Budget;
import ma.emsi.expensebackend.repository.BudgetRepository;
import ma.emsi.expensebackend.service.facade.BudgetFacade;

@Service
public class BudgetFacadeImpl implements BudgetFacade {

    @Autowired
    private final BudgetRepository budgetRepository;

    public BudgetFacadeImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Long budgetId) {
        budgetRepository.deleteById(budgetId);
    }

    @Override
    public Budget updateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    @Override
    public Budget ajouterBudget(Budget budget) {
        // Vérifier si le montant du budget est valide (supérieur à zéro)
        if (budget.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant du budget doit être supérieur à zéro.");
        }

        // Vérifier si la date de dépôt est valide (par exemple, ne pas être dans le passé)
        LocalDate dateDepot = budget.getDateDepot();
        LocalDate currentDate = LocalDate.now();
        if (dateDepot.isBefore(currentDate)) {
            throw new IllegalArgumentException("La date de dépôt du budget doit être dans le futur.");
        }

        // Vous pouvez ajouter d'autres vérifications ou calculs ici

        // Enregistrer le budget dans la base de données
        return budgetRepository.save(budget);
    }
    
}
