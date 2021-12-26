import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory.DriverTypeName
import com.kazurayam.ks.webdriverfactory.KSWebDriverFactory

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens browser with pre-defined Profile named 'Katalon'.
 * Following types of browsers are supported.
 * Choose either of the browser types when you activate this testcase.
 * 
 * 1. Chrome
 * 2. Chrome Headless
 * 
 * If you do not have the user profile 'Katalon' in Chrome browser, an Exception will be thrown.
 */
KSWebDriverFactory factory = 
    new KSWebDriverFactory.Builder(DriverTypeName.CHROME_DRIVER)    // or DriverTypeName.HEADLESS_DRIVER
        .userProfile('Katalon').build()
		
WebDriver driver = factory.newWebDriver()
assert driver != null
DriverFactory.changeWebDriver(driver)	// Do not forget this

WebUI.comment("${DriverFactory.getExecutedBrowser()} has been opend with profile Katalon")
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
