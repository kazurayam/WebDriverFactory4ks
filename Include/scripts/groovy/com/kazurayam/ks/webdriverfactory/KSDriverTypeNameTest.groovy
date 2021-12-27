package com.kazurayam.ks.webdriverfactory

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.driver.DriverType
import com.kazurayam.ks.webdriverfactory.KSDriverTypeName

@RunWith(JUnit4.class)
public class KSDriverTypeNameTest {

	@Test
	public void test_getDriverType_CHROME_DRIVER() {
		DriverType chrome = (DriverType)KSDriverTypeName.CHROME_DRIVER
		assertEquals(KSDriverTypeName.CHROME_DRIVER.toString(), chrome.getName())
	}

	@Test
	public void test_getDriverType_HEADLESS_DRIVER() {
		DriverType headless = (DriverType)KSDriverTypeName.HEADLESS_DRIVER
		assertEquals(KSDriverTypeName.HEADLESS_DRIVER.toString(), headless.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_DRIVER() {
		DriverType ff = (DriverType)KSDriverTypeName.FIREFOX_DRIVER
		assertEquals(KSDriverTypeName.FIREFOX_DRIVER.toString(), ff.getName())
	}

	@Test
	public void test_getDriverType_FIREFOX_HEADLESS_DRIVER() {
		DriverType ffhl = (DriverType)KSDriverTypeName.FIREFOX_HEADLESS_DRIVER
		assertEquals(KSDriverTypeName.FIREFOX_HEADLESS_DRIVER.toString(), ffhl.getName())
	}

	@Test
	public void test_getDriverType_NULL() {
		DriverType nullObject = (DriverType)KSDriverTypeName.NULL
		assertNotNull(nullObject);
	}
}
