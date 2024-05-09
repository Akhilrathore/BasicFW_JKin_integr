package com.qa.demo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.demo.base.BasePage;

public class TestUtil extends BasePage {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_TXTFILE = System.getProperty("user.dir") + "//resource//testdata//TestData_text";
	static JavascriptExecutor js;

	public static void switchToFrameUtility(WebElement ele_frame) {
		driver.switchTo().frame(ele_frame);
	}

	public static void switchToFrameUtilityIndex(int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/Error-screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void takeScreenshot(String pageName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile,
				new File(currentDir + "/Error-screenshots/" + pageName + System.currentTimeMillis() + ".png"));
	}

	/*
	 * public static void runTimeInfo(String messageType, String message) throws
	 * InterruptedException { js = (JavascriptExecutor) driver; // Check for jQuery
	 * on the page, add it if need be js.executeScript("if (!window.jQuery) {" +
	 * "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
	 * +
	 * "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
	 * + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
	 * Thread.sleep(5000);
	 * 
	 * // Use jQuery to add jquery-growl to the page js.executeScript(
	 * "$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')"
	 * );
	 * 
	 * // Use jQuery to add jquery-growl styles to the page
	 * js.executeScript("$('head').append('<link rel=\"stylesheet\" " +
	 * "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " +
	 * "type=\"text/css\" />');"); Thread.sleep(5000);
	 * 
	 * // jquery-growl w/ no frills
	 * js.executeScript("$.growl({ title: 'GET', message: '/' });"); //'"+color+"'"
	 * if (messageType.equals("error")) {
	 * js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message +
	 * "' });"); } else if (messageType.equals("info")) { js.
	 * executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });"
	 * ); } else if (messageType.equals("warning")) { js.
	 * executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });"
	 * ); } else System.out.println("no error message"); // jquery-growl w/
	 * colorized output // js.
	 * executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });"
	 * ); // js.
	 * executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });"
	 * ); // js.
	 * executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });"
	 * ); Thread.sleep(5000); }
	 */
	// Select >>>>>=====-----
	public static void selectDropDownByIndexUtility(WebElement ele, int index) {
		Select dropdown = new Select(ele);
		dropdown.selectByIndex(index);
	}

	public static void selectDropDownByValueUtility(WebElement ele, String option) {
		Select dropdown = new Select(ele);
		dropdown.selectByValue(option);
	}

	public static void selectDropDownByVisibleTextUtility(WebElement ele, String txt) {
		Select dropdown = new Select(ele);
		dropdown.selectByVisibleText(txt);
	}

//		public static void multiSelectUtility(WebDriver driver, WebElement ele) {
	//
//		}
	//
//		public static void deselectAllUtility(WebDriver driver, WebElement ele) {
	//
//		}

	// Select -----=====<<<<<
	public static String dataNtime() {
		Date dateObj = new Date();
//		SimpleDateFormat currTime1 = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z"); >>Thu, 14 Jul 2022 09:04:55 +0000
		SimpleDateFormat currTime1 = new SimpleDateFormat("ddMMMHHmm");
		// currTime= ("%2$tB %2$td, %2$tY 'at' HH:mm:ss", date_n_time);
		return currTime1.format(dateObj);
	}

	public static void setTextDataFile(String data) throws IOException {
		File FC = new File(TESTDATA_TXTFILE + data + ".txt");

		// Try block to check for exceptions
		try {

			// Open given file in append mode by creating an
			// object of BufferedWriter class
			BufferedWriter out = new BufferedWriter(new FileWriter(FC, true));

			// Writing on output stream
			out.write("Candidate Created: " + data);
			// Closing the connection
			out.close();
		}

		// Catch block to handle the exceptions
		catch (IOException e) {

			// Display message when exception occurs
			System.out.println("exception occurred" + e);
		}
	}
}