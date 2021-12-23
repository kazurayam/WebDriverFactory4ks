package com.kazurayam.ks.webdriverfactory.firefox

import org.junit.Test
import org.openqa.selenium.WebDriver

public class FirefoxDriverFactoryTest {

	@Test
	public void test_createFirefoxDriver() {
		FirefoxDriverFactory factory = FirefoxDriverFactory.newInstance()
		WebDriver driver = factory.newFirefoxDriver()
		assert driver != null
		driver.quit()
	}
}
