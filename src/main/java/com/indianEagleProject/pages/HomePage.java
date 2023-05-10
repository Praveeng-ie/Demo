package com.indianEagleProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.indianeagle.enums.AdultsCount;
import com.indianeagle.enums.CabinType;
import com.indianeagle.enums.TripType;

public class HomePage extends PageBase {
	@FindBy(css = "input#roundTrip + span")
	private WebElement rdoRoundTrip;

	@FindBy(css = "#oneWay + span")
	private WebElement rdoOneWay;

	@FindBy(id = "searchFlights")
	private WebElement btnSearchFlights;

	@FindBy(id = "sourceDiv")
	private WebElement txtFromAirport;

	@FindBy(id = "destinationDiv")
	private WebElement txtToAirport;

	@FindBy(css = "form#searchForm div.panel-heading")
	private WebElement divSearchFormHeader;

	@FindBy(tagName = "body")
	private WebElement bdyHtmlBody;

	@FindBy(css = "button[data-id=adults]")
	private WebElement ddnAdults;

	@FindBy(xpath = "//span[text()='Economy']")
	private WebElement btnEconomy;

	@FindBy(xpath = "//span[text()='Business']")
	private WebElement btnBusiness;

	@FindBy(xpath = "//span[text()='Premium Economy']")
	private WebElement btnPremiumEconomy;

	@FindBy(xpath = "//span[text()='First']")
	private WebElement btnFirst;

	@FindBy(css = "button[data-id=cabin]")
	private WebElement ddnCabin;

	@FindBy(css = "#adults+div ul li[data-original-index='0']")
	private WebElement adults1;

	@FindBy(css = "#adults+div ul li[data-original-index='1']")
	private WebElement adults2;

	@FindBy(css = "#adults+div ul li[data-original-index='2']")
	private WebElement adults3;

	@FindBy(css = "#adults+div ul li[data-original-index='3']")
	private WebElement adults4;

	@FindBy(css = "#adults+div ul li[data-original-index='4']")
	private WebElement adults5;

	@FindBy(css = "#adults+div ul li[data-original-index='5']")
	private WebElement adults6;

	@FindBy(xpath="//div[@class='ui-datepicker-group ui-datepicker-group-last']//tr/td/a[(text()='3')]")
	private WebElement dateSecondCalender;
	
	@FindBy(css="div[class*='datepicker-group-first'] div[class*='datepicker-title'] span[class*='datepicker-month']")
	private WebElement dateTitleMonth;
	
	@FindBy(css="div[class*='datepicker-group-first'] div[class*='datepicker-title'] span[class*='datepicker-year']")
	private WebElement dateTitleyear;
	
	@FindBy(css="span[class*='right datepicker-arrow']")
	private WebElement datePickArrow;
	
	@FindBy(css="span[class*='left datepicker-arrow']")
	private WebElement datePickleftArrow;
	
	@FindBy(id="departureDate")
	private WebElement dateDeparture;
	
	@FindBy(id="returnDate")
	private WebElement dateReturn;
	
	public HomePage(WebDriver driver) {
		super(driver);
		bdyHtmlBody.click();
	}

	public SearchResultPage doFlightSearch(TripType tripType, String fromAirport, String toAirport, CabinType cabin,
			AdultsCount adults) throws InterruptedException {

		if (tripType != null) {
			if (tripType == TripType.ONE_WAY) {
				rdoOneWay.click();
			} else if (tripType == TripType.ROUND_TRIP) {
				rdoRoundTrip.click();
			}
		}
		if (fromAirport != null) {
			fillAirportInput(txtFromAirport, fromAirport);
		}
		if (toAirport != null) {
			fillAirportInput(txtToAirport, toAirport);
		}
		if (cabin != null) {
			ddnCabin.click();
			if (cabin == CabinType.ECONOMY) {
				btnEconomy.click();
			} else if (cabin == CabinType.FIRST) {
				btnFirst.click();
			} else if (cabin == CabinType.BUSINESS) {
				btnBusiness.click();
			} else if (cabin == CabinType.PREMIUMECONOMY) {
				btnPremiumEconomy.click();
			}
		}
		if (adults != null) {
			ddnAdults.click();

			if (adults == AdultsCount.count1) {
				adults1.click();
			} else if (adults == AdultsCount.count2) {
				adults2.click();
			} else if (adults == AdultsCount.count3) {
				adults3.click();
			} else if (adults == AdultsCount.count4) {
				adults4.click();
			} else if (adults == AdultsCount.count5) {
				adults5.click();
			} else if (adults == AdultsCount.count6) {
				adults6.click();
			}
		}
		btnSearchFlights.click();

		return new SearchResultPage(driver);
		
	}
	public void testDates() throws InterruptedException
	{
		fillAirportInput(txtFromAirport, "HYD");
		fillAirportInput(txtToAirport, "SFO");
		dateDeparture.click();
		datePickleftArrow.click();
		String departureMonth="JUNE";
		String departureYear="2023";
		String departureDay="24";
		while(true)
		{
		String month=dateTitleMonth.getText().toUpperCase();
		String year=dateTitleyear.getText();
		if(month.equals(departureMonth)&& year.equals(departureYear))
		{
			break;
		}
		else
		{
			datePickArrow.click();
		}
		}
		driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']"
				+ "//tr/td/a[(text()="+departureDay+")]")).click();
		dateReturn.click();
		String returnMonth="JULY";
		String returnYear="2023";
		String returnDay="17";
		while(true)
		{
			String month=dateTitleMonth.getText().toUpperCase();
			String year=dateTitleyear.getText();
		if(month.equals(returnMonth)&& year.equals(returnYear))
		{
			break;
		}
		else
		{
			datePickArrow.click();
		}
		}
		driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']"
				+ "//tr/td/a[(text()="+returnDay+")]")).click();
		btnSearchFlights.click();
	}
	private void fillAirportInput(WebElement txtAirport, String airportCode) throws InterruptedException {
		txtAirport.click();
		txtAirport.sendKeys(airportCode);
		Thread.sleep(2000);
		divSearchFormHeader.click();

	}
}

