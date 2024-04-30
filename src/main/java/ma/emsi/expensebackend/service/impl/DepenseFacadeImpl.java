package ma.emsi.expensebackend.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.repository.DepenseRepository;
import ma.emsi.expensebackend.service.facade.DepenseFacade;

@Service
public class DepenseFacadeImpl implements DepenseFacade {

    @Autowired
    private final DepenseRepository depenseRepository;

    public DepenseFacadeImpl(DepenseRepository depenseRepository) {
        this.depenseRepository = depenseRepository;
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
    public Depense ajouterDepense(Depense depense) {
        // Vérifier si le montant de la dépense est valide (supérieur à zéro)
        if (depense.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant de la dépense doit être supérieur à zéro.");
        }

        LocalDate dateDepense = depense.getDateDepense();
        LocalDate currentDate = LocalDate.now();
        if (dateDepense.isAfter(currentDate)) {
            throw new IllegalArgumentException("La date de la dépense ne peut pas être dans le futur.");
        }

        // Vous pouvez ajouter d'autres vérifications ou calculs ici

        // Enregistrer la dépense dans la base de données
        return depenseRepository.save(depense);
    }

}
