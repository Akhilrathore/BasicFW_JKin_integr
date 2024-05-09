package com.qa.demo.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.demo.base.BasePage;
import com.qa.demo.pages.IndexPage;
import com.qa.demo.pages.LoginPage;
import com.qa.demo.pages.LoginSucessPage;
import com.qa.demo.util.ExcelUtil;
import com.qa.demo.util.TestUtil;

public class LoginPageTest extends BasePage {
	TestUtil testUtil;
	ExcelUtil excelUtil;
	IndexPage indexpage;
	LoginPage loginpage;
	LoginSucessPage loginsucesspage;

	public LoginPageTest() {
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

	@Test(priority = 1)
	public void checkLoginPageTitleImageTest() {

		// login page > > >
		Assert.assertEquals(loginpage.validateLoginPageTitle(), "Welcome: Mercury Tours");
		Assert.assertTrue(loginpage.validateDestinationImage());

	}

	// ======================================================================================
	// get username & password from Property file
	@Test(priority = 1)
	public void checkLoginFunctionCredentialsfrom_Property() {
		loginsucesspage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		// login success > > >
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully");
	}

	// --------------------------------------------------------------------------------------
	// get username & password from TestNg file at runtime
	@Parameters({ "usrName", "pwd" })
	@Test(priority = 1)
	public void checkLoginFunctionCredentialsfrom_TestNG(String usrName, String pwd) {
		loginsucesspage = loginpage.login(usrName, pwd);
		// login success > > >
		Assert.assertEquals(loginsucesspage.validatePageHeading(), "Login Successfully");
	}

	// --------------------------------------------------------------------------------------
	// get username & password from @DataProvider file

	@Test(priority = 1, dataProvider = "getting data from Excel")
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
