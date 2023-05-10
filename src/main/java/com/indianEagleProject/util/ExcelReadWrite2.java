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

public class ExcelReadWrite2 {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		DataDrivenManager ddm = new DataDrivenManager
									("MasterTestDataFile.xlsx");
//		Object[][] tc1Data = 
//				ddm.getTestCaseDataSets("TestDataSheet", "testCase1");
//		print(tc1Data);
//		
//		Object[][] testSuccessfulLoginData = 
//				ddm.getTestCaseDataSets("TestDataSheet", "testSuccessfulLogin");
//		print(testSuccessfulLoginData);
		
		
		Object[][] testApplyingSortOrderData = 
				ddm.getTestCaseDataSets("TestDataSheet", "testFlightSearchPageLowestFareMessage");
		print(testApplyingSortOrderData);		
	}
	
	public static void print(Object[][] testData)
	{
		for (Object[] row : testData) {
			for (Object cell : row) {
				System.out.print(cell.toString() + "\t");
			}
			System.out.println();
		}
	}
}
