import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.WebDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with pre-defined Profile named 'Katalon'.
 * If you do not have 'Katalon' profile in your Chrome, an Exception will be thrown.
 */
WebDriverFactory wdFactory = WebDriverFactory.newInstance()
WebDriver driver = wdFactory.newWebDriver(DriverFactory.getExecutedBrowser(), 'Katalon')  // THIS IS THE MAGIC
assert driver != null
DriverFactory.changeWebDriver(driver)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(5)
WebUI.closeBrowser()
