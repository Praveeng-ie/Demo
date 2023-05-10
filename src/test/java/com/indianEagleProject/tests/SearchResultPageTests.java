package com.indianEagleProject.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.indianEagleProject.pages.SearchResultPage;
import com.indianeagle.enums.AdultsCount;
import com.indianeagle.enums.CabinType;
import com.indianeagle.enums.TripType;

public class SearchResultPageTests extends TestBase {
	SearchResultPage searchResultPage;

	@Test(dataProvider = "dataProvider")
	public void BookTheFirstItenaryTicket(String tripType, String fromAirport, String toAirport, String cabin,
			String adults) throws InterruptedException {

		searchResultPage = homePage.doFlightSearch(Enum.valueOf(TripType.class, tripType), 
														fromAirport,
														toAirport,
														Enum.valueOf(CabinType.class, cabin), 
														Enum.valueOf(AdultsCount.class, adults));
		searchResultPage.BookTheTicket();

	}

	@Test(dataProvider = "dataProvider")
	public void bookFlight(String tripType, String fromAirport, String toAirport, String cabin,String adults )
			throws InterruptedException {
	String actualText	=homePage.doFlightSearch(Enum.valueOf(TripType.class,tripType), "SFO", "HYD", Enum.valueOf(CabinType.class, cabin), 
				null).getCabinText();
	String ExpectedText=cabin;
	Assert.assertEquals(actualText.replace(" ", ""), ExpectedText);
	}
}
