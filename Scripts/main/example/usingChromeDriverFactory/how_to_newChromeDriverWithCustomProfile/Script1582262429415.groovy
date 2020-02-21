import org.openqa.selenium.WebDriver

import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinder
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case opens Chrome browser with a Profile named 'Kazuaki', which is my day-to-day use.
 * If the profile 'Katalon' is not found, an Exception will be thrown.
 * Once opened Chrome, this test case navigates to Gmail at https://mail.google.com/.
 * 
 * Gmail requires login with gmail address and password.
 * 
 * I wanted my Katalon test case to open Gmail without manual login.
 * I thought launching Chrome with my day-to-day profile may may cheat the Chrome browser and
 * let me open Gmail withoug login.
 * I tried, but failed.
 * The security design of Chrome browser was strict enough.
 * I launched the browser the 'Kazuaki' profile, but the browser detected that 
 * it was launched by a dirty tool and challenged credential.
 * Wow, I gave up. 
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

WebUI.navigateToUrl('https://mail.google.com/mail/u/0/#inbox')   // possibly already logged-in, isn't it?
WebUI.delay(5)
//WebUI.closeBrowser()
