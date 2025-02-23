package com.qa.demo.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;
	Logger log = Logger.getLogger(LoginTest.class);
	
	
	@BeforeMethod
	public void setup() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\config\\log4j.properties");
				log.info(
				"****************************** Starting test cases execution  *****************************************");

		System.setProperty("webdriver.chrome.driver",
				"D:\\eclipse\\Workspace\\BasicFW_Log4j\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		log.info("launching chrome broswer");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.freecrm.com/");
		log.info("entering application URL");
		log.warn("Hey this just a warning message");
		log.fatal("hey this is just fatal error message");
		log.debug("this is debug message");
	}

	@Test(priority = 1)
	public void freeCrmTitleTest() {
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
		String title = driver.getTitle();
		System.out.println(title);
		log.info("login page title is--->" + title);
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software");

		log.info("****************************** ending test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
	}

	@Test(priority = 2)
	public void freemCRMLogoTest() {
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** freemCRMLogoTest *****************************************");

		boolean b = driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
		Assert.assertTrue(b);

		log.info("****************************** ending test case *****************************************");
		log.info("****************************** freemCRMLogoTest *****************************************");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("****************************** Browser is closed *****************************************");

	}
}
