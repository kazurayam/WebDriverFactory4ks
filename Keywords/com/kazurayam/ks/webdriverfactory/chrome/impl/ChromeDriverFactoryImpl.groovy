package com.kazurayam.ks.webdriverfactory.chrome.impl

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.kazurayam.ks.webdriverfactory.ApplicationInfo
import com.kazurayam.ks.webdriverfactory.Assert
import com.kazurayam.ks.webdriverfactory.OSIdentifier
import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsResolver
import com.kazurayam.ks.webdriverfactory.chrome.ChromePreferencesResolver
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfile
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinder
import com.kazurayam.ks.webdriverfactory.chrome.DesiredCapabilitiesResolver
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonOutput

public class ChromeDriverFactoryImpl extends ChromeDriverFactory {

	static Logger logger_ = LoggerFactory.getLogger(ChromeDriverFactoryImpl.class)

	static {
		// dynamically add toJsonText() method to ChromeOptions class
		ChromeOptions.metaClass.toString = {
			return JsonOutput.prettyPrint(JsonOutput.toJson(delegate.asMap()))
		}
	}

	private ChromePreferencesResolver chromePreferencesResolver_
	private ChromeOptionsResolver chromeOptionsResolver_
	private DesiredCapabilitiesResolver desiredCapabilitiesResolver_

	ChromeDriverFactoryImpl() {
		chromePreferencesResolver_   = new DefaultChromePreferencesResolver()
		chromeOptionsResolver_       = new DefaultChromeOptionsResolver()
		desiredCapabilitiesResolver_ = new DefaultDesiredCapabilitiesResolver()
	}

	ChromePreferencesResolver getChromePreferencesResolver() {
		return this.chromePreferencesResolver_
	}

	ChromeOptionsResolver getChromeOptionsResolver() {
		return this.chromeOptionsResolver_
	}

	DesiredCapabilitiesResolver getDesiredCapabilitiesResolver() {
		return this.desiredCapabilitiesResolver_
	}

	/**
	 * You can inject your favorite Chrome preferences by setting a ChromePreferenceResolver instance
	 *
	 * @param resolver
	 */
	void setChromePreferencesResolver(ChromePreferencesResolver resolver) {
		Objects.requireNonNull(resolver, "ChromePreferencesResolver must not be null")
		this.chromePreferencesResolver_ = resolver
	}

	/**
	 * You can inject your favorites Chrome options by setting a ChromeOptionsResolver instance
	 * @param resolver
	 */
	void setChromeOptionsResolver(ChromeOptionsResolver resolver) {
		Objects.requireNonNull(resolver, "ChromeOptionsResolver must not be null")
		this.chromeOptionsResolver_ = resolver
	}

	/**
	 * You can inject your favorites Desired capabilities by setting a ChromeDesiredCapambilitiesResolver instance
	 */
	void setChromeDesiredCapabilitiesResolver(DesiredCapabilitiesResolver resolver) {
		Objects.requireNonNull(resolver, "DesiredCapabilitiesResolver must not be null")
		this.desiredCapabilitiesResolver_ = resolver
	}

	

	@Override
	WebDriver openChromeDriver() {
		return openChromeDriver(RunConfiguration.getDefaultFailureHandling())
	}


	/**
	 * Open a Chrome browser without specifying Profle
	 * 
	 * @param flowControl
	 * @return
	 */
	@Override
	WebDriver openChromeDriver(FailureHandling flowControl) {
		Objects.requireNonNull(flowControl, "flowControl must not be null")
		//
		enableChromeDriverLog(Paths.get(RunConfiguration.getProjectDir()).resolve('tmp'))
		//
		Path chromeDriverPath = ChromeDriverFactoryImpl.getChromeDriverPath()
		System.setProperty('webdriver.chrome.driver', chromeDriverPath.toString())
		//
		Map<String, Object> chromePreferences = chromePreferencesResolver_.resolveChromePreferences()
		ChromeOptions chromeOptions = chromeOptionsResolver_.resolveChromeOptions(chromePreferences)
		//
		DesiredCapabilities cap = desiredCapabilitiesResolver_.resolveDesiredCapabilities(chromeOptions)
		WebDriver driver = new ChromeDriver(cap)
		return driver
	}

	@Override
	WebDriver openChromeDriverWithProfile(String userName) {
		return openChromeDriverWithProfile(userName, RunConfiguration.getDefaultFailureHandling())
	}

	/**
	 * Based on the post https://forum.katalon.com/t/open-browser-with-custom-profile/19268 by Thanh To
	 *
	 * Chrome's User Data directory is OS dependent. The User Data Diretory is described in the document
	 * https://chromium.googlesource.com/chromium/src/+/HEAD/docs/user_data_dir.md#Current-Location
	 *
	 * @param userProfile
	 * @param flowControl
	 * @return
	 */
	@Override
	WebDriver openChromeDriverWithProfile(String userName, FailureHandling flowControl) {
		Objects.requireNonNull(userName, "userName must not be null")
		Objects.requireNonNull(flowControl, "flowControl must not be null")
		//
		enableChromeDriverLog(Paths.get(RunConfiguration.getProjectDir()).resolve('tmp'))
		//
		Path chromeDriverPath = ChromeDriverFactoryImpl.getChromeDriverPath()
		System.setProperty('webdriver.chrome.driver', chromeDriverPath.toString())
		//
		Path profileDirectory = ChromeDriverFactoryImpl.getChromeProfileDirectory(userName)
		//
		if (profileDirectory != null) {
			if (Files.exists(profileDirectory) && profileDirectory.toFile().canWrite()) {
				Map<String, Object> chromePreferences = chromePreferencesResolver_.resolveChromePreferences()
				ChromeOptions chromeOptions = chromeOptionsResolver_.resolveChromeOptions(chromePreferences)

				// use the Profile as specified
				Path userDataDirectory = ChromeDriverFactoryImpl.getChromeUserDataDirectory()
				chromeOptions.addArguments("user-data-dir=" + userDataDirectory.toString())
				chromeOptions.addArguments("profile-directory=${profileDirectory.getFileName().toString()}")
				KeywordUtil.logInfo("#openChromeDriver chromeOptions=" + chromeOptions.toString())

				DesiredCapabilities cap = desiredCapabilitiesResolver_.resolveDesiredCapabilities(chromeOptions)
				WebDriver driver = new ChromeDriver(cap)
				return driver
			} else {
				Assert.stepFailed("Profile directory \"${profileDirectory.toString()}\" is not present", flowControl)
			}
		} else {
			Assert.stepFailed("Profile directory for userName \"${userName}\" is not found." +
					"\n" + ChromeProfileFinder.listChromeProfiles()
					)
		}
	}

	
	/**
	 * Usage:
	 * <PRE>
	 * import com.kazurayam.webdriverfactory4ks.ChromeDriverFactory
	 * import import org.openqa.selenium.WebDriver
	 * import com.kms.katalon.core.webui.driver.DriverFactory
	 * 
	 * ChromeDriverFactory cdFactory = new ChromeDriverFactory()
	 *
	 * // open Chrome browser with the profile stored in the directory 'User Data\Default'
	 * WebDriver driver = cdFactory.openChromeDriverWithProfileDirectory('Default')
	 * DriverFactory.changeWebDriver(driver)
	 * WebUI.navigateToUrl('http://demoaut.katalon.com/')
	 * WebUI.delay(3)
	 * WebUI.closeBrowser()
	 * </PRE>
	 */
	@Override
	WebDriver openChromeDriverWithProfileDirectory(String directoryName) {
		return openChromeDriverWithProfileDirectory(directoryName, RunConfiguration.getDefaultFailureHandling())
	}

	@Override
	WebDriver openChromeDriverWithProfileDirectory(String directoryName, FailureHandling flowControl) {
		Objects.requireNonNull(directoryName, "directoryName must not be null")
		Path userDataDir = ChromeDriverFactoryImpl.getChromeUserDataDirectory()
		if (userDataDir != null) {
			if (Files.exists(userDataDir)) {
				Path profileDirectory = userDataDir.resolve(directoryName)
				if (Files.exists(profileDirectory)) {
					ChromeProfile chromeProfile = ChromeProfileFinder.getChromeProfileByDirectoryName(profileDirectory)
					return openChromeDriverWithProfile(chromeProfile.getName(), flowControl)
				}
			} else {
				throw new FileNotFoundException("${userDataDir} is not found")
			}
		} else {
			throw new IllegalStateException("unable to identify the User Data Directory of Chrome browser")
		}
	}
}