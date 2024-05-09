package com.qa.demo.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demo.base.BasePage;
import com.qa.demo.pages.FlightFinderPage;
import com.qa.demo.pages.IndexPage;
import com.qa.demo.pages.LoginPage;
import com.qa.demo.pages.LoginSucessPage;
import com.qa.demo.pages.ReservationPage;
import com.qa.demo.util.ExcelUtil;
import com.qa.demo.util.TestUtil;

public class ReservationPageTest extends BasePage {
	TestUtil testUtil;
	ExcelUtil xlUtil;
	IndexPage indexpage;
	LoginPage loginpage;
	LoginSucessPage loginsucesspage;
	ReservationPage reservationpage;
	FlightFinderPage flightfinderpage;

	public ReservationPageTest() {
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
		xlUtil = new ExcelUtil();
		indexpage = new IndexPage();
		loginpage = new LoginPage();
		loginsucesspage = new LoginSucessPage();
		reservationpage = new ReservationPage();
		flightfinderpage = new FlightFinderPage();
		// index page > > >
		loginpage = indexpage.clickNewToursLink();
		// login page > > >
		loginsucesspage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		// login success > > >

	}

//	@Test(priority = 1)
	public void checkReservationPageTitle() {
		Assert.assertEquals(reservationpage.validateLoginPageTitle(), "Find a Flight: Mercury Tours:");
	}

	@Test(priority = 1, dataProvider = "Reservation Details")
	public void checkReservationPageFunction(String Type, String Departing_From, int Passengers, int Departing_On_Month,
			int Departing_On_Date, String Arriving_In, int Arriving_On_Month, int Arriving_On_Date,
			String Service_Class, String Airline) throws InvalidFormatException {
		reservationpage = loginsucesspage.openReservationPage();
		flightfinderpage = reservationpage.openReservationPage(Type, Departing_From, Passengers, Departing_On_Month,
				Departing_On_Date, Arriving_In, Arriving_On_Month, Arriving_On_Date, Service_Class, Airline);
	}

	@DataProvider(name = "Reservation Details")
	public Object[][] getDatafromExcel() throws InvalidFormatException {
//		Object[][] arrayObject = ExcelUtil.getTestData("Sheet2");
		return ExcelUtil.getTestData("Sheet2");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
