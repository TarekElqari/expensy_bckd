package ma.emsi.expensebackend.controller;

import java.io.IOException;
import java.util.List;

import ma.emsi.expensebackend.export.ExcelExporter;
import ma.emsi.expensebackend.service.impl.DepenseFacadeImpl;
import ma.emsi.expensebackend.service.impl.DepotFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.entity.Depot;


@RestController
@RequestMapping("api/v1")
public class ExportController {

    @Autowired
    private DepenseFacadeImpl depenseFacadeImpl;

    @Autowired
    private DepotFacadeImpl depotFacadeImpl;

    @GetMapping("/export")
    public ResponseEntity<?> exportToExcel() {
        List<Depense> depenses = depenseFacadeImpl.getAllDepenses();
        List<Depot> depots = depotFacadeImpl.getAllDepots();

        ExcelExporter excelExporter = new ExcelExporter();
        try {
            excelExporter.exportToExcel(depenses, depots);
            return ResponseEntity.ok("Excel file exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to export Excel file.");
        }
    }
}
