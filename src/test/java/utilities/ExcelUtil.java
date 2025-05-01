package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelUtil {

    // Read Excel and return List of Maps for all columns
    public static List<Map<String, String>> getTestData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

            Row headerRow = sheet.getRow(0);
            int lastColumn = headerRow.getLastCellNum();
            
            // Read data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < lastColumn; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    if (headerCell == null) continue;

                    String key = headerCell.getStringCellValue().trim();
                    Cell dataCell = row.getCell(j);
                    String value = (dataCell != null) ? dataCell.toString().trim() : "";
                    rowData.put(key, value);
                }
                dataList.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    // Utility method: converts List<Map> to Object[][]
    public static Object[][] getDataFromFile(String filePath, String sheetName) {
        List<Map<String, String>> dataList = getTestData(filePath, sheetName);

        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}
