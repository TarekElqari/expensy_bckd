package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Depot;
import ma.emsi.expensebackend.service.impl.DepotFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/depot")
public class DepotController {
    @Autowired
    public DepotFacadeImpl depotFacadeImpl;

    @PostMapping
    public ResponseEntity<Depot> createDepot(@RequestBody Depot depot) {
        Depot savedDepot = depotFacadeImpl.saveDepot(depot);
        return new ResponseEntity<>(savedDepot, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable("id") Long depotId) {
        depotFacadeImpl.deleteDepot(depotId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Depot> updateDepot(@PathVariable("id") Long depotId, @RequestBody Depot depot) {
        if (!depotId.equals(depot.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Depot updatedDepot = depotFacadeImpl.updateDepot(depot);
        return new ResponseEntity<>(updatedDepot, HttpStatus.OK);
    }

    @GetMapping("/depots")
    public List<Depot> getAllDepots(){
       return depotFacadeImpl.getAllDepots();
    }
}
