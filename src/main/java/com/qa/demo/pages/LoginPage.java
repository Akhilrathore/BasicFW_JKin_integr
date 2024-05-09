package com.qa.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.base.BasePage;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	// Page Factory - OR:
//	@FindBy(name = "userName1")
	@FindBy(name = "userName")
	@CacheLookup
	WebElement txtusername;
	// fail to check screenshots

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtpassword;

	@FindBy(name = "submit")
	@CacheLookup
	WebElement btnlogin;

	@FindBy(xpath = "//img[contains(@alt,'Featured Destination: Aruba')]")
	@CacheLookup
	WebElement imglogo;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	// Allure Report >>>
	@Step("Getting page Title step...")
	// Allure Report <<<
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	// Allure Report >>>
	@Step("Image Display or not step...")
	// Allure Report <<<
	public boolean validateDestinationImage() {
		return imglogo.isDisplayed();
	}

	// Allure Report >>>
	@Step("Login with username:{0} and Password:{1} step...")
	// Allure Report <<<
	public LoginSucessPage login(String un, String pwd) {
		txtusername.sendKeys(un);
		txtpassword.sendKeys(pwd);
		btnlogin.click();
//		    	JavascriptExecutor js = (JavascriptExecutor)driver;
//		    	js.executeScript("arguments[0].click();", loginBtn);
//		    	
		return new LoginSucessPage();
	}

}
