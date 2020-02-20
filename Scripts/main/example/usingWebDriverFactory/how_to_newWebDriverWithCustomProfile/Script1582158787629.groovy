import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.WebDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens browser with pre-defined Profile named 'Katalon'.
 * Following types of browsers are supported
 * 
 * 1. Chrome
 * 2. Chrome Headless
 * 
 * Choose either of the following browser types when you activate this testcase.
 * 
 * If you do not have the profile named 'Katalon' for the browser chosen, an Exception will be thrown.
 * 
 */

WebDriver driver = WebDriverFactory.newWebDriver(DriverFactory.getExecutedBrowser(), 'Katalon')  // THIS IS THE MAGIC
assert driver != null
DriverFactory.changeWebDriver(driver)	// Do not forget this

WebUI.comment("${DriverFactory.getExecutedBrowser()} has been opend with profile Katalon")
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
