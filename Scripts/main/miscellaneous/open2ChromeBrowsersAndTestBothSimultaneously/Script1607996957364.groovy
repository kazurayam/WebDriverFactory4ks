import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject button = new TestObject().addProperty('css', ConditionType.EQUALS, '#btn-make-appointment')

// open 2 Chrome browser, one in normal mode on the left, another in incognito mode on the right
WebDriver normalChrome = openChromeBrowserPlain()
resizeHorizontalHalfLocateLeft(normalChrome)
DriverFactory.changeWebDriver(normalChrome)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.waitForPageLoad(10)

WebDriver incognitoChrome = openChromeBrowserInIncognitoMode()
resizeHorizontalHalfLocateRight(incognitoChrome)
DriverFactory.changeWebDriver(incognitoChrome)
WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/')
WebUI.waitForPageLoad(10)

// in the normal Chrome, do something
DriverFactory.changeWebDriver(normalChrome)
WebUI.comment("switched to ${WebUI.getUrl()}")
WebUI.verifyElementPresent(button, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(button)
WebUI.delay(1)

// in the incoginto Chrome, do something
DriverFactory.changeWebDriver(incognitoChrome)
WebUI.comment("switched to ${WebUI.getUrl()}")
WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/')
WebUI.verifyElementPresent(button, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(button)
WebUI.delay(1)

// close 2 browser windows
DriverFactory.changeWebDriver(normalChrome)
WebUI.closeBrowser()
WebUI.delay(1)
DriverFactory.changeWebDriver(incognitoChrome)
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
	options.addArguments("–incognito")
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

/**
 * resize the browser window to horizontal half, and move it to the right side
 * @param driver
 * @returns Dimension of the window
 */
Dimension resizeHorizontalHalfLocateLeft(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(new Point(0, 0));
	return d
}

/**
 * resize the browser window to horizontal half, and move it to the left side
 * 
 * @param driver
 * @returns Dimension of the window
 */
Dimension resizeHorizontalHalfLocateRight(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(new Point(d.getWidth(), 0))
	return d
}

/**
 * resize the browser window to half-width tile;
 * width=half of full screen, height=height of full screen
 * 
 * @param driver
 * @return
 */
Dimension resizeToHorizontalHalf(WebDriver driver) {
	driver.manage().window().maximize()
	Dimension maxDim = driver.manage().window().getSize()
	Dimension curDim = new Dimension((Integer)(maxDim.getWidth() / 2), maxDim.getHeight())
	driver.manage().window().setSize(curDim)
	return curDim
}

