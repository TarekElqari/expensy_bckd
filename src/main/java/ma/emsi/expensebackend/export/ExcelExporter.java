package ma.emsi.expensebackend.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.entity.Depot;

public class ExcelExporter {

    private static final String EXPORT_DIRECTORY = "C:\\expensy\\exports\\";

    public void exportToExcel(List<Depense> depenses, List<Depot> depots) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            createDepenseSheet(workbook, depenses);
            createDepotSheet(workbook, depots);

            File exportDir = new File(EXPORT_DIRECTORY);
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            String filePath = EXPORT_DIRECTORY + "expenses.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }
    }

    private void createDepenseSheet(Workbook workbook, List<Depense> depenses) {
        Sheet sheet = workbook.createSheet("Depense");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nom");
        headerRow.createCell(2).setCellValue("Date Depense");
        headerRow.createCell(3).setCellValue("Description");
        headerRow.createCell(4).setCellValue("Montant");
        headerRow.createCell(5).setCellValue("Categorie");

        // Populate data rows
        int rowNum = 1;
        for (Depense depense : depenses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(depense.getId());
            row.createCell(1).setCellValue(depense.getNom());
            row.createCell(2).setCellValue(depense.getDateDepense().toString());
            row.createCell(3).setCellValue(depense.getDescription());
            row.createCell(4).setCellValue(depense.getMontant());
            row.createCell(5).setCellValue(depense.getCategorie().getNom());
        }
    }

    private void createDepotSheet(Workbook workbook, List<Depot> depots) {
        Sheet sheet = workbook.createSheet("Depot");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Date Depot");
        headerRow.createCell(2).setCellValue("Montant");

        // Populate data rows
        int rowNum = 1;
        for (Depot depot : depots) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(depot.getId());
            row.createCell(1).setCellValue(depot.getDateDepot().toString());
            row.createCell(2).setCellValue(depot.getMontant());
        }
    }
}
