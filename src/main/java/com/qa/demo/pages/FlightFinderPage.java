package com.qa.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.base.BasePage;

public class FlightFinderPage extends BasePage {

	// Page Factory - OR:
	@FindBy(tagName = "h3")
	@CacheLookup
	WebElement pageheading;

//	@FindBy(xpath = "//img[contains(@alt,'Featured Destination: Aruba')]")
//	@CacheLookup
//	WebElement imglogo;

	// Initializing the Page Objects:
	public FlightFinderPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public String validatePageHeading() {
		return pageheading.getText();
	}

	public void openReservationPage() {

	}
}
