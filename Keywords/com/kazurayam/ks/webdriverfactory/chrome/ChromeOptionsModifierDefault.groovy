package com.kazurayam.ks.webdriverfactory.chrome

import org.openqa.selenium.chrome.ChromeOptions

public class ChromeOptionsModifierDefault implements ChromeOptionsModifier {

	ChromeOptions modify(ChromeOptions chromeOptions) {
		Objects.requireNonNull("chromeOptions must not be null")
		
		chromeOptions.addArguments("window-size=1024,768")
		chromeOptions.addArguments("--no-sandbox")

		//options.addArguments("--single-process")
		chromeOptions.addArguments("disable-infobars")        // disabling infobars
		
		//chromeOptions.addArguments("disable-extensions")    // disabling extensions
		chromeOptions.addArguments("disable-gpu")             // applicable to windows os only
		chromeOptions.addArguments("disable-dev-shm-usage")   // overcome limited resource problems
		
		return chromeOptions
	}
}
