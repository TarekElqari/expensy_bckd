package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Categorie;
import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.service.impl.CategorieFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorie")
public class CategorieController {
    @Autowired
    public CategorieFacadeImpl categorieFacadeImpl;

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieFacadeImpl.saveCategorie(categorie);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }
    @PostMapping("/create-list")
    public ResponseEntity<Categorie> createListCategorie(@RequestBody List<Categorie> categorie) {
        Categorie savedCategorie = null;
        for (Categorie ctg : categorie) {
            savedCategorie = categorieFacadeImpl.saveCategorie(ctg);
        }
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable("id") Long categorieId) {
        categorieFacadeImpl.deleteCategorie(categorieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") Long categorieId, @RequestBody Categorie categorie) {
        if (!categorieId.equals(categorie.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Categorie updatedCategorie = categorieFacadeImpl.updateCategorie(categorie);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategories(){
       return categorieFacadeImpl.getAllCategories();
    }
}
