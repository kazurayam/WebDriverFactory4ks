package com.kazurayam.ks.webdriverfactory.desiredcapabilities

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

interface DesiredCapabilitiesResolver {

	DesiredCapabilities resolveDesiredCapabilities(ChromeOptions chromeOptions)
}