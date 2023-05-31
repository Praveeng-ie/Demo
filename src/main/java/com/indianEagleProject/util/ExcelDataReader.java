package com.indianEagleProject.util;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelDataReader {
	public static Object[][] readDataFromExcel(String filePath, String sheetName) throws IOException {
        
		FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Skip header row

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(cell);
                data[i][j] = value;
            }
        }

        workbook.close();
        file.close();

        return data;
    }

}
