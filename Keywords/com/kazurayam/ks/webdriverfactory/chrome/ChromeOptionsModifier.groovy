package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

interface ChromeOptionsModifier {

	ChromeOptions filter(ChromeOptions chromeOptions)
}
