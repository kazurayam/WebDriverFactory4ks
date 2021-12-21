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
		DriverType chrome = (DriverType)DriverTypeName.CHROME_DRIVER
		assertEquals(DriverTypeName.CHROME_DRIVER.toString(), chrome.getName())
	}

	@Test
	public void test_getDriverType_HEADLESS_DRIVER() {
		DriverType headless = (DriverType)DriverTypeName.HEADLESS_DRIVER
		assertEquals(DriverTypeName.HEADLESS_DRIVER.toString(), headless.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_DRIVER() {
		DriverType ff = (DriverType)DriverTypeName.FIREFOX_DRIVER
		assertEquals(DriverTypeName.FIREFOX_DRIVER.toString(), ff.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_HEADLESS_DRIVER() {
		DriverType ffhl = (DriverType)DriverTypeName.FIREFOX_HEADLESS_DRIVER
		assertEquals(DriverTypeName.FIREFOX_HEADLESS_DRIVER.toString(), ffhl.getName())
	}

	@Test
	public void test_getDriverType_NULL() {
		DriverType nullObject = (DriverType)DriverTypeName.NULL
		assertNotNull(nullObject);
	}
}
