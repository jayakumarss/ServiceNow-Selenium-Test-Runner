package com.company.servicenowtests.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.company.servicenowtests.ScreenshotUtils;
import com.company.servicenowtests.ServiceNowLeftNav;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.company.servicenowtests.BaseServiceNowTest;

/**
 *
 * Test left menu system navigation.
 */
public class TestLeftNav extends BaseServiceNowTest {
    
    ServiceNowLeftNav leftNav;

    @BeforeMethod
	public void setUp() {
    	try {
    	System.setProperty("webdriver.gecko.driver","D:\\Users\\jayakumars\\git\\ServiceNow-Selenium-Test-Runner\\geckodriver-v0.26.0-win64\\geckodriver.exe");
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
    	capabilities.setCapability(CapabilityType.VERSION, version);
    	capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);
    	capabilities.setCapability("name", testName.getMethodName());
        driver = new FirefoxDriver();      
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  
        leftNav = PageFactory.initElements(driver, ServiceNowLeftNav.class);
        leftNav.login(0);
        ScreenshotUtils.captureSnapshotForAllure(driver);
    	}catch(Exception ex) {ex.printStackTrace();}
    }
   
   @Severity(SeverityLevel.BLOCKER)
   @Description("Verify Basic Navigatsion functionalities as an ITIL user")
   @Story("Test the following Features: Access to: Application, Incidents, Requests, Service Catalog, and Knowledge Base")
   @Test
   public void testswitchToNavFrame() {
    	leftNav.switchToNavFrame();
    }
    
}
