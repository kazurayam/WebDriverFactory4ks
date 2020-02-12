package com.kazurayam.ks.webdriverfactory.chrome.impl

import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.ks.webdriverfactory.chrome.ChromeOptionsResolver
import com.kazurayam.ks.webdriverfactory.chrome.ChromeDriverUtils

/**
 *
 * @author kazurayam
 *
 */
class ChromeOptionsDefaultResolver implements ChromeOptionsResolver {
	
	/**
	 *
	 */
	@Override
	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences) {
		ChromeOptions options = new ChromeOptions()
		// set location of the Chrome Browser's binary
		options.setBinary(ChromeDriverUtils.getChromeBinaryPath().toString());
		// set my chrome preferences
		options.setExperimentalOption('prefs', chromePreferences)
		// The following lines are copy&pasted from
		// https://github.com/SeleniumHQ/selenium/issues/4961

		return options
	}
}