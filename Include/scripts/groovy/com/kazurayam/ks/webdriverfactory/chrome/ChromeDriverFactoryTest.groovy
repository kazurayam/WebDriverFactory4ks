package com.kazurayam.ks.webdriverfactory.chrome

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

import com.kazurayam.junit4ks.IgnoreRest
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
/**
 * @author kazurayam
 */
@RunWith(JUnit4.class)
public class ChromeDriverFactoryTest {

	@Before
	void setup() {}

	/**
	 * Basic case.
	 * Instantiate a ChromeDriver to open a Chrome browser with the default profile.
	 * 
	 */
	@Test
	void test_newChromeDriver() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.newChromeDriver()
		assertThat(driver, is(notNullValue()))

		DesiredCapabilities dc = cdFactory.getEmployedDesiredCapabilities()
		assertNotNull(dc)
		WebUI.comment("DesiredCapabilities: ${dc.toString()}")
		//assertThat(cdFactory.getChromeProfile().getName(), is('kazurayam'))

		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	/**
	 * Instantiate a ChromeDriver to open a Chrome browser specifying a user profile "Katalon"
	 * 
	 */
	@Test
	void test_newChromeDriverWithProfile() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.newChromeDriverWithProfile('Katalon')
		assertThat(driver, is(notNullValue()))

		DesiredCapabilities dc = cdFactory.getEmployedDesiredCapabilities()
		assertNotNull(dc)
		WebUI.comment("DesiredCapabilities: ${dc.toString()}")

		WebUI.comment("ChromeDriver has been instantiated with profile Katalon")
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	/**
	 * Instantiate a ChromeDriver to open Chrome browser specifying a profile directory "Default"
	 * 
	 */
	@Test
	void test_newChromeDriverWithProfileDirectory() {
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		WebDriver driver = cdFactory.newChromeDriverWithProfileDirectory('Default')
		assertThat(driver, is(notNullValue()))

		DesiredCapabilities dc = cdFactory.getEmployedDesiredCapabilities()
		assertNotNull(dc)
		WebUI.comment("DesiredCapabilities: ${dc.toString()}")

		WebUI.comment("ChromeDriver has been instantiated with profile directory Default")
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	/**
	 * open a session using a user profile Katalon and navigate too http://localhost, then close the session.
	 * the session will create a cookie "timestamp".
	 * open a second session using Katalon Profile again.
	 * I expect the second session will use the same value of timestamp cookie. So I test it.
	 * 
	 */
	@Ignore
	@Test
	public void test_if_cookie_is_retained_in_profile_accross_2_sessions() {
		// we want Headless
		ChromeDriverFactoryImpl cdFactory = new ChromeDriverFactoryImpl()
		//ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
		//cdFactory.addChromeOptionsModifier(com)
		//
		String url = 'http://localhost/'
		// 1st session
		WebDriver driver = cdFactory.newChromeDriverWithProfile('Katalon')
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		Set<Cookie> cookies = driver.manage().getCookies()
		println "1st session: " + cookies
		String phpsessid1st = driver.manage().getCookieNamed('timestamp')
		WebUI.closeBrowser()

		// 2nd session
		driver = cdFactory.newChromeDriverWithProfile('Katalon')
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		cookies = driver.manage().getCookies()
		println "2nd session: " + cookies
		String phpsessid2nd = driver.manage().getCookieNamed('timestamp')
		WebUI.closeBrowser()
		//
		assert phpsessid1st == phpsessid2nd;
	}
}
