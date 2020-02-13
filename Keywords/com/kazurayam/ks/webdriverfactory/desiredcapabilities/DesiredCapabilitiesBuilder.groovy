package com.kazurayam.ks.webdriverfactory.desiredcapabilities

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

interface DesiredCapabilitiesBuilder {

	DesiredCapabilities build(ChromeOptions chromeOptions)
}