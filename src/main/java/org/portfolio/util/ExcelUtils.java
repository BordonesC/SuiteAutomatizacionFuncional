package org.portfolio.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {
    public static Object[][] getExcelData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();

            // Saltar encabezado
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                int colCount = row.getPhysicalNumberOfCells();
                String[] rowData = new String[colCount];

                for (int i = 0; i < colCount; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = String.valueOf(cell).trim();
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

    public static List<Map<String, String>> getExcelDataAsListOfMap(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Hoja no encontrada: " + sheetName);

            Row headerRow = sheet.getRow(0); // Primera fila como encabezado
            if (headerRow == null) throw new RuntimeException("La hoja está vacía.");

            int numCols = headerRow.getLastCellNum();
            int numRows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < numRows; i++) { // desde la segunda fila
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < numCols; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = row.getCell(j);

                    String header = getCellValueAsString(headerCell);
                    String value = getCellValueAsString(dataCell);
                    rowData.put(header, value);
                }
                dataList.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el archivo Excel: " + e.getMessage());
        }

        return dataList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                if (numericValue == Math.floor(numericValue)) {
                    return String.valueOf((int) numericValue);
                } else {
                    return String.valueOf(numericValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
