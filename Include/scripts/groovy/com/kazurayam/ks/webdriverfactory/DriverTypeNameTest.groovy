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
public class DriverTypeNameTest {

	@Test
	public void test_getDriverType_CHROME_DRIVER() {
		DriverType chrome = DriverTypeName.CHROME_DRIVER.getDriverType()
		assertEquals(DriverTypeName.CHROME_DRIVER.toString(), chrome.getName())
	}

	@Test
	public void test_getDriverType_HEADLESS_DRIVER() {
		DriverType headless = DriverTypeName.HEADLESS_DRIVER.getDriverType()
		assertEquals(DriverTypeName.HEADLESS_DRIVER.toString(), headless.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_DRIVER() {
		DriverType ff = DriverTypeName.FIREFOX_DRIVER.getDriverType()
		assertEquals(DriverTypeName.FIREFOX_DRIVER.toString(), ff.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_HEADLESS_DRIVER() {
		DriverType ffhl = DriverTypeName.FIREFOX_HEADLESS_DRIVER.getDriverType()
		assertEquals(DriverTypeName.FIREFOX_HEADLESS_DRIVER.toString(), ffhl.getName())
	}

}
