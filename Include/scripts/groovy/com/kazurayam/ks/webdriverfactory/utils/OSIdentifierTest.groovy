<<<<<<< HEAD:Include/scripts/groovy/com/kazurayam/ks/thoughtful/OSIdentifierTest.groovy
package com.kazurayam.ks.thoughtful
=======
package com.kazurayam.ks.webdriverfactory.utils
>>>>>>> develop:Include/scripts/groovy/com/kazurayam/ks/webdriverfactory/utils/OSIdentifierTest.groovy

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.webdriverfactory.utils.OSIdentifier

@RunWith(JUnit4.class)
public class OSIdentifierTest {

	@Test
	void test_anyOneOfThem() {
		boolean isWindows = OSIdentifier.isWindows()
		boolean isMac = OSIdentifier.isMac()
		boolean isUnix = OSIdentifier.isUnix()
		boolean isSolaris = OSIdentifier.isSolaris()
		assertThat(isWindows || isMac || isUnix || isSolaris, is(true))
	}
}
