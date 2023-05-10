package com.indianEagleProject.tests;

import org.testng.annotations.Test;

import com.indianEagleProject.pages.ReviewPage;
import com.indianeagle.enums.AdultsCount;
import com.indianeagle.enums.CabinType;
import com.indianeagle.enums.TripType;

public class ReviewPageTests extends TestBase
{
	ReviewPage reviewPage;
@Test(dataProvider = "dataProvider")
public void makeTheSignIn(String tripType, String fromAirport, String toAirport,String cabin,String adults) throws InterruptedException
{
	reviewPage=homePage.doFlightSearch(Enum.valueOf(TripType.class, tripType), fromAirport, toAirport,
			Enum.valueOf(CabinType.class, cabin), Enum.valueOf(AdultsCount.class, adults)).BookTheTicket();
	reviewPage.doSignIn();
	reviewPage.getTrustPilotReview();
	
	
}
}
