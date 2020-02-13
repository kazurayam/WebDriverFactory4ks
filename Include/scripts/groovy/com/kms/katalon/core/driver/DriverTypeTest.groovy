package com.kms.katalon.core.driver

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.driver.DriverType

@RunWith(JUnit4.class)
public class DriverTypeTest {

	@Test
	void test_getName() {
		DriverType driverType = DriverFactory.getExecutedBrowser()
		String name = driverType.getName()
		println "name is $name"
		assertTrue(
				name.equals('CHROME_DRIVER') ||
				name.equals('HEADLESS_DRIVER') ||   // should have been named as CHROME_HEADLESS_DRIVER
				name.equals('FIREFOX_DRIVER') ||
				name.equals('FIREFOX_HEADLESS_DRIVER') ||
				name.equals('SAFARI_DRIVER')
				)
	}
}
