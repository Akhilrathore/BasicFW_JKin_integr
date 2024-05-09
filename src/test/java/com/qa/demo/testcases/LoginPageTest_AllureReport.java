package com.qa.demo.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.demo.base.BasePage;
import com.qa.demo.listeners.TestAllureListener;
import com.qa.demo.pages.IndexPage;
import com.qa.demo.pages.LoginPage;
import com.qa.demo.pages.LoginSucessPage;
import com.qa.demo.util.ExcelUtil;
import com.qa.demo.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

// for Allure Listener
@Listeners(TestAllureListener.class)

public class LoginPageTest_AllureReport extends BasePage {
	TestUtil testUtil;
	ExcelUtil excelUtil;
	IndexPage indexpage;
	LoginPage loginpage;
	LoginSucessPage loginsucesspage;

	public LoginPageTest_AllureReport() {
		// super will call parent(Testbase) class Constructor because we have to
				// initialize properties otherwise there will be Null Pointer exception.
		super();
	}

	// test cases should be separated -- independent with each other
	// before each test case -- launch the browser and login
	// @Test -- execute test case
	// after each test case -- close the browser

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		excelUtil = new ExcelUtil();
		indexpage = new IndexPage();
		loginpage = new LoginPage();
		loginsucesspage = new LoginSucessPage();
		// index page > > >
		loginpage = indexpage.clickNewToursLink();
	}

	@Test(priority = 1, description = "verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify login page title test on Login Page")
	@Story("Story Name: To check login page title")
	public void checkLoginPageTitleTest() {
		// login page > > >
		Assert.assertEquals(loginpage.validateLoginPageTitle(), "Welcome: Mercury Tours");
		Assert.assertTrue(loginpage.validateDestinationImage());

	}

	// ======================================================================================
	// get username & password from Property file
	@Test(priority = 1, description = "verifying login page get username & password from Property file")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify login page get creds from 'property' file and login")
	@Story("Story Name: To check get creds from 'property' file and login")
	public void checkLoginFunctionCredentialsfrom_Property() {
		loginsucesspage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		// login success > > >
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully123");
	}

	// --------------------------------------------------------------------------------------
	// get username & password from TestNg file at runtime
	@Parameters({ "usrName", "pwd" })
	@Test(priority = 1, description = "get username & password from TestNg file at runtime")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify login page get username & password from TestNg file at runtime")
	@Story("Story Name: To check login page get username & password from TestNg file at runtime")
	public void checkLoginFunctionCredentialsfrom_TestNG(String usrName, String pwd) {
		loginsucesspage = loginpage.login(usrName, pwd);
		// login success > > >
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully");
	}

	// --------------------------------------------------------------------------------------
	// get username & password from @DataProvider file

	@Test(priority = 1, dataProvider = "getting data from Excel", description = "verifying login page get username & password from @DataProvider file")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify login page title test get username & password from @DataProvider file on Login Page")
	@Story("Story Name: To check login page get username & password from @DataProvider file")
	public void checkLoginFunctionCredentialsfrom_DataProvider(String uname, String pwd) {
//	System.out.println(uname + pwd);
		loginsucesspage = loginpage.login(uname, pwd);
		// login success > > >
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully");
	}

	@DataProvider(name = "getting data from Excel")
	public Object[][] getDatafromExcel() throws InvalidFormatException {
		Object[][] arrayObject = ExcelUtil.getTestData("Sheet1");
//		System.out.println(arrayObject);
		return arrayObject;
	}

	// ======================================================================================
	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
