import static com.kazurayam.junit4ks.JUnitCustomKeywords.runWithJUnitRunner

import com.kazurayam.ks.webdriverfactory.WebDriverFactoryTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverFactoryTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverUtilsTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinderTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileTest
import com.kazurayam.ks.webdriverfactory.config.ApplicationInfoTest
import com.kazurayam.ks.webdriverfactory.utils.AssertTest
import com.kazurayam.ks.webdriverfactory.utils.OSIdentifierTest
import com.kms.katalon.core.driver.DriverTypeTest

runWithJUnitRunner(WebDriverFactoryTest.class)

runWithJUnitRunner(ChromeDriverFactoryTest.class)
runWithJUnitRunner(ChromeDriverUtilsTest.class)

runWithJUnitRunner(ChromeProfileFinderTest.class)
runWithJUnitRunner(ChromeProfileTest.class)

runWithJUnitRunner(ApplicationInfoTest.class)

runWithJUnitRunner(AssertTest.class)
runWithJUnitRunner(OSIdentifierTest.class)

runWithJUnitRunner(DriverTypeTest.class)

