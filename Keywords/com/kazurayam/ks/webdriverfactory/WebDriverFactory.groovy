package com.kazurayam.ks.webdriverfactory

import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsModifier
import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsModifierHeadless
import com.kazurayam.ks.webdriverfactory.firefox.FirefoxDriverFactoryImpl
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.driver.DriverType
import com.kms.katalon.core.model.FailureHandling

class WebDriverFactory {

	@Keyword
	static WebDriver newWebDriver(DriverType driverType) {
		return newWebDriver(driverType, RunConfiguration.getDefaultFailureHandling())
	}

	@Keyword
	static WebDriver newWebDriver(DriverType driverType, FailureHandling flowControl) {
		switch (driverType.getName()) {
			case DriverTypeName.CHROME_DRIVER.toString() :		// Google Chrome Browser
				return ChromeDriverFactory.newInstance().newChromeDriver(flowControl)
				break
			case DriverTypeName.HEADLESS_DRIVER.toString() :	// Chrome Headless Browser
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
				cdf.addChromeOptionsModifier(com)
				return cdf.newChromeDriver(flowControl)
				break
			// I haven't worked out enough for Firefox yet 
			case DriverTypeName.FIREFOX_DRIVER.toString():		// Firefox Browser
				FirefoxDriverFactoryImpl fdf = FirefoxDriverFactoryImpl.newInstance()
				// FirefoxOptionsModifier is not yet implemented 
				return fdf.createFirefoxDriver()
			default:
				throw new RuntimeException("DriverType ${driverType.getName()} is not supported")
		}
	}

	@Keyword
	static WebDriver newWebDriver(DriverType driverType, String profileName) {
		return newWebDriver(driverType, profileName, RunConfiguration.getDefaultFailureHandling())
	}

	@Keyword
	static WebDriver newWebDriver(DriverType driverType, String profileName, FailureHandling flowControl) {
		switch (driverType.getName()) {
			case DriverTypeName.CHROME_DRIVER.toString() :
				return ChromeDriverFactory.newInstance().newChromeDriverWithProfile(profileName, flowControl)
				break
			case DriverTypeName.HEADLESS_DRIVER.toString() :	// Chrome Headless Browser
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
				cdf.addChromeOptionsModifier(com)
				return cdf.newChromeDriverWithProfile(profileName, flowControl)
				break
			default:
				throw new RuntimeException("DriverType ${driverType.getName()} is not supported")
		}
	}
}
