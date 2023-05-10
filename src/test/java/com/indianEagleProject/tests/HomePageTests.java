package com.indianEagleProject.tests;

import org.testng.annotations.Test;

import com.indianeagle.enums.AdultsCount;
import com.indianeagle.enums.CabinType;
import com.indianeagle.enums.TripType;



public class HomePageTests extends TestBase
{
	@Test(dataProvider = "dataProvider")
	public void doFlightSearch(String tripType, String fromAirport, String toAirport,String cabin,String adults)
			throws InterruptedException {
		homePage.doFlightSearch(Enum.valueOf(TripType.class, tripType), fromAirport,
				toAirport,Enum.valueOf(CabinType.class, cabin),Enum.valueOf(AdultsCount.class, adults));
	}
	@Test
	public void testTheDates() throws InterruptedException
	{
		homePage.testDates();
		Thread.sleep(5000);
	}
}
