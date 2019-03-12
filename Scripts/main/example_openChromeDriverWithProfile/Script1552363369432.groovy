import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory4ks.ChromeDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with pre-defined Profile named 'Katalon'.
 * If you do not have 'Katalon' profile in your Chrome, an Exception will be thrown.
 */
ChromeDriverFactory cdFactory = new ChromeDriverFactory()
WebDriver driver = cdFactory.openChromeDriverWithProfile('Katalon')  // THIS IS THE MAGIC
assert driver != null
DriverFactory.changeWebDriver(driver)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
