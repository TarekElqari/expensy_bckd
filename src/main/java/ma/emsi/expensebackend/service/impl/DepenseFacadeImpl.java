package ma.emsi.expensebackend.service.impl;

import java.time.LocalDate;
import java.util.List;

import ma.emsi.expensebackend.entity.Budget;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.repository.DepenseRepository;
import ma.emsi.expensebackend.service.facade.DepenseFacade;

@Service
public class DepenseFacadeImpl implements DepenseFacade {

    private final DepenseRepository depenseRepository;

    private final BudgetFacadeImpl budgetFacadeImpl;

    public DepenseFacadeImpl(DepenseRepository depenseRepository, BudgetFacadeImpl budgetFacadeImpl) {
        this.depenseRepository = depenseRepository;
        this.budgetFacadeImpl = budgetFacadeImpl;
    }

    @Override
    public Depense saveDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public void deleteDepense(Long depenseId) {
        depenseRepository.deleteById(depenseId);
    }

    @Override
    public Depense updateDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }
    
    @Override
    public Depense ajouterDepense(Depense depense, Long userId) {
        if (depense.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant de la dépense doit être supérieur à zéro.");
        }
        LocalDate dateDepense = depense.getDateDepense();
        LocalDate currentDate = LocalDate.now();
        if (dateDepense.isAfter(currentDate)) {
            throw new IllegalArgumentException("La date de la dépense ne peut pas être dans le futur.");
        }
        Budget budget = budgetFacadeImpl.findBudgetByUserId(userId);
        if (budget.getMontant() < depense.getMontant()) {
            throw new IllegalArgumentException("Le montant de la dépense dépasse le budget disponible.");
        }
        budget.setMontant(budget.getMontant() - depense.getMontant());
        budgetFacadeImpl.updateBudget(budget);
        return depenseRepository.save(depense);
    }

}
