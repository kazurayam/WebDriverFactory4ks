package com.kazurayam.ks.webdriverfactory

import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsModifier
import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsModifierHeadless
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
			case 'CHROME_DRIVER' :
				return ChromeDriverFactory.newInstance().openChromeDriver(flowControl)
				break
			case 'HEADLESS_DRIVER' :
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
				cdf.addChromeOptionsModifier(com)
				return cdf.openChromeDriver(flowControl)
				break
			default:
				throw new RuntimeException("${driverType.getName()} is not supported")
		}
	}
	
	@Keyword
	static WebDriver newWebDriver(DriverType driverType, String profileName) {
		return newWebDriver(driverType, profileName, RunConfiguration.getDefaultFailureHandling())
	}

	@Keyword
	static WebDriver newWebDriver(DriverType driverType, String profileName, FailureHandling flowControl) {
		switch (driverType.getName()) {
			case 'CHROME_DRIVER' :
				return ChromeDriverFactory.newInstance().openChromeDriverWithProfile(profileName, flowControl)
				break
			case 'HEADLESS_DRIVER' :
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				ChromeOptionsModifier com = new ChromeOptionsModifierHeadless()
				cdf.addChromeOptionsModifier(com)
				return cdf.openChromeDriverWithProfile(profileName, flowControl)
				break
			default:
				throw new RuntimeException("${driverType.getName()} is not supported")
		}
	}
}
