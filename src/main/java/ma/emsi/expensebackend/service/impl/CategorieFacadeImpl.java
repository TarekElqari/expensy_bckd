package ma.emsi.expensebackend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Categorie;
import ma.emsi.expensebackend.repository.CategorieRepository;
import ma.emsi.expensebackend.service.facade.CategorieFacade;

@Service
public class CategorieFacadeImpl implements CategorieFacade {

    @Autowired
    private final CategorieRepository categorieRepository;

    public CategorieFacadeImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Long categorieId) {
        categorieRepository.deleteById(categorieId);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
