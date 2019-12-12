package com.company.servicenowtests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * Basic Navigation Functionalities: Access to All Apps, 
 * Incidents Open, My Requests, Service Catalog, and Knowledge Base
 */

public class ServiceNowLeftNav extends BaseTest {

    public ServiceNowLeftNav(WebDriver driver) {
        super(driver);
    }
        
    /**
     * Expands all applications in the left nav
     */
    public void expandAll() {
        switchToNavFrame();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("ServiceNow Selenium Tests - The Left Nav");
        }
    }
    
    /**
     * Collapses all Applications in the left nav
     */
    public void collapseAll() {     
        switchToNavFrame();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("$j(window).trigger(\"collapse_all\")");
        }
    }
    
       
    /**
     * Switches to the left nav frame of ServiceNow
     */
    public void switchToNavFrame() {
       driver.switchTo().defaultContent();
       WebElement allapps = driver.findElement(By.id("allApps_tab"));
       allapps.click();
       try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
       
       IncidentOpen.click();
       
       try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
       
       MyRequests.click();
       
       try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
       
       ServiceCatalog.click();
       
       try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
       
       Knowledge.click();
       
       try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       ScreenshotUtils.captureSnapshotForAllure(driver);
    }
    
    /**
     * Switches to the main frame of ServiceNow
     */
    public void switchToMainFrame() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("gsft_main");
    }
    
    /**
     * Switches frames to the left nav, clicks on a given element, then switches back to the main frame
     * @param element A link element to click on
     */
    public void navigateToElement(WebElement element) {
       switchToNavFrame();
        expandAll();
        element.click();
        switchToMainFrame();
    }
    
    /**
     * Finds the breadcrumb on the page and compares its filter text to see if it contains an expected value
     * @param page The name of the page (formal table name)
     * @param text The filter text expected
     * @return True if the filter text is present in the page's filter
     */
    public boolean checkForBreadcrumbText(String page, String text) {
        String filterText = driver.findElement(By.id(page + "_breadcrumb")).getText();
        return filterText.contains(text);
    }
    
    /**
     * Navigates to a page using the left nav, then compares the filter text to an expected value
     * @param element A link element to click on
     * @param page The name of the page (formal table name)
     * @param text The filter text expected
     */
    public void navigateToElementAndCheckitsBreadcrumb(WebElement element, String page, String text) {
        navigateToElement(element);
        assertTrue("The filter was correct", checkForBreadcrumbText(page, text));
    }    
    
    /**
     * Gets a list of LeftNavFilters to check for Incident
     * @return LeftNavFilters for incident
     */
    public List<LeftNavFilter> getIncidentFilters() {
        List<LeftNavFilter> incidentFilter = new ArrayList();
        incidentFilter.add(new LeftNavFilter(IncidentOpen, "incident", "active=true"));
        //incidentFilter.add(new LeftNavFilter(IncidentClosed, "incident", "active=false"));
        return incidentFilter;
    }
   
    @FindBy(id = "087800c1c0a80164004e32c8a64a97c9")
    public WebElement IncidentOpen;
        
    @FindBy(id = "0878652cc0a801640001ff83e2bfbfe7")
    public WebElement Knowledge;
    
    @FindBy (id = "e660172ac611227b00fa88fb47ae3fad")
    public WebElement ServiceCatalog;
    
    @FindBy(id = "e661dff4c611227b01af0af70d4b67f1")
    public WebElement MyRequests;

}
