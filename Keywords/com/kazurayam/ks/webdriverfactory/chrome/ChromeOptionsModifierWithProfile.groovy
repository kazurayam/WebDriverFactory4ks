package com.kazurayam.ks.webdriverfactory.chrome

import java.nio.file.Files
import java.nio.file.Path

import org.openqa.selenium.chrome.ChromeOptions

public class ChromeOptionsModifierWithProfile implements ChromeOptionsModifier {

	private Path profileDirectory_

	ChromeOptionsModifierWithProfile(Path profileDirectory) {
		Objects.requireNonNull(profileDirectory, "profileDirectory must not be null")
		if (!Files.exists(profileDirectory)) {
			throw new IllegalArgumentException("${profileDirectory} does not exist")
		}
		this.profileDirectory_ = profileDirectory
	}

	ChromeOptions modify(ChromeOptions chromeOptions) {
		Objects.requireNonNull(chromeOptions, "chromeOptions must not be null")

		chromeOptions.addArguments("profile-directory=${profileDirectory_.getFileName().toString()}")

		return chromeOptions
	}
}
