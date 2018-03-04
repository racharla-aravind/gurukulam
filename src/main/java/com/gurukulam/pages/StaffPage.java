package com.gurukulam.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;

public class StaffPage {
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//button/span[text()='Create a new Staff']")
	public WebElement createNewStaffBtn;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//form//input[@id='searchQuery']")
	public WebElement queryTxtFld;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//form//button//span[text()='Search a Staff']")
	public WebElement searchStaffBtn;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//table//tr/td//span[text()='View']")
	public WebElement viewBtn;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//table//tr/td//span[text()='Edit']")
	public WebElement editBtn;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//table//tr/td//span[text()='Delete']")
	public WebElement deleteBtn;
	
	@FindBy(xpath="//h2[text() = 'Staffs']/following-sibling::div//table//tr/td")
	public List <WebElement> staffRecords;
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public StaffPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to click on Create a new Staff  button
	 */
	public void clickOnCreateNewStaff(){
		performAction.clickOnElement(driver, this.createNewStaffBtn);
		Log.info("Clicked on Create a new Staff button");
	}
	
	/**
	 * This method is to clear the query text field value
	 */
	public void clearQueryFieldValue(){
		performAction.clearTextFieldValue(driver, this.queryTxtFld);
		Log.info("Query field value has been cleared to ");
	}
	
	/**
	 * This method is to set the query text field value
	 * @param queryString
	 */
	public void setQuery(String queryString){
		performAction.setTextFieldValue(driver, this.queryTxtFld, queryString);
		Log.info("Query field value has been set to " + queryString);
	}
	
	/**
	 * This method is to click on Search a Staff  button
	 */
	public void clickOnSearchStaff(){
		performAction.clickOnElement(driver, this.searchStaffBtn);
		Log.info("Clicked on Search a Staff button");
	}
	
	/**
	 * This method is to search for a staff
	 * @param queryString
	 * @throws InterruptedException 
	 */
	public void searchForAStaff(String queryString) throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		this.clearQueryFieldValue();
		this.setQuery(queryString);
		this.clickOnSearchStaff();
	}
	
	/**
	 * This method is to click on view button
	 * @throws InterruptedException 
	 */
	public void clickOnViewStaff() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.viewBtn);
		Log.info("Clicked on View branch button");
	}
	
	/**
	 * This method is to click on edit button
	 * @throws InterruptedException 
	 */
	public void clickOnEditStaff() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.editBtn);
		Log.info("Clicked on Edit branch button");
	}
	
	/**
	 * This method is to click on delete button
	 * @throws InterruptedException 
	 */
	public void clickOnDeleteStaff() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.deleteBtn);
		Log.info("Clicked on Delete branch button");
	}
	
	/**
	 * This method is to check for the display of search record
	 * @param queryString
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean isStaffRecordDisplayed(String queryString) throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		System.out.println("branchRecords=="+ staffRecords.size());
		boolean flag = false;
		for (WebElement element : staffRecords){
            if(element.getText().equals(queryString)) {
            	flag = true;
            	break;
            }
        }
		return flag;    
	}
	
	/**
	 * This method is to get the count of records displayed
	 * @return
	 */
	public int getCountOfStaffRecords(){
		int noOfRecords = staffRecords.size()/4;
		Log.info("Total number of records displayed are " + noOfRecords);
		return noOfRecords;
	}
}
