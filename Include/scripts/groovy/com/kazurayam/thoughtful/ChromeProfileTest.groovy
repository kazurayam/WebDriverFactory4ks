package com.kazurayam.thoughtful

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Path

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author kazurayam
 *
 */
@RunWith(JUnit4.class)
public class ChromeProfileTest {

	@Test
	void test_ChromeProfile() {
		// when:
		Path profileDirectory = ChromeDriverFactory.getChromeUserDataDirectory().resolve('Default')
		ChromeProfile defaultProfile = new ChromeProfile(profileDirectory)
		// then:
		assertThat(defaultProfile, is(notNullValue()))
		assertThat(defaultProfile.getName(), is(notNullValue()))
		assertThat(defaultProfile.getDirectoryName(), is('Default'))
		assertThat(defaultProfile.getProfilePath(), is(profileDirectory))
	}
}