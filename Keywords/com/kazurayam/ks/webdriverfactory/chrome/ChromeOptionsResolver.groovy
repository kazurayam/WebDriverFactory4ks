package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

interface ChromeOptionsResolver {

	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences)
}