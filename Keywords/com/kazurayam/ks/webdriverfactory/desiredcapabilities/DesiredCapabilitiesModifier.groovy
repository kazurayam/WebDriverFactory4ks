package com.kazurayam.ks.webdriverfactory.desiredcapabilities

import org.openqa.selenium.remote.DesiredCapabilities

interface DesiredCapabilitiesModifier {

	DesiredCapabilities filter(DesiredCapabilities desiredCapabilities)
}
