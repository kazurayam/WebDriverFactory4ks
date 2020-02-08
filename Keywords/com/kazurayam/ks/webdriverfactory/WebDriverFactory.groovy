package com.kazurayam.ks.webdriverfactory

import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.driver.DriverType

class WebDriverFactory {

	@Keyword
	static WebDriver newWebDriver(DriverType driverType) {
		switch (driverType.getName()) {
			case 'CHROME_DRIVER' :
				return ChromeDriverFactory.newInstance().openChromeDriver()
				break
			default:
				throw new RuntimeException("${driverType.getName()} is not supported")
		}
	}

	@Keyword
	static WebDriver newWebDriver(DriverType driverType, String profileName) {
		switch (driverType.getName()) {
			case 'CHROME_DRIVER' :
				return ChromeDriverFactory.newInstance().openChromeDriverWithProfile(profileName)
				break
			default:
				throw new RuntimeException("${driverType.getName()} is not supported")
		}
	}
}
