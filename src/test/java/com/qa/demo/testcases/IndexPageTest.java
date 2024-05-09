package com.qa.demo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.demo.base.BasePage;
import com.qa.demo.pages.IndexPage;
import com.qa.demo.util.TestUtil;

public class IndexPageTest extends BasePage {
	TestUtil testUtil;
	IndexPage indexpage;

	public IndexPageTest() {
		// super will call parent(Testbase) class Constructor because we have to
		// initialize properties otherwise there will be Null Pointer exception.
		super();
	}

	// test cases should be separated -- independent with each other
	// before each test case -- launch the browser and login
	// @test -- execute test case
	// after each test case -- close the browser

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		indexpage = new IndexPage();

//		indexpage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyindexpageTitleTest() {
		String indexpageTitle = indexpage.verifyindexpageTitle();
		Assert.assertEquals(indexpageTitle, "Guru99 Bank Home Page");
	}

	@Test(priority = 2)
	public void openNewToursPage() {
		indexpage.clickNewToursLink();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
