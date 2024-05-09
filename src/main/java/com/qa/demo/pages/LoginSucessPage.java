package com.qa.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.base.BasePage;

public class LoginSucessPage extends BasePage {

	// Page Factory - OR:
	@FindBy(tagName = "h3")
	@CacheLookup
	WebElement pageheading;

//	@FindBy(xpath = "//img[contains(@alt,'Featured Destination: Aruba')]")
	@FindBy (xpath = "//img[@src='images/featured_destination.gif']")
	@CacheLookup
	WebElement imglogo;
	
	@FindBy(linkText =  "Flights")
	@CacheLookup
	WebElement lnkFlights;
	
	// Initializing the Page Objects:
	public LoginSucessPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public String validatePageHeading() {
		return pageheading.getText();
	}
	
	public boolean checkImage() {
		return imglogo.isDisplayed();				
	}

	public ReservationPage openReservationPage() {
		lnkFlights.click();
		return new ReservationPage();

	}
}
