package com.kazurayam.ks.webdriverfactory

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.driver.DriverType
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kazurayam.ks.webdriverfactory.DriverTypeName
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory.UserDataAccess
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

@RunWith(JUnit4.class)
public class KSWebDriverFactoryTest {

	private WebDriver driver
	private String url = "http://demoaut.katalon.com"

	@Before
	void setup() {
		driver = null
	}

	@After
	void quitWebDriver() {
		WebUI.delay(1)
		if (driver != null) {
			driver.quit()
		}
	}

	@Test
	public void test_newWebDriver_ChromeDriver() {
		driver = KSWebDriverFactory.newWebDriver(DriverTypeName.CHROME_DRIVER)
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		//
		String employedDesiredCapabilities = KSWebDriverFactory.getEmployedDesiredCapabilities()
		println employedDesiredCapabilities
	}

	@Test
	public void test_newWebDriver_ChromeDriver_Headless() {
		driver = KSWebDriverFactory.newWebDriver(DriverTypeName.HEADLESS_DRIVER)
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile() {
		driver = KSWebDriverFactory.newWebDriver(DriverTypeName.CHROME_DRIVER, 'Katalon')
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile_Headless() {
		driver = KSWebDriverFactory.newWebDriver(DriverTypeName.HEADLESS_DRIVER, 'Katalon')
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile_FOR_HERE() {
		driver = KSWebDriverFactory.newWebDriver(DriverTypeName.HEADLESS_DRIVER, 'Katalon', UserDataAccess.FOR_HERE)
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
}
