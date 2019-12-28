package com.kazurayam.ks.clientsidestorage

import org.openqa.selenium.chrome.ChromeOptions

public interface ChromeOptionsResolver {

	ChromeOptions resolveChromeOptions(Map<String, Object> chromePreferences)
}