package com.kazurayam.ks.webdriverfactory

import com.kms.katalon.core.driver.DriverType

/**
 * 
 * https://docs.katalon.com/javadoc/com/kms/katalon/core/driver/DriverType.html
 * 
 * @author kazurayam
 */
enum DriverTypeName {

	CHROME_DRIVER,
	HEADLESS_DRIVER,	// I believe, this should have been named as CHROME_HEADLESS_DRIVER
	FIREFOX_DRIVER,
	FIREFOX_HEADLESS_DRIVER,
	NULL;

	public DriverType getDriverType() {
		if (this.toString() == "NULL") {
			throw new UnsupportedOperationException("DriverTypeName.NULL.getDriverType() should not be used")
		}
		return new DriverTypeImpl(this.toString())
	}

	/**
	 * Surprising enough, com.kms.katalon.core.driver.DriverType is not an "enum",
	 * is an "interface". Therefore I need to write a skeltal class that implements it.
	 */
	class DriverTypeImpl implements DriverType {
		private String name
		DriverTypeImpl(String name) {
			this.name = name
		}
		String getName() {
			return this.name
		}
		String getPropertyKey() {
			throw new RuntimeException("TODO")
		}
		String getPropertyValue() {
			throw new RuntimeException("TODO")
		}
	}
}
