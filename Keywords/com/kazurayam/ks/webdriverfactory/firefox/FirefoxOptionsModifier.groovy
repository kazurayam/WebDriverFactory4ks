package com.kazurayam.ks.webdriverfactory.firefox

import org.openqa.selenium.firefox.FirefoxOptions

interface FirefoxOptionsModifier {

	FirefoxOptions modify(FirefoxOptions firefoxOptions)
}