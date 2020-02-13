import org.openqa.selenium.WebDriver

<<<<<<< HEAD:Scripts/main/example_openChromeDriverWithProfile/Script1552363369432.groovy
import com.kazurayam.ks.thoughtful.ChromeDriverFactory
=======
import com.kazurayam.ks.webdriverfactory.WebDriverFactory
>>>>>>> develop:Scripts/main/example/WebDriverFactory/how_to_newWebDriverForChromeWithCustomProfile/Script1581383208888.groovy
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
