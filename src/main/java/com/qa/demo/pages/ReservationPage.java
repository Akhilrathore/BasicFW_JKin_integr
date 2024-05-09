package com.qa.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.base.BasePage;
import com.qa.demo.util.TestUtil;

public class ReservationPage extends BasePage {
	TestUtil testutil;
	// Page Factory - OR:
	@FindBy(css = "input[value=roundtrip]")
	@CacheLookup
	WebElement radioroundtrip;

	@FindBy(css = "input[value=oneway]")
	@CacheLookup
	WebElement radiooneway;

	@FindBy(name = "passCount")
	@CacheLookup
	WebElement lstpassCount;

	@FindBy(name = "fromPort")
	@CacheLookup
	WebElement lstfromPort;

	@FindBy(name = "fromMonth")
	@CacheLookup
	WebElement lstfromMonth;

	@FindBy(name = "fromDay")
	@CacheLookup
	WebElement lstfromDay;
	@FindBy(name = "toPort")
	@CacheLookup
	WebElement lsttoPort;

	@FindBy(name = "toMonth")
	@CacheLookup
	WebElement lsttoMonth;

	@FindBy(name = "toDay")
	@CacheLookup
	WebElement lsttoDay;

	@FindBy(css = "input[value=Coach]")
	@CacheLookup
	WebElement radioCoach;

	@FindBy(css = "input[value=Business]")
	@CacheLookup
	WebElement radioBusiness;

	@FindBy(css = "input[value=First]")
	@CacheLookup
	WebElement radioFirst;

	@FindBy(name = "airline")
	@CacheLookup
	WebElement lstairline;

//	@FindBy(css = "input[src=images/continue.gif]")
	@FindBy(name = "findFlights")
	@CacheLookup
	WebElement btnContinue;

	// Initializing the Page Objects:
	public ReservationPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public FlightFinderPage openReservationPage(String Type, String Departing_From, int Passengers,
			int Departing_On_Month, int Departing_On_Date, String Arriving_In, int Arriving_On_Month,
			int Arriving_On_Date, String Service_Class, String Airline) {
		if (Type == "Round" || Type == "round") {
			radioroundtrip.click();
		} else {
			radiooneway.click();
		}

		TestUtil.selectDropDownByIndexUtility(lstpassCount, Passengers);
		TestUtil.selectDropDownByValueUtility(lstfromPort, Departing_From);
		TestUtil.selectDropDownByIndexUtility(lstfromMonth, Departing_On_Month);
		TestUtil.selectDropDownByIndexUtility(lstfromDay, Departing_On_Date);
		TestUtil.selectDropDownByValueUtility(lsttoPort, Arriving_In);
		TestUtil.selectDropDownByIndexUtility(lsttoMonth, Arriving_On_Month);
		TestUtil.selectDropDownByIndexUtility(lsttoDay, Arriving_On_Date);

		switch (Service_Class) {
		case "First":
			radioFirst.click();
			break;
		case "Business":
			radioBusiness.click();
			break;
		default:
			radioCoach.click();
			break;
		}

		TestUtil.selectDropDownByVisibleTextUtility(lstairline, Airline);
		btnContinue.click();
		return new FlightFinderPage();

	}
}
