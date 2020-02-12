package com.kazurayam.ks.webdriverfactory.chrome

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

import com.kazurayam.ks.webdriverfactory.config.ApplicationInfo
import com.kazurayam.ks.webdriverfactory.utils.OSIdentifier
import com.kms.katalon.core.model.FailureHandling

abstract class ChromeDriverFactory {

	static ChromeDriverFactory newInstance() {
		return new ChromeDriverFactoryImpl()
	}

	abstract WebDriver openChromeDriver()

	abstract WebDriver openChromeDriver(FailureHandling flowControl)

	abstract WebDriver openChromeDriverWithProfile(String userName)

	abstract WebDriver openChromeDriverWithProfile(String userName, FailureHandling flowControl)

	abstract WebDriver openChromeDriverWithProfileDirectory(String directoryName)

	abstract WebDriver openChromeDriverWithProfileDirectory(String directoryName, FailureHandling flowControl)
}
