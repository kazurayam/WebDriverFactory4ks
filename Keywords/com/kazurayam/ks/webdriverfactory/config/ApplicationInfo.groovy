<<<<<<< HEAD:Keywords/com/kazurayam/ks/thoughtful/ApplicationInfo.groovy
package com.kazurayam.ks.thoughtful

=======
package com.kazurayam.ks.webdriverfactory.config

import java.util.regex.Pattern

import com.kazurayam.ks.webdriverfactory.utils.OSIdentifier

>>>>>>> develop:Keywords/com/kazurayam/ks/webdriverfactory/config/ApplicationInfo.groovy
import java.util.regex.Matcher
import java.util.regex.Pattern


public class ApplicationInfo {

	/**
	 * on Windows, %HOME%\.katalon\application.properties file is as follows:
	 <PRE>
	 #C:\Katalon_Studio_Windows_64-5.10.1
	 #Mon Mar 11 11:27:49 JST 2019
	 katalon.buildNumber=1
	 proxy.serverType=HTTP
	 proxy.username=
	 katalon.versionNumber=5.10.1
	 ntc=0
	 proxy.option=Manual proxy configuration
	 proxy.serverAddress=172.24.2.10
	 ntr=0
	 activated=1015_-403376007
	 proxy.serverPort=8080
	 email=kazurayam@gmail.com
	 npc=0
	 proxy.password=
	 ntcc=196
	 orgTime=1535948278390
	 </PRE>
	 *
	 * on Mac, ~/.katalon/application.properties file is as follows:
	 * <PRE>
	 # <projectname>.GlobalVarialbe.<gvname>=<value you want runtime>
	 OverridingGlobalVariablesWithPropertiesFile.GlobalVariable.hostname=demoaut.katalon.com
	 * </PRE>
	 * This means that on mac Katalon Studio is installed into the fixed location, therefore no need to recording the
	 * location into the application.properties file. But where the proxy information is recorded? I miss it.
	 */
	private Properties appProp

	/**
	 * the location of ~/.katalon/application.properities as constant string
	 *
	 * copied from
	 * public static final String APP_INFO_FILE_LOCATION
	 * in
	 * https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/constants/StringConstants.java
	 */
	static final String APP_INFO_FILE_LOCATION = System.getProperty("user.home") +
	File.separator + ".katalon" + File.separator + "application.properties"

	static final Pattern KATALON_HOME_PATTERN = Pattern.compile('^#([a-zA-Z]:.*)$')

	private String katalonHome

	/**
	 * copied from
	 * private static final ThreadLocal<Properties> applicationInfo
	 * in
	 * https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/configuration/RunConfiguration.java
	 */
	ApplicationInfo() {
		appProp = new Properties();
		File appPropFile = new File(APP_INFO_FILE_LOCATION)
		if (appPropFile.exists()) {
			try {
				appProp.load(new FileInputStream(appPropFile));
			} catch (FileNotFoundException e) {
				// do nothing
			} catch (IOException e) {
				// do nothing
			}
		}

		if (OSIdentifier.isWindows()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(appPropFile), "utf-8"))
			String line
			while ((line = br.readLine()) != null) {
				Matcher m = KATALON_HOME_PATTERN.matcher(line)
				if (m.matches()) {
					katalonHome = m.group(1)
				}
			}
		} else if (OSIdentifier.isMac()) {
			katalonHome = '/Applications/Katalon Studio.app'
		} else if (OSIdentifier.isUnix()) {
			katalonHome = '/usr/bin/katalon'   // TODO: should confirm the location
		} else {
			katalonHome = null
		}
	}

	/**
	 * Mimic of DriverFactory.getChromeDriverPath()
	 */
	String getChromeDriverPath() {
		if (OSIdentifier.isWindows()) {
			throw new UnsupportedOperationException("TODO")
		} else if (OSIdentifier.isMac()) {
			throw new UnsupportedOperationException("TODO")
		} else if (OSIdentifier.isUnix()) {
			throw new UnsupportedOperationException("TODO")
		} else {
			throw new IllegalStateException("unsupported OS")
		}
	}

	Properties getAppProp() {
		return this.appProp
	}

	String getKatalonHome() {
		return katalonHome
	}

	String getProxyOption() {
		return this.appProp.get('proxy.option')  // Manual proxy configuration
	}

	String getProxyServerType() {
		return this.appProp.getProperty('proxy.serverType')  // HTTP
	}

	String getProxyServerAddress() {
		return this.appProp.getProperty('proxy.serverAddress')  // 172.24.2.10
	}

	String getProxyServerPort() {
		return this.appProp.getProperty('proxy.serverPort')  // 8080
	}

	String getProxyUsername() {
		return this.appProp.getProperty('proxy.username')   // can be null
	}

	String getProxyPassword() {
		return this.appProp.getProperty('proxy.password')   // can be null
	}
}
