package com.indianEagleProject.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverUtil
{
	public static WebDriver createDriver(String browser)
	{
		WebDriver driver=null;
		
		
		if(browser.equalsIgnoreCase("Firefox"))
		{
			
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{

			driver = new EdgeDriver();
		}
		else
		{
			throw new InvalidParameterException(browser + " - is not a valid web browser for web driver.");
		}


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		
		return driver;
	}
	
	public static void quitDriver(WebDriver driver)
	{
		// Do all clean up logic
		driver.quit();
	}

	public static void getScreenshot(WebDriver driver, String filePath) throws IOException
	{
		File imgFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(imgFile.toPath(), Paths.get(filePath));
	}
	
	public static boolean isElementPresent(WebDriver driver, By by) {
	    try {
		      	driver.findElement(by);
		      	return true;
	    } 
	    catch (NoSuchElementException e) 
	    {
	    		return false;
	    }
	  }
	
	// Get Window Handle for given title keyword by going through all windows
	public static String getWindowHandle(WebDriver driver, String titleKeyWord)
	{
		String requiredWinHandle = "";
		
		// Get all available window handles
		Set<String> windowHandles = driver.getWindowHandles();
		
		for(String windowHandle : windowHandles)
		{
			// Switch to each window in windowHandles and check for title keyword
			driver.switchTo().window(windowHandle);
			if(driver.getTitle().contains(titleKeyWord))
			{
				// if title keyword is found in a Window Title, we can return that window handle
				requiredWinHandle =  windowHandle;
				break; // exit for-each loop
			}
		}

		return requiredWinHandle;
	}
	
} 
		
