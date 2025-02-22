package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelUtil {
    private static final String EXCEL_PATH = "./test-results/TestResults.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;

    static {
        try {
            File file = new File(EXCEL_PATH);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("TestResults");
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("TestResults");
                
                // Creating header row
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Test Scenario No.");
                header.createCell(1).setCellValue("Test Case ID");
                header.createCell(2).setCellValue("Input");
                header.createCell(3).setCellValue("Status");
                header.createCell(4).setCellValue("Remarks");
                header.createCell(5).setCellValue("Severity");
                header.createCell(6).setCellValue("Expected/Actual Results");
                header.createCell(7).setCellValue("Pass or Fail");
                header.createCell(8).setCellValue("Complexity");

                saveExcel();
            }
        } catch (IOException e) {
            System.out.println("Excel Initialization Error: " + e.getMessage());
        }
    }

    public static void writeTestResult(String scenarioNo, String testCaseID, String input, String status, String remarks, String severity, String expectedActualResults, String passFail, String complexity) {
        try {
            int rowCount = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(scenarioNo);
            row.createCell(1).setCellValue(testCaseID);
            row.createCell(2).setCellValue(input);
            row.createCell(3).setCellValue(status);
            row.createCell(4).setCellValue(remarks);
            row.createCell(5).setCellValue(severity);
            row.createCell(6).setCellValue(expectedActualResults);
            row.createCell(7).setCellValue(passFail);
            row.createCell(8).setCellValue(complexity);

            saveExcel();
        } catch (IOException e) {
            System.out.println("Error writing to Excel: " + e.getMessage());
        }
    }

    private static void saveExcel() throws IOException {
        FileOutputStream fos = new FileOutputStream(EXCEL_PATH);
        workbook.write(fos);
        fos.close();
    }
}

