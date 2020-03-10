import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.WebDriverFactory
import com.kms.katalon.core.driver.DriverType
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome/Chrome Headless browser with pre-defined Profile named 'Katalon'.
 * It depends on which browser you chose when you activete the test case. 
 * If you do not have 'Katalon' profile in your Chrome, an Exception will be thrown.
 */

DriverType driverType = DriverFactory.getExecutedBrowser()
WebUI.comment("DriverType is ${driverType.getName()}")

WebDriver driver = WebDriverFactory.newWebDriver(driverType, 'Katalon')  // THIS IS THE MAGIC

assert driver != null

DriverFactory.changeWebDriver(driver)

WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
