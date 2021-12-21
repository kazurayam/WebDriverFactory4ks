package com.kazurayam.ks.webdriverfactory

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.driver.DriverType
import com.kazurayam.ks.webdriverfactory.DriverTypeName


@RunWith(JUnit4.class)
public class WebDriverFactoryTest {

	WebDriver driver

	@After
	void quitWebDriver() {
		if (driver != null) {
			driver.quit()
			driver = null
		}
	}

	@Test
	public void test_newWebDriver_ChromeDriver() {
		DriverType chrome = DriverTypeName.CHROME_DRIVER
		driver = WebDriverFactory.newWebDriver(chrome)
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_Headless() {
		DriverType chromeHeadless = DriverTypeName.HEADLESS_DRIVER
		driver = WebDriverFactory.newWebDriver(chromeHeadless)
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile() {
		DriverType chrome = DriverTypeName.CHROME_DRIVER
		driver = WebDriverFactory.newWebDriver(chrome, 'Katalon')
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile_byDriverTypeName() {
		driver = WebDriverFactory.newWebDriver(DriverTypeName.CHROME_DRIVER, 'Katalon')
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_Headless_withProfile() {
		DriverType chromeHeadless = DriverTypeName.HEADLESS_DRIVER
		driver = WebDriverFactory.newWebDriver(chromeHeadless, 'Katalon')
		assertNotNull(driver)
	}
}
