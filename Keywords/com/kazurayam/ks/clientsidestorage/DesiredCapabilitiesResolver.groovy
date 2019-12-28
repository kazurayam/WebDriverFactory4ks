package com.kazurayam.ks.clientsidestorage

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

public interface DesiredCapabilitiesResolver {

	DesiredCapabilities resolveDesiredCapabilities(ChromeOptions chromeOptions)
}