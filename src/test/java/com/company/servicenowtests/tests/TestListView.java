package com.company.servicenowtests.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.company.servicenowtests.BaseServiceNowTestSauce;
import com.company.servicenowtests.BaseTest;
import com.company.servicenowtests.ListView;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import com.company.servicenowtests.BaseServiceNowTest;
/**
 * 
 * Test ListView of Incidents, Problems, and Change Requests.
 */
@Ignore
public class TestListView extends BaseServiceNowTest {
    
    public ListView listView;

    @BeforeMethod
	public void setUp(){
    	System.out.println("Inside Incident Setup....");
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
        System.out.println("browswer init");
        
        listView = PageFactory.initElements(driver, ListView.class);
        listView.login(1);
    }
    
    @Test
   public void testChangeManagementListView() {
        driver.get(listView.getBASE_URL() + "change_request_list.do");
      if (listView.isListViewCustomized()) {
            listView.uncustomizeListView();
        }
        List<String> expectedValues = Arrays.asList("Number","Short description","Type","State","Planned start date","Planned end date","Assigned to");
        listView.compareHeadingsToExpectedValues(expectedValues);
    }
    
    @Test
   public void testIncidentListView() {
        driver.get(listView.getBASE_URL() + "incident_list.do");
        if (listView.isListViewCustomized()) {
            listView.uncustomizeListView();
       }
        List<String> expectedValues = Arrays.asList("Number", "Caller", "Short description", "Category", "Priority", "State", "Assignment group", "Assigned to");
        listView.compareHeadingsToExpectedValues(expectedValues);
    }
    
   @Test
    public void testProblemListView() {
        driver.get(listView.getBASE_URL() + "problem_list.do");
        if (listView.isListViewCustomized()) {
            listView.uncustomizeListView();
        }
        List<String> expectedValues = Arrays.asList("Number", "Short description", "State", "Assignment group", "Assigned to", "Configuration item", "Related Incidents");
        listView.compareHeadingsToExpectedValues(expectedValues);
    }
}
