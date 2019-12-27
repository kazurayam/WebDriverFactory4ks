package com.kazurayam.thoughtful

import org.openqa.selenium.chrome.ChromeOptions

public interface ChromeOptionsResolver {

	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences)
}