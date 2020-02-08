package com.kazurayam.ks.webdriverfactory.chrome.impl

import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.webdriverfactory.chrome.ChromePreferencesResolver

/**
 *
 * @author kazurayam
 */
class DefaultChromePreferencesResolver implements ChromePreferencesResolver {
	/**
	 *
	 * @return
	 */
	@Override
	Map<String, Object> resolveChromePreferences() {
		Map<String, Object> chromePreferences = new HashMap<>()
		// Below two preference settings will disable popup dialog when download file
		chromePreferences.put('profile.default_content_settings.popups', 0)
		chromePreferences.put('download.prompt_for_download', false)
		// set directory to save files
		Path downloads = Paths.get(System.getProperty('user.home'), 'Downloads')
		chromePreferences.put('download.default_directory', downloads.toString())
		// disable flash and pdf viewer
		chromePreferences.put('plugins.plugins_disabled',
				[
					'Adobe Flash Player',
					'Chrome PDF Viewer'
				])
		return chromePreferences
	}
}