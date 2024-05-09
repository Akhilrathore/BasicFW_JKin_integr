package com.qa.demo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.demo.base.BasePage;
import com.qa.demo.pages.IndexPage;
import com.qa.demo.pages.LoginPage;
import com.qa.demo.pages.LoginSucessPage;
import com.qa.demo.util.TestUtil;

public class LoginSucessPageTest extends BasePage {
	TestUtil testUtil;
	IndexPage indexpage;
	LoginPage loginpage;
	LoginSucessPage loginsucesspage;

	public LoginSucessPageTest() {
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
		loginpage = new LoginPage();
		loginsucesspage = new LoginSucessPage();

		// index page > > >
		loginpage = indexpage.clickNewToursLink();
		Assert.assertTrue(loginsucesspage.checkImage());
		// login page > > >
		loginsucesspage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		// login success > > >
	}

	@Test(priority = 1)
	public void checkLoginSucessPageTest() {
		Assert.assertEquals(loginsucesspage.validateLoginPageTitle(), "Login: Mercury Tours");
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully");
		System.err.println("validatePageHeading fail for screenshot");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
