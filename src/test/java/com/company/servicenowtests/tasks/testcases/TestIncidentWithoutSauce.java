package com.company.servicenowtests.tasks.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.company.servicenowtests.BaseServiceNowTest;
import com.company.servicenowtests.ScreenshotUtils;
import com.company.servicenowtests.tasks.Incident;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

/**
 * Tests Incident Management using the Firefox Driver that comes with Selenium
 * 
 */
public class TestIncidentWithoutSauce extends BaseServiceNowTest {

	public Incident incident;
    
   
	
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
        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  
        incident = PageFactory.initElements(driver, Incident.class);
        incident.login(0);
        ScreenshotUtils.captureSnapshotForAllure(driver);
    	}catch(Exception ex) {ex.printStackTrace();}
    }
    
	@Severity(SeverityLevel.BLOCKER)
    @Description("Create, Update, and Resolve an Incident using the Service Desk Application")
	@Story("Test creating, updating, and resolving an incident ticket using the Service Desk Application as an ITIL user")
	@Test
    public void testIncident(){
        createIncident();
        resolveIncident();
    }
    
    /**
     * Creates a new incident
     */
    public void createIncident(){
        driver.get(incident.getBASE_URL() + "incident.do");
        incidentCreationAssertions();
        incident.assignmentGroup.sendKeys("Service Desk");
        incident.callerId.sendKeys("ITIL User");
        incident.useElementSelection(incident.contactType, "phone");
        incident.workNotes.click();
        incident.shortDescription.sendKeys("Testing Incident with Selenium");
        incident.insertAndStayButton.click(); 
        try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ScreenshotUtils.captureSnapshotForAllure(driver);
    }
    
    /**
     * Resolves the current incident
     */
    public void resolveIncident(){
        driver.get(incident.getCurrentIncident());
        incident.resolvebtn.click();
        try {
 			TimeUnit.SECONDS.sleep(10);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
       WebElement resoinfo  = driver.findElement(By.xpath("//*[@id=\"tabs2_section\"]/span[3]/span[1]/span[2]"));
       resoinfo.click();
       ScreenshotUtils.captureSnapshotForAllure(driver);
        incident.useElementSelection(incident.closeCode, "solved (permanently)");      
        incident.closeNotes.sendKeys("Resolved this incident.");
        incident.updateButton.click();
        try {
 			TimeUnit.SECONDS.sleep(10);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        ScreenshotUtils.captureSnapshotForAllure(driver);
    }	
        
    /**
     * Checks the fields of an incident form during creation
     */
    public void incidentCreationAssertions(){
        //assertTrue("Assignment group is mandatory", incident.isMandatory(incident.assignmentGroupSpan));
        //assertTrue("Number is read only", incident.isReadOnly(incident.numberSpan));
    }    
       
    /**
     * Checks the fields of an incident during resolution
     */
    public void incidentResolveAssertions(){
        //assertTrue("Close Code is mandatory", incident.isMandatory(incident.closeCodeSpan));
        //assertTrue("Close Notes are mandatory", incident.isMandatory(incident.closeNotesSpan));
    }
    
}
