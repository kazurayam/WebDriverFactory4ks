package com.kms.katalon.core.driver

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.driver.DriverType
import com.kazurayam.ks.webdriverfactory.KSDriverTypeName

@RunWith(JUnit4.class)
public class DriverTypeTest {

	@Test
	void test_getName() {
		DriverType driverType = DriverFactory.getExecutedBrowser()
		String name = driverType.getName()
		println "name is $name"
		assertTrue(
				name.equals(KSDriverTypeName.CHROME_DRIVER.toString()) ||
				name.equals(KSDriverTypeName.HEADLESS_DRIVER.toString()) ||   // should have been named as CHROME_HEADLESS_DRIVER
				name.equals(KSDriverTypeName.FIREFOX_DRIVER.toString()) ||
				name.equals(KSDriverTypeName.FIREFOX_HEADLESS_DRIVER.toString())
				)
	}
}
