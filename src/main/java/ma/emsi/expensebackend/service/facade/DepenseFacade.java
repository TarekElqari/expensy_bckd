package ma.emsi.expensebackend.service.facade;

import java.util.List;
import ma.emsi.expensebackend.entity.Depense;

public interface DepenseFacade {
    Depense saveDepense(Depense depense);
    void deleteDepense(Long depenseId);
    Depense updateDepense(Depense depense);
    List<Depense> getAllDepenses();
	Depense ajouterDepense(Depense depense, Long id);
	List<Depense> getDepensesByUserId(Long userId);
}