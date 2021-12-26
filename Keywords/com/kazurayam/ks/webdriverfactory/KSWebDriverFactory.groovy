package com.kazurayam.ks.webdriverfactory

import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory.UserProfile
import com.kazurayam.webdriverfactory.WebDriverFactory
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.ChromeOptionsModifiers
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory.UserDataAccess
import com.kazurayam.webdriverfactory.utils.OSIdentifier
import com.kms.katalon.core.driver.DriverType

class KSWebDriverFactory {

	private static String employedDC = ""

	static WebDriver newWebDriver(DriverTypeName driverTypeName) {
		return newWebDriver((DriverType)driverTypeName)
	}

	static WebDriver newWebDriver(DriverType driverType) {
		setPathsToDriverExecutables()
		switch (driverType.getName()) {
			case DriverTypeName.CHROME_DRIVER.toString() :		// Google Chrome Browser
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				employedDC = cdf.getEmployedDesiredCapabilitiesAsJSON()
				return cdf.newChromeDriver()
				break
			case DriverTypeName.HEADLESS_DRIVER.toString() :	// Chrome Headless Browser
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				cdf.addChromeOptionsModifier(ChromeOptionsModifiers.headless())
				employedDC = cdf.getEmployedDesiredCapabilitiesAsJSON()
				return cdf.newChromeDriver()
				break
			default:
				throw new RuntimeException("DriverType ${driverType.getName()} is not supported")
		}
	}

	static WebDriver newWebDriver(DriverTypeName driverTypeName, String userProfile) {
		return newWebDriver((DriverType)driverTypeName, userProfile)
	}


	static WebDriver newWebDriver(DriverType driverType, String userProfile) {
		return newWebDriver(driverType, userProfile, UserDataAccess.TO_GO)    // UserDataAccess.FOR_HERE or .TO_GO
	}

	static WebDriver newWebDriver(DriverType driverType, String userProfile, UserDataAccess instruction) {
		setPathsToDriverExecutables()
		switch (driverType.getName()) {
			case DriverTypeName.CHROME_DRIVER.getName() :
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				employedDC = cdf.getEmployedDesiredCapabilitiesAsJSON()
				return cdf.newChromeDriver(new UserProfile(userProfile), instruction)
				break
			case DriverTypeName.HEADLESS_DRIVER.getName() :	// Chrome Headless Browser
				ChromeDriverFactory cdf = ChromeDriverFactory.newInstance()
				cdf.addChromeOptionsModifier(ChromeOptionsModifiers.headless())
				employedDC = cdf.getEmployedDesiredCapabilitiesAsJSON()
				return cdf.newChromeDriver(new UserProfile(userProfile), instruction)
				break
			default:
				throw new RuntimeException("DriverType ${driverType.getName()} is not supported")
		}
	}


	/**
	 * 
	 */
	static void setPathsToDriverExecutables() {
		System.setProperty("webdriver.chrome.driver", getChromeDriverPath().toString())
	}

	/**
	 * returns the path of the binary of Chrome Driver bundled in Katalon Studio
	 */
	static Path getChromeDriverPath() {
		ApplicationInfo appInfo = new ApplicationInfo()
		String katalonHome = appInfo.getKatalonHome()
		if (OSIdentifier.isWindows()) {
			return Paths.get(katalonHome).resolve('configuration').
					resolve('resources').resolve('drivers').
					resolve('chromedriver_win32').resolve('chromedriver.exe')
		} else if (OSIdentifier.isMac()) {
			return Paths.get(katalonHome).resolve('Contents').
					resolve('Eclipse').resolve('Configuration').
					resolve('resources').resolve('drivers').
					resolve('chromedriver_mac').resolve('chromedriver')
		} else if (OSIdentifier.isUnix()) {
			throw new UnsupportedOperationException("TODO")
		} else {
			throw new IllegalStateException(
			"Windows, Mac, Linux are supported. Other platforms are not supported.")
		}
	}
}
