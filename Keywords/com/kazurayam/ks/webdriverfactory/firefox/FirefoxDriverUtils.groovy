package com.kazurayam.ks.webdriverfactory.firefox

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

import internal.GlobalVariable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import com.kazurayam.ks.webdriverfactory.config.ApplicationInfo
import com.kazurayam.ks.webdriverfactory.utils.OSIdentifier

public class FirefoxDriverUtils {

	/**
	 * 
	 * @param logsDir
	 */
	static void enableFirefoxDriverLog(Path logsDir) {
		Files.createDirectories(logsDir)
		Path firefoxDriverLog = logsDir.resolve('firefoxdriver.log')
		System.setProperty('webdriver.gecko.logfile', firefoxDriverLog.toString())
		System.setProperty('webdriver.gecko.verboseLogging', "true")
	}

	/**
	 * 
	 * @return the path of the binary of Firefox browser
	 */
	static Path getFirefoxBinaryPath() {
		if (OSIdentifier.isWindows()) {
			throw new RuntimeException("TODO")
		} else if (OSIdentifier.isMac()) {
			return Paths.get('/Applications/Firefox.app/Contents/MacOS/firefox')
		} else if (OSIdentifier.isUnix()) {
			return Paths.get('/usr/bin/firefox')
		} else {
			throw new IllegalStateException(
			"Windows, Mac, Linux are supported. Other platforms are not supported.")
		}
	}

	static Path getFirefoxDriverPath() {
		ApplicationInfo appInfo = new ApplicationInfo()
		String katalonHome = appInfo.getKatalonHome()
		if (OSIdentifier.isWindows()) {
			throw new RuntimeException("TODO")
		} else if (OSIdentifier.isMac()) {
			return Paths.get(katalonHome).resolve('Contents').
					resolve('Eclipse').resolve('configuration').
					resolve('resources').resolve('drivers').
					resolve('firefox_mac').resolve('geckodriver')
		} else if (OSIdentifier.isUnix()) {
			throw new UnsupportedOperationException("TODO")
		} else {
			throw new IllegalStateException(
			"Windows, Mac, Linux are supported. Other platforms are not supported.")
		}
	}
}
