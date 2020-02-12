package com.kazurayam.ks.webdriverfactory.chrome

import java.nio.file.Path
import java.nio.file.Paths

/**
 *
 * @author kazurayam
 */
class ChromePreferencesDefaultResolver implements ChromePreferencesResolver {
	
	/**
	 *
	 * @return
	 */
	@Override
	Map<String, Object> resolveChromePreferences() {
		Map<String, Object> chromePreferences = new HashMap<>()
		return chromePreferences
	}
}