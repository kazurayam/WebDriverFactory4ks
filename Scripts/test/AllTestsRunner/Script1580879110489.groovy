import static com.kazurayam.junit4ks.JUnitCustomKeywords.runWithJUnitRunner

import com.kazurayam.ks.webdriverfactory.ApplicationInfoTest
import com.kazurayam.ks.webdriverfactory.AssertTest
import com.kazurayam.ks.webdriverfactory.OSIdentifierTest

import com.kazurayam.ks.webdriverfactory.chrome.impl.ChromeDriverFactoryTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileFinderTest
import com.kazurayam.ks.webdriverfactory.chrome.ChromeProfileTest

runWithJUnitRunner(ApplicationInfoTest.class)
runWithJUnitRunner(AssertTest.class)
runWithJUnitRunner(OSIdentifierTest.class)

runWithJUnitRunner(ChromeDriverFactoryTest.class)
runWithJUnitRunner(ChromeProfileFinderTest.class)
runWithJUnitRunner(ChromeProfileTest.class)

