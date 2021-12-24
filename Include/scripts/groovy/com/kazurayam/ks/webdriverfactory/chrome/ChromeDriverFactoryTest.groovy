package com.kazurayam.ks.webdriverfactory.chrome

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.ZoneId
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
import java.util.regex.Pattern
import java.util.regex.Matcher


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
	@Ignore
	@Test
	void test_newChromeDriver() {
		ChromeDriverFactory cdFactory = ChromeDriverFactory.newInstance()
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
	@Ignore
	@Test
	void test_newChromeDriverWithProfile() {
		ChromeDriverFactory cdFactory = ChromeDriverFactory.newInstance()
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
	@Ignore
	@Test
	void test_newChromeDriverWithProfileDirectory() {
		ChromeDriverFactory cdFactory = ChromeDriverFactory.newInstance()
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
	
	@Test
	public void test_if_cookie_is_retained_in_profile_accross_2_sessions() {
		// we want Headless
		ChromeDriverFactory cdFactory = ChromeDriverFactory.newInstance()
		//ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
		//cdFactory.addChromeOptionsModifier(com)
		//
		String url = 'http://localhost/'
		// 1st session
		WebDriver driver = cdFactory.newChromeDriverWithProfile('Katalon')
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		Set<Cookie> cookies = driver.manage().getCookies()
		println "1st session: " + printCookies(cookies)
		String timestamp1st = driver.manage().getCookieNamed('timestamp').getValue()
		WebUI.closeBrowser()

		// 2nd session
		driver = cdFactory.newChromeDriverWithProfile('Katalon')
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		cookies = driver.manage().getCookies()
		println "2nd session: " + printCookies(cookies)
		String timestamp2nd = driver.manage().getCookieNamed('timestamp').getValue()
		WebUI.closeBrowser()
		//
		assert timestamp1st == timestamp2nd;
	}
	
	
	String printCookies(Set<Cookie> cookies) {
		StringBuilder sb = new StringBuilder()
		sb.append("[")
		for (Cookie ck in cookies) {
			sb.append("\"")
			sb.append(printCookie(ck))
			sb.append("\"")
		}
		sb.append("] as Set")
		sb.toString()
	}
	
	private static DateTimeFormatter rfc7231 = 
		DateTimeFormatter
			.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
			.withZone(ZoneId.of("GMT"))
  
	String printCookie(Cookie cookie) {
		StringBuilder sb = new StringBuilder()
		sb.append(cookie.getName())
		sb.append("=")
		sb.append(cookie.getValue())
		sb.append("; ")
		sb.append("expires=")
		ZonedDateTime zdt = cookie.getExpiry().toInstant().atZone(ZoneId.systemDefault())
		sb.append(rfc7231.format(zdt))
		sb.append("; ")
		sb.append("path0")
		sb.append(cookie.getPath())
		sb.append("; ")
		sb.append("domain=")
		sb.append(cookie.domain)
		return sb.toString()
	}
}
