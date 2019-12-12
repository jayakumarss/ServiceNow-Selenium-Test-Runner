package com.company.servicenowtests.servicecatalog.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.company.servicenowtests.servicecatalog.SalesLaptop;

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
import com.company.servicenowtests.ScreenshotUtils;

/**
 * Tests the Sales Laptop service catalog item & creates an RITM.
 * 
 *
 */
public class TestSalesLaptop extends BaseServiceNowTest {    
	
	private SalesLaptop ritm;
    private String url = "com.glideapp.servicecatalog_cat_item_view.do?sysparm_id=e212a942c0a80165008313c59764eea1";

	@BeforeMethod
	public void setUp(){
    	try {
		//System.setProperty("webdriver.chrome.driver","D:\\Users\\jayakumars\\git\\ServiceNow-Selenium-Test-Runner\\chromedriver_win32\\chromedriver.exe");
    	System.setProperty("webdriver.gecko.driver","D:\\Users\\jayakumars\\git\\ServiceNow-Selenium-Test-Runner\\geckodriver-v0.26.0-win64\\geckodriver.exe");
  	  	DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
    	capabilities.setCapability(CapabilityType.VERSION, version);
    	capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);
    	capabilities.setCapability("name", testName.getMethodName());
        //driver = new ChromeDriver(); 
    	driver = new FirefoxDriver(); 
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  
        ritm = PageFactory.initElements(driver, SalesLaptop.class);
        ritm.login(0);
        ScreenshotUtils.captureSnapshotForAllure(driver);
		}catch(Exception ex) {ex.printStackTrace();}
    }
    
	@Severity(SeverityLevel.BLOCKER)
    @Description("Create a laptop order using the Service Catalog Application")
	@Story("Test creating a customized laptop order using the Service Catalog Application as the ITIL user.")
	@Test
    public void testCreationOfOrder(){
        driver.get(ritm.getBASE_URL() + url);
        try {
 			TimeUnit.SECONDS.sleep(5);
 		} catch (InterruptedException e) {}
        ritm.acrobat.click();
        ScreenshotUtils.captureSnapshotForAllure(driver);
        try {
 			TimeUnit.SECONDS.sleep(5);
 		} catch (InterruptedException e) {}
        ritm.adddescription.sendKeys("Please configure all of the Adobe Suite of Products");
        ScreenshotUtils.captureSnapshotForAllure(driver);
        try {
 			TimeUnit.SECONDS.sleep(5);
 		} catch (InterruptedException e) {}
        ritm.orderNow.click();
        ScreenshotUtils.captureSnapshotForAllure(driver);
        try {
 			TimeUnit.SECONDS.sleep(5);
 		} catch (InterruptedException e) {}
        ritm.sys_id = ritm.extractSysIDFromLink();
        driver.get(ritm.getBASE_URL() + "sc_req_item.do?sys_id=" + ritm.sys_id);
        try {
 			TimeUnit.SECONDS.sleep(5);
 		} catch (InterruptedException e) {}
        ScreenshotUtils.captureSnapshotForAllure(driver);
    }
    
}
