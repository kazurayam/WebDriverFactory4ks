package com.kazurayam.ks.webdriverfactory.firefox

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

import com.kazurayam.ks.webdriverfactory.desiredcapabilities.DesiredCapabilitiesModifier
import com.kms.katalon.core.model.FailureHandling


abstract class FirefoxDriverFactory {

	static FirefoxDriverFactory newInstance() {
		return new FirefoxDriverFactoryImpl()
	}

	abstract void addFirefoxPreferencesModifier(FirefoxPreferencesModifier firefoxPreferecesModifier)

	abstract void addFirefoxOptionsModifier(FirefoxOptionsModifier firefoxOptionsModifier)

	abstract void addDesiredCapabilitiesModifier(DesiredCapabilitiesModifier desiredCapabilityModifier)

	abstract WebDriver newFirefoxDriver()

	abstract WebDriver newFirefoxDriver(FailureHandling flowControl)

	abstract WebDriver newFirefoxDriverWithProfile(String userName)

	abstract WebDriver newFirefoxDriverWithProfile(String userName, FailureHandling flowControl)

	abstract DesiredCapabilities getEmployedDesiredCapabilities()
}
