package com.kazurayam.ks.webdriverfactory.firefox

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.kazurayam.ks.webdriverfactory.desiredcapabilities.DesiredCapabilitiesModifier
import com.kazurayam.ks.webdriverfactory.desiredcapabilities.DesiredCapabilitiesBuilderImpl

import groovy.json.JsonOutput
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling

public class FirefoxDriverFactoryImpl extends FirefoxDriverFactory {

	static Logger logger_ = LoggerFactory.getLogger(FirefoxDriverFactoryImpl.class)

	static {
		// dynamically add toJsonText() method to the built-in classes
		DesiredCapabilities.metaClass.toString = {
			return JsonOutput.prettyPrint(JsonOutput.toJson(delegate.asMap()))
		}
	}

	private final List<FirefoxPreferencesModifier> firefoxPreferencesModifiers_
	private final List<FirefoxOptionsModifier> firefoxOptionsModifiers_
	private final List<DesiredCapabilitiesModifier> desiredCapabilitiesModifiers_

	private DesiredCapabilities desiredCapabilities_

	FirefoxDriverFactoryImpl() {
		firefoxPreferencesModifiers_  = new ArrayList<>();
		firefoxOptionsModifiers_      = new ArrayList<>();
		desiredCapabilitiesModifiers_ = new ArrayList<>();
		desiredCapabilities_ = null;
	}

	@Override
	void addFirefoxPreferencesModifier(FirefoxPreferencesModifier firefoxPreferencesModifier) {
		firefoxPreferencesModifiers_.add(firefoxPreferencesModifier)
	}

	@Override
	void addFirefoxOptionsModifier(FirefoxOptionsModifier firefoxOptionsModifier) {
		firefoxOptionsModifiers_.add(firefoxOptionsModifier)
	}

	@Override
	void addDesiredCapabilitiesModifier(DesiredCapabilitiesModifier desiredCapabilitiesModifier) {
		desiredCapabilitiesModifiers_.add(desiredCapabilitiesModifier)
	}

	/**
	 * 1. enable logging by FirefoxDriver into the tmp directory under the Katalon Studio Project directory
	 * 2. ensure the path of FirefoxDriver executable
	 */
	private void prepare() {
		FirefoxDriverUtils.enableFirefoxDriverLog(Paths.get(RunConfiguration.getProjectDir()).resolve('tmp'))
		Path firefoxDriverPath = FirefoxDriverUtils.getFirefoxDriverPath()
		System.setProperty('webdriver.gecko.driver', firefoxDriverPath.toString())

		this.addFirefoxPreferencesModifier(new FirefoxPreferencesModifierDefault())
		this.addFirefoxOptionsModifier(new FirefoxOptionsModifierDefault())
	}

	/**
	 * Create an instance of Gecko Driver with configuration
	 * setup through the chain of
	 *     Firefox Prefereces => Firefox Options => Desired Capabilities
	 * while modifying each containers with specified Modifiers
	 */
	private WebDriver execute() {
		// create a Firefox Preferences object as the seed
		Map<String, Object> firefoxPreferences = new HashMap<>()

		// modify the Chrome Preferences
		for (FirefoxPreferencesModifier modifier in firefoxPreferencesModifiers_) {
			firefoxPreferences = modifier.modify(firefoxPreferences)
		}

		// create Firefox Options taking over the Firefox Preferences
		FirefoxOptions firefoxOptions = new FirefoxOptionsBuilderImpl().build(firefoxPreferences)
		// modify the Firefox Options
		for (FirefoxOptionsModifier modifier in firefoxOptionsModifiers_) {
			firefoxOptions = modifier.modify(firefoxOptions)
		}

		// create Desired Capabilities taking over settings in the Chrome Options
		desiredCapabilities_ = new DesiredCapabilitiesBuilderImpl().build(firefoxOptions)
		// modify the Desired Capabilities
		for (DesiredCapabilitiesModifier dcm in desiredCapabilitiesModifiers_) {
			desiredCapabilities_ = dcm.modify(desiredCapabilities_)
		}

		// now launch the browser
		WebDriver driver = new FirefoxDriver(desiredCapabilities_)

		// well done
		return driver
	}

	@Override
	WebDriver newFirefoxDriver() {
		return newFirefoxDriver(RunConfiguration.getDefaultFailureHandling())
	}

	@Override
	WebDriver newFirefoxDriver(FailureHandling flowControl) {
		Objects.requireNonNull(flowControl)
		this.prepare()
		return this.execute()
	}

	@Override
	WebDriver newFirefoxDriverWithProfile(String profileName) {
		return newFirefoxDriverWithProfile(profileName,
				RunConfiguration.getDefaultFailureHandling())
	}


	WebDriver newFirefoxDriverWithProfile(String profileName,
			FailureHandling flowControl) {
		Objects.requireNonNull(profileName, "profileName must not be null")
		Objects.requireNonNull(flowControl, "flowControl must not be null")
		//
		this.prepare()
		//
		throw new RuntimeException("TODO")
	}


	/**
	 * returns the DesiredCapabilitiy object employed when the factory instantiated ChromeDriver by calling execute().
	 * If you call this before calling execute(), null will be returned.
	 */
	@Override
	DesiredCapabilities getEmployedDesiredCapabilities() {
		return this.desiredCapabilities_
	}





	/**
	 * 
	 * @return
	 */
	static FirefoxDriver createFirefoxDriver() {
		/**
		 * see https://stackoverflow.com/questions/36309314/set-firefox-profile-to-download-files-automatically-using-selenium-and-java
		 *
		 * see https://developer.mozilla.org/en-US/docs/Archive/Mozilla/Download_Manager_preferences
		 * - browser.download.useDownloadDir : A boolean value that indicates whether or not the user's preference is to automatically save files into the download directory. If this value is false, the user is asked what to do. In Thunderbird and SeaMonkey the default is false. In Other Applications the default is true.
		 * - browser.download.folderList : Indicates the default folder to download a file to. 0 indicates the Desktop; 1 indicates the systems default downloads location; 2 indicates a custom (see: browser.download.dir) folder.
		 * - browser.download.dir : A local folder the user may have selected for downloaded files to be saved. Migration of other browser settings may also set this path. This folder is enabled when browser.download.folderList equals 2.
		 * - browser.download.manager.showWhenStarting : A boolean value that indicates whether or not to show the Downloads window when a download begins. The default value is true.
		 *
		 * - browser.helperApps.neverAsk.saveToDisk :
		 *
		 */
		FirefoxProfile profile = new FirefoxProfile()

		// set location to store files after downloading
		profile.setPreference("browser.download.useDownloadDir", true)
		profile.setPreference("browser.download.folderList", 2)
		Path downloads = Paths.get(System.getProperty('user.home'), 'Downloads')
		profile.setPreference("browser.download.dir", downloads.toString())

		// set preference not to show file download confirmation dialog
		def mimeTypes = getAllMimeTypesAsString()
		//println "mimeTypes=${mimeTypes}"
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", mimeTypes)
		profile.setPreference("browser.helperApps.neverAsk.openFile", mimeTypes)

		// profile.setPreference("browser.download.manager.showWhenStarting", false) // you can not modify this particular profile any more
		profile.setPreference("pdfjs.disable", true)

		FirefoxOptions options = new FirefoxOptions()
		options.setProfile(profile)
		return new FirefoxDriver(options)
	}

	private static String getAllMimeTypesAsString() {
		return [
			"application/gzip",
			"application/java-archive",
			"application/json",
			"application/msexcel",
			"application/msword",
			"application/octet-stream",
			"application/pdf",
			"application/vnd-ms-office",
			"application/vnd-xls",
			"application/vnd.ms-excel",
			"application/vnd.ms-powerpoint",
			"application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/x-dos_mx_excel",
			"application/x-excel",
			"application/x-ms-excel",
			"application/x-msexcel",
			"application/x-tar",
			"application/x-xls",
			"application/x-zip-compressed",
			"application/xls",
			"application/xml",
			"application/zip",
			"application/zlib",
			"image/bmp",
			"image/gif",
			"image/jpeg",
			"image/png",
			"image/svg+xml",
			"text/csv",
			"text/plain",
			"text/xml"
		].join(",")
	}
}
