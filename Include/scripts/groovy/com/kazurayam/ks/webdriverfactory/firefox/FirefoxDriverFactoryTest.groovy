package com.kazurayam.ks.webdriverfactory.firefox

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class FirefoxDriverFactoryTest {

	@Ignore
	@Test
	public void test_newFirefoxDriver_noArg() {
		FirefoxDriverFactory factory = FirefoxDriverFactory.newInstance()
		WebDriver driver = factory.newFirefoxDriver()
		assert driver != null
		driver.quit()
	}

	/**
	 * Instantiate a FirefoxDriver to open a Firefox browser specifying a user profile "Katalon"
	 *
	 */
	@Test
	void test_newFirefoxDriverWithProfile() {
		FirefoxDriverFactory factory = FirefoxDriverFactory.newInstance()
		WebDriver driver = factory.newFirefoxDriverWithProfile('Katalon')
		assertThat(driver, is(notNullValue()))

		DesiredCapabilities dc = factory.getEmployedDesiredCapabilities()
		assertNotNull(dc)
		WebUI.comment("DesiredCapabilities: ${dc.toString()}")

		WebUI.comment("ChromeDriver has been instantiated with profile Katalon")
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}
}
