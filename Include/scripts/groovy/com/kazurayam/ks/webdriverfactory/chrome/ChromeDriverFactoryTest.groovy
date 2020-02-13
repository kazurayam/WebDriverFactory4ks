package com.kazurayam.ks.webdriverfactory.chrome

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.junit4ks.IgnoreRest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverUtils
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfile
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinder
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * @author kazurayam
 *
 */
@RunWith(JUnit4.class)
public class ChromeDriverFactoryTest {

	@Before
	void setup() {}


	/**
	 * Basic case.
	 * Open a Chrome browser with the default profile
	 */
	@Test
	//@IgnoreRest
	void test_openChromeDriver() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.openChromeDriver()
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	/**
	 * Open a Chrome browser specifying a user profile "Katalon"
	 */
	@Test
	void test_openChromeDriverWithProfile() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.openChromeDriverWithProfile('Katalon')
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	/**
	 * Open a Chrome browser specifying a profile directory "Default"
	 */
	@Test
	void test_openChromeDriverWithProfileDirectory() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.openChromeDriverWithProfileDirectory('Default')
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

}
