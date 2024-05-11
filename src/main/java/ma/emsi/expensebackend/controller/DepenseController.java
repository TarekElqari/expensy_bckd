package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.entity.Depot;
import ma.emsi.expensebackend.service.impl.DepenseFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/depense")
public class DepenseController {
    @Autowired
    public DepenseFacadeImpl depenseFacadeImpl;

    @PostMapping("/{userId}")
    public ResponseEntity<Depense> createDepense(@RequestBody Depense depense, @PathVariable Long userId) {
        Depense savedDepense = depenseFacadeImpl.ajouterDepense(depense, userId);
        return new ResponseEntity<>(savedDepense, HttpStatus.CREATED);
    }

    @PostMapping("/create-list/{userId}")
    public ResponseEntity<Depense> createListDepot(@RequestBody List<Depense> depense, @PathVariable Long userId) {
        Depense savedDepense = null;
        for (Depense dps : depense) {
            savedDepense = depenseFacadeImpl.ajouterDepense(dps, userId);
        }
        return new ResponseEntity<>(savedDepense, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepense(@PathVariable("id") Long depenseId) {
        depenseFacadeImpl.deleteDepense(depenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Depense> updateDepense(@PathVariable("id") Long depenseId, @RequestBody Depense depense) {
        if (!depenseId.equals(depense.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Depense updatedDepense = depenseFacadeImpl.updateDepense(depense);
        return new ResponseEntity<>(updatedDepense, HttpStatus.OK);
    }

    @GetMapping("/depenses")
    public List<Depense> getAllDepenses(){
       return depenseFacadeImpl.getAllDepenses();
    }
    @PostMapping("/ajouter/{userId}")
    public ResponseEntity<Depense> ajouterDepense(@RequestBody Depense depense, Long userId) {
        Depense nouvelleDepense = depenseFacadeImpl.ajouterDepense(depense, userId);
        return ResponseEntity.ok().body(nouvelleDepense);
    }
    
}
