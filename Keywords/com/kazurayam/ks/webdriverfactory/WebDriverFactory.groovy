package com.kazurayam.ks.webdriverfactory

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.impl.HeadlessChromeOptionsFilterImpl
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.driver.DriverType

class WebDriverFactory {

	@Keyword
	static WebDriver newWebDriver(DriverType driverType) {
		switch (driverType.getName()) {
			case 'CHROME_DRIVER' :
				return ChromeDriverFactory.newInstance().openChromeDriver()
				break
			case 'HEADLESS_DRIVER' :
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				ChromeOptions co = cdf.getChromeOptions()
				ChromeOptions coHeadless = new HeadlessChromeOptionsFilterImpl().filter(co)
				cdf.setChromeOptions(coHeadless)
				return cdf.openChromeDriver()
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
