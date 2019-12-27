package com.kazurayam.webdriverfactory4ks

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

public class DefaultDesiredCapabilitiesResolver implements DesiredCapabilitiesResolver {
	/**
	 *
	 */
	@Override
	DesiredCapabilities resolveDesiredCapabilities(ChromeOptions chromeOptions) {
		DesiredCapabilities cap = DesiredCapabilities.chrome()
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
		return cap
	}
}
