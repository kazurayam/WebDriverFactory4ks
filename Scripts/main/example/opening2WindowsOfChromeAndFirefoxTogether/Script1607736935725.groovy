import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.WebDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens 3 Browser windows, that is
 * 
 * 1. The browser you have chosen in GUI
 * 2. Google Chrome
 * 3. Firefox 
 * 
 */
String chosenBrowser = DriverFactory.getExecutedBrowser()
println("chosenBrowser=${chosenBrowser}")   // "Chrome", "Firefox", "Safari", "Edge Chromium", "Chrome (headless)", "Firefox (headless)"

String url = "http://demoaut.katalon.com"

// open 1st browser window
WebUI.openBrowser(url)

/*
WebDriver driver = WebDriverFactory.newWebDriver(chosenBrowser)
assert driver != null
DriverFactory.changeWebDriver(driver)	// Do not forget this

WebUI.comment("${DriverFactory.getExecutedBrowser()} has been opend with profile Katalon")
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
*/

// close the 1st browser window
WebUI.close