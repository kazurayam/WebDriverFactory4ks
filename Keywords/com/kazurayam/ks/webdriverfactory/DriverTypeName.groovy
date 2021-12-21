package com.kazurayam.ks.webdriverfactory

import com.kms.katalon.core.driver.DriverType

/**
 * 
 * https://docs.katalon.com/javadoc/com/kms/katalon/core/driver/DriverType.html
 * 
 * @author kazurayam
 */
enum DriverTypeName implements DriverType {

	CHROME_DRIVER,
	HEADLESS_DRIVER,	// I believe, this should have been named as CHROME_HEADLESS_DRIVER
	FIREFOX_DRIVER,
	FIREFOX_HEADLESS_DRIVER,
	NULL;

	String getName() {
		return this.name()
	}
	String getPropertyKey() {
		throw new RuntimeException("TODO")
	}
	String getPropertyValue() {
		throw new RuntimeException("TODO")
	}
}
