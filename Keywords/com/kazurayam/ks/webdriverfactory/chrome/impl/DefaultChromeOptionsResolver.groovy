package com.kazurayam.ks.webdriverfactory.chrome.impl

import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsResolver

/**
 *
 * @author kazurayam
 *
 */
class DefaultChromeOptionsResolver implements ChromeOptionsResolver {
	/**
	 *
	 */
	@Override
	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences) {
		ChromeOptions options = new ChromeOptions()
		// set location of the Chrome Browser's binary
		options.setBinary(ChromeDriverFactoryImpl.getChromeBinaryPath().toString());
		// set my chrome preferences
		options.setExperimentalOption('prefs', chromePreferences)
		// The following lines are copy&pasted from
		// https://github.com/SeleniumHQ/selenium/issues/4961
		//options.addArguments("--headless")     // thought that this is necessary for working around the "(unknown error: DevToolsActivePort file doesn't exist)"
		options.addArguments("window-size=1024,768")
		options.addArguments("--no-sandbox")

		//options.addArguments("--single-process")
		options.addArguments("disable-infobars")        // disabling infobars
		//chromeOptions.addArguments("disable-extensions")    // disabling extensions
		options.addArguments("disable-gpu")             // applicable to windows os only
		options.addArguments("disable-dev-shm-usage")   // overcome limited resource problems
		//
		return options
	}
}