package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

import com.kazurayam.ks.webdriverfactory.desiredcapabilities.DesiredCapabilitiesModifier
import com.kms.katalon.core.model.FailureHandling

abstract class ChromeDriverFactory {

	static ChromeDriverFactory newInstance() {
		return new ChromeDriverFactoryImpl()
	}

	abstract void addChromePreferencesModifier(ChromePreferencesModifier chromePreferencesModifier)

	abstract void addChromeOptionsModifier(ChromeOptionsModifier chromeOptionsModifier)

	abstract void addDesiredCapabilitiesModifier(DesiredCapabilitiesModifier desiredCapabilitiesModifier)

	abstract WebDriver newChromeDriver()

	abstract WebDriver newChromeDriver(FailureHandling flowControl)

	abstract WebDriver newChromeDriverWithProfile(String userName)

	abstract WebDriver newChromeDriverWithProfile(String userName, FailureHandling flowControl)

	abstract WebDriver newChromeDriverWithProfileDirectory(String directoryName)

	abstract WebDriver newChromeDriverWithProfileDirectory(String directoryName, FailureHandling flowControl)

	abstract DesiredCapabilities getEmployedDesiredCapabilities()
}
