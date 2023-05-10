package com.indianEagleProject.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends PageBase {

	@FindBy(css = "#flightDetailsDiv div[id='pageDivId1'] div.itineraryLoop:nth-child(1) form+div")
	private WebElement btnBookTheTicketOfFirstItenary;

	@FindBy(css = "li[class*='tripType']+li+li+li")
	private WebElement modifyCabin;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		WebDriverWait searchResultsFullLoadWait = new WebDriverWait(driver, Duration.ofSeconds(110));
		searchResultsFullLoadWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.affirm")));
		System.out.println("Research results are fully loaded");
	}

	public ReviewPage BookTheTicket() throws InterruptedException {
		btnBookTheTicketOfFirstItenary.click();
		Thread.sleep(5000);
		return new ReviewPage(driver);

	}

	public String getCabinText() {
		String cabinText = modifyCabin.getText();
		System.out.println(cabinText);
		return cabinText;
	}

}
