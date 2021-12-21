package com.kazurayam.ks.webdriverfactory.firefox

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.nio.file.Path
import java.nio.file.Paths

import internal.GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.internal.ProfilesIni
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

public class FirefoxDriverFactoryImpl {


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
