package com.company.servicenowtests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.company.servicenowtests.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provides basic functionality for tests that don't use Sauce Labs
 * 
 * 
 */

public class BaseServiceNowTest {
    
    public WebDriver driver;
    public WebDriverWait wait;
    public BaseTest baseTest;
    public @Rule
    TestName testName = new TestName();
    public String browser;
    public String os;
    public String version;

    @BeforeMethod
	public void setUp() {
    	
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
        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  
        baseTest = PageFactory.initElements(driver, BaseTest.class);   
    	}catch(Exception ex) {ex.printStackTrace();}
    }

    @AfterMethod
	public void tearDown(){
    	
        driver.quit();
    }   
        
}
