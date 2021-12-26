package com.kazurayam.ks.webdriverfactory

import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory.DriverTypeName
import com.kazurayam.webdriverfactory.UserProfile
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.ChromeOptionsModifier
import com.kazurayam.webdriverfactory.chrome.ChromeOptionsModifiers
import com.kazurayam.webdriverfactory.chrome.ChromePreferencesModifier
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory.UserDataAccess
import com.kazurayam.webdriverfactory.desiredcapabilities.DesiredCapabilitiesModifier
import com.kazurayam.webdriverfactory.utils.OSIdentifier


class KSWebDriverFactory {

	private DriverTypeName driverTypeName
	private UserProfile userProfile
	private UserDataAccess userDataAccess
	private List<ChromePreferencesModifier> chromePreferencesModifierList
	private List<ChromeOptionsModifier> chromeOptionsModifierList
	private List<DesiredCapabilitiesModifier> desiredCapabilitiesModifierList
	private boolean requireDefaultSettings

	private String employedDesiredCapabilities

	private KSWebDriverFactory(Builder builder) {
		this.driverTypeName = builder.driverTypeName
		this.userProfile = builder.userProfile
		this.userDataAccess = builder.userDataAccess
		this.chromePreferencesModifierList = builder.chromePreferencesModifierList
		this.chromeOptionsModifierList = builder.chromeOptionsModifierList
		this.desiredCapabilitiesModifierList = builder.desiredCapabilitiesModifierList
		this.requireDefaultSettings = builder.requireDefaultSettings
		this.employedDesiredCapabilities = ''
	}

	String getEmployedDesiredCapabilities() {
		return this.employedDesiredCapabilities
	}

	WebDriver newWebDriver() {
		//
		setPathsToDriverExecutables()
		//
		if (this.userProfile == UserProfile.NULL) {
			// when UserProfile is not specified
			switch (driverTypeName) {
				case DriverTypeName.CHROME_DRIVER :		// Google Chrome Browser
					ChromeDriverFactory cdf = ChromeDriverFactory.newInstance(this.requireDefaultSettings)
					cdf.addAllChromePreferencesModifiers(this.chromePreferencesModifierList)
					cdf.addAllChromeOptionsModifiers(this.chromeOptionsModifierList)
					cdf.addAllDesiredCapabilitiesModifiers(this.desiredCapabilitiesModifierList)
					WebDriver driver = cdf.newChromeDriver()
					this.employedDesiredCapabilities =  cdf.getEmployedDesiredCapabilitiesAsJSON()
					return driver
					break
				case DriverTypeName.HEADLESS_DRIVER :	// Chrome Headless Browser
					ChromeDriverFactory cdf = ChromeDriverFactory.newInstance(this.requireDefaultSettings)
				//
					cdf.addChromeOptionsModifier(ChromeOptionsModifiers.headless())  // make it headless
					cdf.addAllChromePreferencesModifiers(this.chromePreferencesModifierList)
					cdf.addAllChromeOptionsModifiers(this.chromeOptionsModifierList)
					cdf.addAllDesiredCapabilitiesModifiers(this.desiredCapabilitiesModifierList)
					WebDriver driver = cdf.newChromeDriver()
					this.employedDesiredCapabilities =  cdf.getEmployedDesiredCapabilitiesAsJSON()
					return driver
					break
				default:
					throw new RuntimeException("DriverTypeName ${driverTypeName} is not supported")
			}
		} else {
			// when some UserProfile is specified
			switch (driverTypeName) {
				case DriverTypeName.CHROME_DRIVER :
					ChromeDriverFactory cdf = ChromeDriverFactory.newInstance(this.requireDefaultSettings)
					cdf.addAllChromePreferencesModifiers(this.chromePreferencesModifierList)
					cdf.addAllChromeOptionsModifiers(this.chromeOptionsModifierList)
					cdf.addAllDesiredCapabilitiesModifiers(this.desiredCapabilitiesModifierList)
					WebDriver driver = cdf.newChromeDriver(userProfile)
					this.employedDesiredCapabilities =  cdf.getEmployedDesiredCapabilitiesAsJSON()
					return driver
					break
				case DriverTypeName.HEADLESS_DRIVER :
					ChromeDriverFactory cdf = ChromeDriverFactory.newInstance(this.requireDefaultSettings)
				//
					cdf.addChromeOptionsModifier(ChromeOptionsModifiers.headless())  // make it headless
					cdf.addAllChromePreferencesModifiers(this.chromePreferencesModifierList)
					cdf.addAllChromeOptionsModifiers(this.chromeOptionsModifierList)
					cdf.addAllDesiredCapabilitiesModifiers(this.desiredCapabilitiesModifierList)
					WebDriver driver = cdf.newChromeDriver(userProfile)
					this.employedDesiredCapabilities =  cdf.getEmployedDesiredCapabilitiesAsJSON()
					return driver
					break
				default:
					throw new RuntimeException("DriverTypeName ${driverTypeName} is not supported")
			}
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
		//
		ApplicationInfo appInfo = new ApplicationInfo()
		//
		String katalonHome = appInfo.getKatalonHome()
		//
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


	/**
	 *
	 */
	static class Builder {
		private DriverTypeName driverTypeName
		private UserProfile userProfile = UserProfile.NULL
		private UserDataAccess userDataAccess = UserDataAccess.TO_GO
		private List<ChromePreferencesModifier> chromePreferencesModifierList = new ArrayList<>()
		private List<ChromeOptionsModifier> chromeOptionsModifierList = new ArrayList<>()
		private List<DesiredCapabilitiesModifier> desiredCapabilitiesModifierList = new ArrayList<>()
		private Boolean requireDefaultSettings = true
		
		Builder(DriverTypeName driverTypeName) {
			this.driverTypeName = driverTypeName
		}
		Builder userProfile(String userProfile) {
			this.userProfile = new UserProfile(userProfile)
			return this
		}
		Builder userProfile(UserProfile userProfile) {
			this.userProfile = userProfile
			return this
		}
		Builder userDataAccess(UserDataAccess instruction) {
			this.userDataAccess = instruction
			return this
		}
		Builder addChromePreferencesModifier(ChromePreferencesModifier modifier) {
			this.chromePreferencesModifierList.add(modifier)
			return this
		}
		Builder addChromeOptionsModifier(ChromeOptionsModifier modifier) {
			this.chromeOptionsModifierList.add(modifier)
			return this
		}
		Builder addDesiredCapabilitiesModifier(DesiredCapabilitiesModifier modifier) {
			this.desiredCapabilitiesModifierList.add(modifier)
			return this
		}
		Builder requireDefaultSettings(Boolean requireDefaultSettings) {
			this.requireDefaultSettings = requireDefaultSettings
			return this
		}
		KSWebDriverFactory build() {
			return new KSWebDriverFactory(this)
		}
	}

}
