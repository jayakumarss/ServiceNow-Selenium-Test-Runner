package com.company.servicenowtests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author jbauguess
 */
public class TemplateForm extends BaseTest {

    public TemplateForm(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(id = "sys_template.name")
    public WebElement name;
    
    @FindBy(id = "status.sys_template.name")
    public WebElement nameSpan;
 
 	@FindBy(id = "element.sys_template.table")
     public WebElement table;
 	
 	//@FindBy(id = "sys_template.table")
    //public WebElement table;

 	@FindBy(xpath=("//*[@class=\"select2-container filerTableSelect select2 form-control filter_type\"]"))
 	public WebElement template;
    
    @FindBy(id = "status.sys_template.table")
    public WebElement tableSpan;
    
    @FindBy(id = "ni.sys_template.active")
    public WebElement active;
    
    @FindBy(id = "status.sys_template.active")
    public WebElement activeSpan;
    
    @FindBy(id = "sys_display.sys_template.user")
    public WebElement user;
    
    @FindBy(id = "status.sys_template.user")
    public WebElement userSpan;
    
    @FindBy(id = "sys_display.sys_template.group")
    public WebElement group;

    @FindBy(id = "status.sys_template.group")
    public WebElement groupSpan;
    
    @FindBy(id = "ni.sys_template.global")
    public WebElement global;

    @FindBy(id = "status.sys_template.global")
    public WebElement globalSpan;

    @FindBy(id = "sys_template.short_description")
    public WebElement shortDescription;
    
    @FindBy(id = "status.sys_template.short_description")
    public WebElement shortDescriptionSpan;
    
    @FindBy(id = "sys_template.templatefilters_table")
    public WebElement templateFiltersTable;   
    
    @FindBy(id = "s2id_autogen1_search")
    public WebElement tablesearch;
    
    @FindBy(id = "s2id_autogen5983_search")
    public WebElement templatesearch;

    @FindBy(xpath=("//*[@class=\"select2-result-label\"]"))
	public WebElement result;
    
    public String sys_id;
    
    /**
     * Gets the elements on the page that are template fields
     * Index 0/3/6/etc are the fields names
     * Index 1/4/7/etc are the operators (not really used)
     * Index 2/5/8/etc are the values
     * @return 
     */
    public List<WebElement> refreshTemplateFilters() {
        return driver.findElements(By.className("filerTableSelect"));
    }
    
    /**
     * Creates a template for a given table
     * @param tableName The table name
     */
    public void createATemplateRecord(String tableName) {
        driver.get(getBASE_URL() + "sys_template.do");
        shortDescription.sendKeys("Testing templates for " + tableName);
        table.click();
        tablesearch.sendKeys(tableName);
      
        if (result.equals(tablesearch));
        {
        result.click();	
        }
        
        try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        insertAndStayButton.click();
        try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ScreenshotUtils.captureSnapshotForAllure(driver);
        sys_id = sysId.getAttribute("value");
    }
  
    /**
     * Sets a field on the template form
     * @param index which field is being set (0-based)
     * @param field The field label being set
     * @param value The value to give the template
     */
    public void setAField(int index, String field, String value) {
        List<WebElement> rows = templateFiltersTable.findElements(By.className("filter_row_condition"));
        List<WebElement> selects = rows.get(index).findElements(By.className("sn-filter-top"));
        WebElement currentSelect = selects.get(1).findElement(By.tagName("select"));        
        useSelectElement(currentSelect, field);
        rows = templateFiltersTable.findElements(By.className("filter_row_condition"));
        WebElement input = rows.get(index).findElement(By.className("form-inline"));
        List<WebElement> inputs = input.findElements(By.className("form-control"));
        if (inputs.size() == 2) {
            inputs.get(1).sendKeys(value); //This is to handle reference fields
            } else {
            inputs.get(0).sendKeys(value);
            }
        try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**
     * Clears all values in the template
     */
    public void clearExistingValues() {
        List<WebElement> deleteButtons = driver.findElements(By.className("deleteButton"));
        for (WebElement el : deleteButtons) {
            try {
                el.click();
            } catch (Exception e) {
                //The last one isn't visible
            }
        }
    }
    
    /**
     * Clears a single field
     * @param index The index of the field line to clear
     */
    public void clearExistingValueByIndex(int index) {
        List<WebElement> deleteButtons = driver.findElements(By.className(".filerTableAction.btn.btn-default.deleteButton"));
        
        try {
        	deleteButtons.get(index).click();
        	
        } catch (Exception e) {
            //The last one isn't visible
        	e.printStackTrace();
        }
        updateButton.click();
       }
    
    /**
     * Updates a task template with two dummy values
     */
    public void updateATaskTemplate() {
        driver.get(getBASE_URL() + "sys_template.do?sys_id=" + sys_id);
        clearExistingValues();
        //setAField(0, "Short description", "A basic change test");
        //setAField(1, "Description", "A basic change test description");
        setAField(0, "SLA due", "2019-12-06 00:00:00");
        setAField(1, "Delivery plan", "DEFAULT");
        updateButton.click();
        try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ScreenshotUtils.captureSnapshotForAllure(driver);
        
    }
}