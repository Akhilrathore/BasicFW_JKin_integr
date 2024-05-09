package com.qa.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.base.BasePage;

public class IndexPage extends BasePage {

	@FindBy(linkText = "New Tours")
	@CacheLookup
	WebElement NewToursLink;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	@CacheLookup
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	@CacheLookup
	WebElement newContactLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	@CacheLookup
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	@CacheLookup
	WebElement tasksLink;

	// Initializing the Page Objects:
	public IndexPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyCorrectUserName() {
		return NewToursLink.isDisplayed();
	}

	public LoginPage clickNewToursLink() {
		NewToursLink.click();
		return new LoginPage();
	}

//	
	public String verifyindexpageTitle() {
		return driver.getTitle();
	}

	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();

	}

}
