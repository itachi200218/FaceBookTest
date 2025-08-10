package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ExcelReader {

    public static Object[][] readExcelData(String fileName, String sheetName, int expectedColumns) {
        Object[][] data = null;

        try (InputStream is = ExcelReader.class.getClassLoader().getResourceAsStream(fileName);
             Workbook workbook = new XSSFWorkbook(is)) {

            if (is == null) {
                throw new IOException("File not found in resources: " + fileName);
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            int actualColCount = sheet.getRow(0).getLastCellNum();
            int colCount = Math.min(expectedColumns, actualColCount);

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    System.out.println("Skipping empty row at index: " + i);
                    continue;
                }

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        data[i - 1][j] = "";
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i - 1][j] = cell.getStringCellValue().trim();
                                break;
                            case NUMERIC:
                                double num = cell.getNumericCellValue();
                                if (num == (long) num) {
                                    data[i - 1][j] = String.valueOf((long) num);
                                } else {
                                    data[i - 1][j] = String.valueOf(num);
                                }
                                break;
                            case BOOLEAN:
                                data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                                break;
                            default:
                                data[i - 1][j] = cell.toString().trim();
                        }
                    }
                }

                System.out.println("Row " + i + " → " + Arrays.toString(data[i - 1]));
            }

        } catch (Exception e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }
}
