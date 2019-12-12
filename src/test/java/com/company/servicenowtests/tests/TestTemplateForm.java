package com.company.servicenowtests.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.company.servicenowtests.TemplateForm;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.SeverityLevel;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import com.company.servicenowtests.BaseServiceNowTest;
import com.company.servicenowtests.ScreenshotUtils;

/**
 *
 * Test creating a system template and then updating the template.
 */

public class TestTemplateForm extends BaseServiceNowTest{
    
    public TemplateForm templateForm;
    
  
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
        templateForm = PageFactory.initElements(driver, TemplateForm.class);
        templateForm.login(1);
        ScreenshotUtils.captureSnapshotForAllure(driver);
		}catch(Exception ex) {ex.printStackTrace();}
    }
    
	@Severity(SeverityLevel.BLOCKER)
    @Description("Create a system template")
	@Story("Test creating a system template and updating tasks for that template as the Admin user.")
	@Test
    public void createAChangeTemplate() {
        templateForm.createATemplateRecord("change_request");
        templateForm.updateATaskTemplate();    
    }
	
}
