import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebDriver incognitoDriver = openChromeBrowserInIncognitoMode()

DriverFactory.changeWebDriver(incognitoDriver)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
TestObject tObj = new TestObject().addProperty('css', ConditionType.EQUALS, '#btn-make-appointment')
WebUI.verifyElementPresent(tObj, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(1)
WebUI.closeBrowser()

/**
 * opens a Chrome browser with nothing special
 * returns the ChromeDriver instance that is assocated with the window
 * @return
 */
ChromeDriver openChromeBrowserPlain() {
	return openChromeBrowser(new ChromeOptions())
}
/**
 * opens a Chrome browser with -incoginito mode,
 * returns the ChromeDriver instance that is associated with the window
 */
ChromeDriver openChromeBrowserInIncognitoMode() {
	ChromeOptions options = new ChromeOptions()
	options.addArguments("-–incognito")
	return openChromeBrowser(options);
}

/**
 * opens a ChromeBrowser with the ChromeOptions given.
 * returns the ChromeDriver instance that is associated with the window
 * @param options
 * @return
 */
ChromeDriver openChromeBrowser(ChromeOptions options) {
	System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
	return new ChromeDriver(options);
}