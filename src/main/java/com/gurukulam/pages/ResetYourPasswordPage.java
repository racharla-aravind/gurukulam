package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.Log;

public class ResetYourPasswordPage {
	
	WebDriver driver = null;
	Actions performAction = new Actions();
	
	@FindBy(xpath="//h1[text() = 'Reset your password']/following-sibling::div")
	public WebElement instructionalTxt;
	
	@FindBy(xpath="(//h1[text() = 'Reset your password']/following-sibling::div)[2]")
	public WebElement defaultInstructionalTxt;
	
	@FindBy(xpath="//h1[text() = 'Reset your password']/following-sibling::form//label[text()='E-mail']")
	public WebElement emailLabel;
	
	@FindBy(xpath="//h1[text() = 'Reset your password']/following-sibling::form//input[@name='email']")
	public WebElement emailTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Reset your password']/following-sibling::form//button[text()='Reset password']")
	public WebElement resetPasswordBtn;
	
	@FindBy(xpath="//h1[text() = 'Reset your password']/following-sibling::form//div/p")
	public WebElement resetPasswordErrorMsgRequired;
	
	@FindBy(xpath="(//h1[text() = 'Reset your password']/following-sibling::form//div/p)[2]")
	public WebElement resetPasswordErrorMsgInvalidFormat;
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public ResetYourPasswordPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to clear the Email text field value
	 * @param email
	 */
	public void clearEmail(String email){
		performAction.clearTextFieldValue(driver, this.emailTxtFld);
		Log.info("Email field value has been cleared");
	}
	
	/**
	 * This method is to set the Email text field value
	 * @param email
	 */
	public void setEmail(String email){
		performAction.clearTextFieldValue(driver, this.emailTxtFld);
		Log.info("Email field value has been cleared");
		performAction.setTextFieldValue(driver, this.emailTxtFld, email);
		Log.info("Email field value has been set to " + email);
	}
	
	/**
	 * This method is to perform a click on Reset Password button
	 */
	public void clickOnResetPassword(){
		performAction.clickOnElement(driver, this.resetPasswordBtn);
		Log.info("Clicked on Reset Password button");
	}
	
	/**
	 * This method is to get the instructional text
	 * @return
	 */
	public String getInstructionalText(){
		String instructionalText = performAction.getText(driver, this.instructionalTxt);
		Log.info("Instructional text has been displayed as " + instructionalText);
		return instructionalText;
	}
}
