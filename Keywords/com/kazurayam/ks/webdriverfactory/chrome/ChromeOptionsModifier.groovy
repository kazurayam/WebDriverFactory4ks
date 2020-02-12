package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

interface ChromeOptionsModifier {

	ChromeOptions modify(ChromeOptions chromeOptions)
}
