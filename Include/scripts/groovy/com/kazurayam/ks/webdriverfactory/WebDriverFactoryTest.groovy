package com.kazurayam.ks.webdriverfactory

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.driver.DriverType


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
		DriverType chrome = new DriverTypeImpl('CHROME_DRIVER')
		driver = WebDriverFactory.newWebDriver(chrome)
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_Headless() {
		DriverType chromeHeadless = new DriverTypeImpl('HEADLESS_DRIVER')
		driver = WebDriverFactory.newWebDriver(chromeHeadless)
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_withProfile() {
		DriverType chrome = new DriverTypeImpl('CHROME_DRIVER')
		driver = WebDriverFactory.newWebDriver(chrome, 'Katalon')
		assertNotNull(driver)
	}

	@Test
	public void test_newWebDriver_ChromeDriver_Headless_withProfile() {
		DriverType chromeHeadless = new DriverTypeImpl('HEADLESS_DRIVER')
		driver = WebDriverFactory.newWebDriver(chromeHeadless, 'Katalon')
		assertNotNull(driver)
	}

	class DriverTypeImpl implements DriverType {
		private String name
		DriverTypeImpl(String name) {
			this.name = name
		}
		String getName() {
			return this.name
		}
		String getPropertyKey() {
			throw new RuntimeException("TODO")
		}
		String getPropertyValue() {
			throw new RuntimeException("TODO")
		}
	}
}
