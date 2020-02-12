package com.kazurayam.ks.webdriverfactory.chrome

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author kazurayam
 *
 */
@RunWith(JUnit4.class)
public class ChromeDriverUtilsTest {

	@Test
	void test_getUserDataDirectory() {
		Path userDataDirectory = ChromeDriverUtils.getChromeUserDataDirectory()
		assertThat(Files.exists(userDataDirectory), is(true))
	}

	@Test
	void test_getChromeDriverPath() {
		Path chromeDriverPath = ChromeDriverUtils.getChromeDriverPath()
		assertThat(chromeDriverPath, is(notNullValue()))
		println "chromeDriverPath=\"${chromeDriverPath.toString()}\""
		assertTrue(Files.exists(chromeDriverPath))
	}

	@Test
	void test_ChromeProfile() {
		// when:
		Path profileDirectory = ChromeDriverUtils.getChromeUserDataDirectory().resolve('Default')
		ChromeProfile defaultProfile = new ChromeProfile(profileDirectory)
		// then:
		assertThat(defaultProfile, is(notNullValue()))
		assertThat(defaultProfile.getName(), is(notNullValue()))
		assertThat(defaultProfile.getDirectoryName(), is('Default'))
		assertThat(defaultProfile.getProfilePath(), is(profileDirectory))
	}
}