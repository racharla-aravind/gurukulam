package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.Log;

public class CreateOrEditBranchPage {
	WebDriver driver = null;
	Actions performAction = new Actions();
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//label[text()='Name']")
	public WebElement nameLabel;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//input[@name='name']")
	public WebElement nameTxtFld;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//label[text()='Code']")
	public WebElement codeLabel;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//input[@name='code']")
	public WebElement codeTxtFld;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//button//span[text()='Cancel']")
	public WebElement cancelBtn;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//button//span[text()='Save']")
	public WebElement saveBtn;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//input[@name='name']/following-sibling::div")
	public WebElement nameFieldErrMsg;
	
	@FindBy(xpath=".//*[@id='saveBranchModal']//form//input[@name='code']/following-sibling::div")
	public WebElement codeFieldErrMsg;
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public CreateOrEditBranchPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to set the value for Create or edit Branch details form fields
	 * @param name
	 * @param Code
	 */
	public void setCreateOrEditBranchDetails(String name, String code){
		performAction.clearTextFieldValue(driver, this.nameTxtFld);
		performAction.setTextFieldValue(driver, this.nameTxtFld, name);
		performAction.clearTextFieldValue(driver, this.codeTxtFld);
		performAction.setTextFieldValue(driver, this.codeTxtFld, code);
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
	 * This method is to get the error message displayed for Code field
	 * @return
	 */
	public String getCodeFieldErrMsg(){
		String errorMsg = performAction.getText(driver, this.codeFieldErrMsg);
		Log.info("Code field error message is returning as " + errorMsg);
		return errorMsg;
	}
	
	/**
	 * This method is to create a new branch
	 * @param name
	 * @param code
	 * @throws InterruptedException
	 */
	public void createNewBranch(String name, String code) throws InterruptedException{
		
		NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
		Log.info("Navigation Bar page has been initiated");
		
		BranchesPage branchesPage = PageFactory.initElements(driver, BranchesPage.class);
		Log.info("Branch Page has been initiated");
		
		navigationBar.clickOnBranch();
	
		branchesPage.clickOnCreateNewBranch();
		this.setCreateOrEditBranchDetails(name, code);
		this.clickOnSave();
		
		performAction.waitForElementVisible(driver, branchesPage.createNewBranchBtn);
		performAction.waitForFewSeconds(driver);
	}
}
