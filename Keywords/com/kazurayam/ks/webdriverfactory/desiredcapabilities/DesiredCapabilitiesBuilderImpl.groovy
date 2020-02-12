package com.kazurayam.ks.webdriverfactory.desiredcapabilities

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

public class DesiredCapabilitiesBuilderImpl implements DesiredCapabilitiesBuilder {

	/**
	 *
	 */
	@Override
	DesiredCapabilities build(ChromeOptions chromeOptions) {
		DesiredCapabilities cap = DesiredCapabilities.chrome()
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
		return cap
	}
}
