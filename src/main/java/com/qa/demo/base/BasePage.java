package com.qa.demo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;

import com.qa.demo.listeners.TestAllureListener;
import com.qa.demo.listeners.WebEventListener;
import com.qa.demo.util.TestUtil;

//for Allure screenshot
@Listeners({ TestAllureListener.class })

public class BasePage {

	public static WebDriver driver;
	public static Properties prop;

	// for WebEventListener.java >>>>
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	// for WebEventListener.java <<<<

	// for Allure screenshot
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

//	// for Log4j
//	Logger log = Logger.getLogger(BasePage.class);

	public BasePage() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "//resources//config//config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		// for log4j
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//resources//config//log4j.properties");
	}

	public static WebDriver initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//resources//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "//resources//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}

//		// for WebEventListener.java >>>>
//		e_driver = new EventFiringWebDriver(driver);
//		// Now create object of EventListerHandler to register it with
//		// EventFiringWebDriver
//		eventListener = new WebEventListener();
//		e_driver.register(eventListener);
//		driver = e_driver;
//		// for WebEventListener.java <<<<

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

		// for Allure screenshot >>>
		tdriver.set(driver);
		return getDriver();
		// for Allure screenshot <<<
	}

	// for Allure screenshot >>>
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	// for Allure screenshot <<<
}