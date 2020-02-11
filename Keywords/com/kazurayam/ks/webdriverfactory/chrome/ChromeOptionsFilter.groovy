package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

interface ChromeOptionsFilter {

	ChromeOptions filter(ChromeOptions chromeOptions)
	
}
