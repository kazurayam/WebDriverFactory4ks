<<<<<<< HEAD:Include/scripts/groovy/com/kazurayam/ks/thoughtful/AssertTest.groovy
package com.kazurayam.ks.thoughtful
=======
package com.kazurayam.ks.webdriverfactory.utils
>>>>>>> develop:Include/scripts/groovy/com/kazurayam/ks/webdriverfactory/utils/AssertTest.groovy

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.thoughtful.Assert as MyAssert

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kazurayam.ks.webdriverfactory.utils.Assert as MyAssert
import com.kms.katalon.core.exception.StepFailedException

@RunWith(JUnit4.class)
class AssertTest {

	@Test
	void test_assertTrue() {
		def expected = "Ken"
		def actual   = "Ken"
		MyAssert.assertTrue(
				"expected truth that ${expected} is ${actual}, but found false",
				expected == actual,
				FailureHandling.OPTIONAL)
	}

	@Test
	void test_assertFalse() {
		def expected = "Ken"
		def actual   = "Merry"
		MyAssert.assertFalse(
				"$expected truth that {expected} is ${actual}, but found false",
				expected == actual,
				FailureHandling.OPTIONAL)
	}

	@Test
	void test_assertEquals_String() {
		def expected = "Tom"
		def actual   = "Tom"
		MyAssert.assertEquals(
				"expected(${expected}) was expected to be equal to actual(${actual}), but found not",
				expected, actual,
				FailureHandling.CONTINUE_ON_FAILURE)
	}

	@Test
	void test_assertEquals_String_throwsException() {
		try {
			def expected = "Tom"
			def actual   = "Jerry"
			MyAssert.assertEquals(
					"expected(${expected}) was expected to be equal to actual(${actual}), but found not",
					expected, actual,
					FailureHandling.OPTIONAL)
		} catch (StepFailedException ex) {
			assertEquals("expected(Tom) was expected to be equal to actual(Jerry), but found not", ex.getMessage())
		}
	}

	@Test
	void test_assertEquals_Number() {
		Number expected = 0
		Number actual   = 0
		MyAssert.assertEquals(
				"expected(${expected}) was expected to be equal to actual(${actual}), but found not",
				expected, actual,
				FailureHandling.CONTINUE_ON_FAILURE)
	}

	@Test
	void test_assertEquals_Number_throwsException() {
		try {
			Number expected = 0
			Number actual   = 999
			MyAssert.assertEquals(
					"expected(${expected}) was expected to be equal to actual(${actual}), but found not",
					expected, actual,
					FailureHandling.OPTIONAL)
		} catch (StepFailedException ex) {
			assertEquals("expected(0) was expected to be equal to actual(999), but found not", ex.getMessage())
		}
	}
}