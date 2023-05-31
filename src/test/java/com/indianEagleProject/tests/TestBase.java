package com.indianEagleProject.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.indianEagleProject.pages.HomePage;
import com.indianEagleProject.util.DataDrivenManager;
import com.indianEagleProject.util.ExcelDataReader;
import com.indianEagleProject.util.ExtentManager;
import com.indianEagleProject.util.WebDriverUtil;




public class TestBase {
	
	private final static String TEST_CONFIG_FILE_PATH = "testconfig.properties";
	private static final String EXTENT_REPORT_FILE_PATH = "AutomationReport.html";
	
	private WebDriver driver;
	String InputFile;
	protected HomePage homePage;
	
	protected static Properties testConfig;
	protected static ExtentReports extentReport;
	protected static ThreadLocal<ExtentTest> erTestThread = new ThreadLocal<ExtentTest>();
	protected ExtentTest erTest;
	@Parameters("InputFile")
	@BeforeSuite
	public void suiteSetup(String InputFile) throws FileNotFoundException, IOException
	{
		testConfig = new Properties();
		new FileInputStream(InputFile);	
		extentReport = ExtentManager.createInstance(EXTENT_REPORT_FILE_PATH);
	}
	@Parameters("browser")
	@BeforeMethod
	public void testSetup(String browser) throws FileNotFoundException, IOException {
	
		driver = WebDriverUtil.createDriver(browser);

		driver.get(testConfig.getProperty("baseurl"));


		homePage = new HomePage(driver);
	}
	
	@BeforeMethod
	public synchronized void extentReportBeforeMethod(Method method) {	
		erTest = extentReport.createTest(method.getName() );
		erTestThread.set(erTest);
	}
	
	@AfterMethod
	public void testCleanup()
	{
		WebDriverUtil.quitDriver(driver);
	}
	
	
	@DataProvider
    public Object[][] testData() throws IOException {
        String excelFilePath = System.getenv("InputFile"); // Retrieve file path from Jenkins environment variable
        String sheetName = "TestDataSheet1"; // Name of the sheet in the Excel file

        return ExcelDataReader.readDataFromExcel(excelFilePath, sheetName);
    }
	/*@DataProvider
	public Object[][] dataProvider(Method method)
	{
		 
		DataDrivenManager ddm = new DataDrivenManager("indianEagle.xlsx");
		return 
			ddm.getTestCaseDataSets("TestDataSheet1",method.getName());
	}*/
	

	@AfterMethod
    public synchronized void extentAfterMethod(ITestResult result) 
	
	{
		if (result.getStatus() == ITestResult.FAILURE)
			erTest.fail(result.getThrowable());
		else if (result.getStatus() == ITestResult.SKIP)
			erTest.skip(result.getThrowable());
		else
			erTest.pass("TEST PASSED");

        flushExtentReport();
    }
	
	@AfterSuite
	public void afterSuite() {
		flushExtentReport();
	}

	private void flushExtentReport() {
		if (extentReport != null) {
			extentReport.flush();
		}
	}
	
}
