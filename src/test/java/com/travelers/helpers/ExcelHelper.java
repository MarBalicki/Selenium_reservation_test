package com.travelers.helpers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExcelHelper {

    public static Object[][] readExcelFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
//        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        String format = ".xlsx";
        Workbook workbook = getCorrectWorkBook(format, inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumbers = sheet.getLastRowNum();
        int colNumbers = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowNumbers][colNumbers];

        for (int i = 0; i < rowNumbers; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                data[i][j] = row.getCell(j).getStringCellValue();
            }
        }
//        printIteratedSheet(sheet);
        return data;
    }

    private static void printIteratedSheet(Sheet sheet) {
        Iterator<Row> iterator = sheet.rowIterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.println(cell.getStringCellValue());
                } else if (cell.getCellType().equals(CellType.NUMERIC)) {
                    System.out.println(cell.getNumericCellValue());
                }
            }
        }
    }

    private static Workbook getCorrectWorkBook(String format, InputStream inputStream) throws IOException {
        if (format.equals(".xls")) {
            return new HSSFWorkbook(inputStream);
        } else if (format.equals(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else {
            throw new NoSuchElementException();
        }
    }

//    public static void main(String[] args) throws IOException {
//        Object[][] data = readExcelFile(new File("src/main/resources/files/Dane.xlsx"));
//        System.out.println(data[0][0]);
//    }
}
