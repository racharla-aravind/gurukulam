package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;

public class DeleteBranchOrStaffPage {
	
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();

	@FindBy(xpath=".//h4[text()='Confirm delete operation']/following::div")
	public WebElement confirmationTxt;
	
	@FindBy(xpath=".//h4[text()='Confirm delete operation']/following::div//span[text()='Cancel']")
	public WebElement cancelBtn;
	
	@FindBy(xpath=".//h4[text()='Confirm delete operation']/following::div//span[text()='Delete']")
	public WebElement deleteBtn;
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public DeleteBranchOrStaffPage(WebDriver driver){
		this.driver = driver;
	} 
	
	
	/**
	 * This method is to get the Confirmation text
	 * @return
	 * @throws InterruptedException 
	 */
	public String getConfirmationText() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		String confirmationText = performAction.getText(driver, this.confirmationTxt);
		Log.info("Confirmation text is displayed as " + confirmationText);
		return confirmationText;
	}
	
	/**
	 * This method is to click on Cancel button
	 * @throws InterruptedException 
	 */
	public void clickOnCancel() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.cancelBtn);
		Log.info("Clicked on Cancel button");
	}
	
	/**
	 * This method is to click on Delete button
	 * @throws InterruptedException 
	 */
	public void clickOnDelete() throws InterruptedException{
		performAction.waitForFewSeconds(driver);
		performAction.clickOnElement(driver, this.deleteBtn);
		Log.info("Clicked on Delete button");
	}
	
}
