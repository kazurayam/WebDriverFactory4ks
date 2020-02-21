import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinder
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with a Profile which is associated with
 * the user data directory named 'Default'.
 * 
 * The user data directory 'Default' is created when the Chrome is installed,
 * so usually be there.
 * If the directory 'User Data\Default' is not found, an Exception will be thrown.
 * 
 * Once opened Chrome, this test case navigates to Gmail at https://mail.google.com/.
 * Gmail usually requires login with username/password.
 * However this time you would not be requested to login.
 * It is because the Chrome Profile associated with the Default directory would be your
 * day-to-day use, and you have already logged into the Gmail alread. 
 * 
 * You may encounter an error like:
 * >org.openqa.selenium.InvalidArgumentException: invalid argument: user data directory is already in use, please specify a unique value for --user-data-dir argument, or don't use --user-data-dir
 * This is because you have a Google Chrome process already up and running with 'Default' profile.
 * You need to stop it.
 * You may want to use Windows' Task Manager to terminate all processes of Google Chrome.
 */
ChromeDriverFactory cdFactory = ChromeDriverFactory.newInstance()
WebDriver driver = cdFactory.newChromeDriverWithProfile('Kazuaki')
assert driver != null
//assert cdFactory.getChromeProfile().getName() == 'Kazuaki'

DriverFactory.changeWebDriver(driver)

WebUI.navigateToUrl('https://mail.google.com/')   // possibly already logged-in, isn't it?
WebUI.delay(3)
WebUI.closeBrowser()
