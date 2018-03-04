package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;

public class CreateOrEditStaffPage {
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//label[text()='Name']")
	public WebElement nameLabel;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//input[@name='name']")
	public WebElement nameTxtFld;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//label[text()='Branch']")
	public WebElement branchLabel;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//select[@name='related_branch']")
	public WebElement branchDrpDwn;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//button//span[text()='Cancel']")
	public WebElement cancelBtn;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//button//span[text()='Save']")
	public WebElement saveBtn;
	
	@FindBy(xpath=".//*[@id='saveStaffModal']//form//input[@name='name']/following-sibling::div")
	public WebElement nameFieldErrMsg;
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public CreateOrEditStaffPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to set the value for Create or edit Staff details form fields
	 * @param name
	 * @param Code
	 */
	public void setCreateOrEditStaffDetails(String name, String branch){
		performAction.clearTextFieldValue(driver, this.nameTxtFld);
		performAction.setTextFieldValue(driver, this.nameTxtFld, name);
		performAction.clickOnElement(driver, this.branchDrpDwn);
		performAction.selectDrpDwnValueByText(driver, this.branchDrpDwn, branch);
	}
	
	/**
	 * This method is to click on Cancel button
	 */
	public void clickOnCancel(){
		performAction.clickOnElement(driver, this.cancelBtn);
		Log.info("Clicked on Cancel button");
	}
	
	/**
	 * This method is to click on Save button
	 */
	public void clickOnSave(){
		performAction.clickOnElement(driver, this.saveBtn);
		Log.info("Clicked on Save button");
	}
	
	/**
	 * This method is to get the error message displayed for Name field
	 * @return
	 */
	public String getNameFieldErrMsg(){
		String errorMsg = performAction.getText(driver, this.nameFieldErrMsg);
		Log.info("Name field error message is returning as " + errorMsg);
		return errorMsg;
	}
	
	/**
	 * This method is to create a new staff
	 * @param staffName
	 * @param branch
	 * @throws InterruptedException 
	 */
	public void createNewStaff(String staffName, String branch) throws InterruptedException{
		
		NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
		Log.info("Navigation Bar page has been initiated");
	
		StaffPage staffPage = PageFactory.initElements(driver, StaffPage.class);
		Log.info("Staff Page has been initiated");
	
		navigationBar.clickOnStaff();
		
		staffPage.clickOnCreateNewStaff();
		this.setCreateOrEditStaffDetails(staffName, branch);
		this.clickOnSave();
		
		performAction.waitForElementVisible(driver, staffPage.createNewStaffBtn);
		performAction.waitForFewSeconds(driver);
	}
}
