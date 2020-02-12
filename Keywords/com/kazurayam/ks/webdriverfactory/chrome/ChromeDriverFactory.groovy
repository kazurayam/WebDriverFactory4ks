package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.desiredcapabilities.DesiredCapabilitiesModifier
import com.kms.katalon.core.model.FailureHandling

abstract class ChromeDriverFactory {

	static ChromeDriverFactory newInstance() {
		return new ChromeDriverFactoryImpl()
	}
	
	abstract void addChromePreferencesModifier(ChromePreferencesModifier chromePreferencesModifier)
	
	abstract void addChromeOptionsModifier(ChromeOptionsModifier chromeOptionsModifier)
	
	abstract void addDesiredCapabilitiesModifier(DesiredCapabilitiesModifier desiredCapabilitiesModifier)

	abstract WebDriver openChromeDriver()

	abstract WebDriver openChromeDriver(FailureHandling flowControl)

	abstract WebDriver openChromeDriverWithProfile(String userName)

	abstract WebDriver openChromeDriverWithProfile(String userName, FailureHandling flowControl)

	abstract WebDriver openChromeDriverWithProfileDirectory(String directoryName)

	abstract WebDriver openChromeDriverWithProfileDirectory(String directoryName, FailureHandling flowControl)
}
