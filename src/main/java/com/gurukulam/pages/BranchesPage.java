package com.gurukulam.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;

public class BranchesPage {
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//button/span[text()='Create a new Branch']")
	public WebElement createNewBranchBtn;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//form//input[@id='searchQuery']")
	public WebElement queryTxtFld;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//form//button//span[text()='Search a Branch']")
	public WebElement searchBranchBtn;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//table//tr/td//span[text()='View']")
	public WebElement viewBtn;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//table//tr/td//span[text()='Edit']")
	public WebElement editBtn;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//table//tr/td//span[text()='Delete']")
	public WebElement deleteBtn;
	
	@FindBy(xpath="//h2[text() = 'Branches']/following-sibling::div//table//tr/td")
	public List <WebElement> branchRecords;
	
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public BranchesPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to click on Create a new Branch  button
	 */
	public void clickOnCreateNewBranch(){
		performAction.clickOnElement(driver, this.createNewBranchBtn);
		Log.info("Clicked on Create a new Branch button");
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
	 * @throws InterruptedException 
	 */
	public void setQuery(String queryString) throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.setTextFieldValue(driver, this.queryTxtFld, queryString);
		Log.info("Query field value has been set to " + queryString);
	}
	
	/**
	 * This method is to click on Search a Branch  button
	 * @throws InterruptedException 
	 */
	public void clickOnSearchBranch() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.searchBranchBtn);
		Log.info("Clicked on Search a Branch button");
		performAction.waitForFewSeconds(driver);
	}
	
	/**
	 * This method is to search for a branch
	 * @param queryString
	 * @throws InterruptedException 
	 */
	public void searchForABranch(String queryString) throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		this.clearQueryFieldValue();
		this.setQuery(queryString);
		this.clickOnSearchBranch();
	}
	
	/**
	 * This method is to click on view button
	 * @throws InterruptedException 
	 */
	public void clickOnViewBranch() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.viewBtn);
		Log.info("Clicked on View branch button");
	}
	
	/**
	 * This method is to click on edit button
	 * @throws InterruptedException 
	 */
	public void clickOnEditBranch() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.editBtn);
		Log.info("Clicked on Edit branch button");
	}
	
	/**
	 * This method is to click on delete button
	 * @throws InterruptedException 
	 */
	public void clickOnDeleteBranch() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.deleteBtn);
		Log.info("Clicked on Delete branch button");
	}
	
	/**
	 * This method is to check for the display of search record
	 * @param queryString
	 * @return
	 */
	public boolean isBranchRecordDisplayed(String queryString){
		System.out.println("branchRecords=="+ branchRecords.size());
		boolean flag = false;
		for (WebElement element : branchRecords){
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
	public int getCountOfBranchRecords(){
		int noOfRecords = branchRecords.size()/4;
		Log.info("Total number of records displayed are " + noOfRecords);
		return noOfRecords;
	}
}
