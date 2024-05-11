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

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetFacadeImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
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
    public Budget ajouterBudget(Budget budget) { //save
        // Vérifier si le montant du budget est valide (supérieur à zéro)
        if (budget.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant du budget doit être supérieur à zéro.");
        }

        // Initialiser la date de dépôt à la date actuelle si elle n'est pas définie
        if (budget.getDateDepot() == null) {
            budget.setDateDepot(LocalDate.now());
        }

        // Vérifier si la date de dépôt est valide (par exemple, ne pas être dans le passé)
        LocalDate dateDepot = budget.getDateDepot();
        if (dateDepot.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de dépôt du budget doit être dans le futur.");
        }
        
        return budgetRepository.save(budget);
    }


    @Override
    public Budget findFirstByOrderById() {
        return budgetRepository.findFirstByOrderById();
    }
    
    @Override
    public Budget findBudgetByUserId(long id) {
    	List<Budget> budgets = budgetRepository.findAll();
    	for (Budget budget : budgets) {
			if(budget.getUser().getId() == id) {
				return budget;
			}
		}
    	return null;
    }
    
}
