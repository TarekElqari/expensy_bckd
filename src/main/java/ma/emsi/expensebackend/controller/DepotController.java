package ma.emsi.expensebackend.controller;

import ma.emsi.expensebackend.entity.Depot;
import ma.emsi.expensebackend.service.impl.DepotFacadeImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/depot")
public class DepotController {
    public final DepotFacadeImpl depotFacadeImpl;

    public DepotController(DepotFacadeImpl depotFacadeImpl) {
        this.depotFacadeImpl = depotFacadeImpl;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Depot> createDepot(@RequestBody Depot depot, @PathVariable Long userId) {
        Depot savedDepot = depotFacadeImpl.saveDepot(depot, userId);
        return new ResponseEntity<>(savedDepot, HttpStatus.CREATED);
    }

    @PostMapping("/create-list/{userId}")
    public ResponseEntity<Depot> createListDepot(@RequestBody List<Depot> depot, @PathVariable Long userId) {
        Depot savedDepot = null;
        for (Depot dp : depot) {
            savedDepot = depotFacadeImpl.saveDepot(dp, userId);
        }
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