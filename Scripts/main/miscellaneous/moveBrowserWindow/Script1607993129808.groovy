import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebDriver driver = DriverFactory.getWebDriver()
resizeHorizontalHalfLocateRight(driver)
WebUI.navigateToUrl('http://demoaut.katalon.com')
WebUI.delay(3)
WebUI.closeBrowser()

def resizeHorizontalHalfLocateLeft(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(0, 0);
}

def resizeHorizontalHalfLocateRight(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(new Point(d.getWidth(), 0))
}

Dimension resizeToHorizontalHalf(WebDriver driver) {
	driver.manage().window().maximize()
	Dimension maxDim = driver.manage().window().getSize()
	Dimension curDim = new Dimension((Integer)(maxDim.getWidth() / 2), maxDim.getHeight())
	driver.manage().window().setSize(curDim)
	return curDim
}