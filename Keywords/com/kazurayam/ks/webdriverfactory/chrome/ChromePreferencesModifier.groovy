package com.kazurayam.ks.webdriverfactory.chrome

interface ChromePreferencesModifier {

	Map<String, Object> filter(Map<String, Object> chromePreferences)
}
