package com.kazurayam.thoughtfulwebdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

public interface ChromeOptionsResolver {

	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences)
}