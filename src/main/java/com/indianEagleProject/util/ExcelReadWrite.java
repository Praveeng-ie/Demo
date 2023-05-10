package com.indianEagleProject.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReadWrite {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		File excelFile = new File("MasterTestDataFile.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
				
		// Go to the folder
		// Go to the file
		// Open the file
		Workbook workbook;
		workbook = WorkbookFactory.create(fis);
		
		// Go to the sheet
		Sheet sh =  workbook.getSheet("TestDataSheet");
		
		// Go to the row
		Row row = sh.getRow(6);
		
		// Go to the cell
		Cell cell7B = row.getCell(1);
		
		// Read the cell
		// Print the value
		System.out.println("Cell 7B: " + cell7B.toString());
		System.out.println("Cell 9B: " + sh.getRow(8).getCell(1).toString());
		System.out.println("Cell 14D: " + sh.getRow(13).getCell(3).toString());
		sh.getRow(0).createCell(6).setCellValue("333333333");
		
	
		
		sh.getRow(13).getCell(3).setCellValue("CELL_CHANGED_TEST_PASSED");
		sh.getRow(0).getCell(0).setCellValue("SUPER AUTOMATION TEAM");
		
		Sheet ds1 = workbook.createSheet("DataSheet_2");
		ds1.createRow(3).createCell(3).setCellValue("Indian Eagle");
		
		
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(excelFile);
		workbook.write(fos);
		workbook.close();
		
	}
}
