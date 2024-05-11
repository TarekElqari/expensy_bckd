package ma.emsi.expensebackend.service.facade;

import java.util.List;
import java.util.Optional;

import ma.emsi.expensebackend.entity.Categorie;

public interface CategorieFacade {
    Categorie saveCategorie(Categorie categorie);
    void deleteCategorie(Long categorieId);
    Categorie updateCategorie(Categorie categorie);
    List<Categorie> getAllCategories();
    Optional<Categorie> findById(Long id);
}