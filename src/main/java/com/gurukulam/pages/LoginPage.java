package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.Log;

public class LoginPage {
	
	WebDriver driver = null;
	Actions performAction = new Actions();
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::div[@class='alert alert-danger ng-scope']")
	public WebElement loginErrorMSg;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//label[text()='Login']")
	public WebElement loginLabel;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//input[@id='username']")
	public WebElement loginTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//label[text()='Password']")
	public WebElement passwordLabel;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//input[@id='password']")
	public WebElement passwordTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//span[text()='Automatic Login']")
	public WebElement rememberMeLabel;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//input[@id='rememberMe']")
	public WebElement rememberMeChkBx;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::form//button[text()='Authenticate']")
	public WebElement authenticateBtn;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::div//a[text()='Did you forget your password?']")
	public WebElement forgotPasswordLnk;
	
	@FindBy(xpath="//h1[text() = 'Authentication']/following-sibling::div//a[text()='Register a new account']")
	public WebElement registerNewAccountLnk;
	
	@FindBy(xpath="(//h1[text() = 'Authentication']/following-sibling::div)[3]")
	public WebElement dontHaveAnAccountTxt;
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to fill the authentication fields
	 * @param driver
	 * @param username
	 * @param password
	 * @param rememberMe
	 */
	public void setAuthenticationDetails(String username, String password, boolean rememberMe){
		performAction.clearTextFieldValue(driver, this.loginTxtFld);
		performAction.setTextFieldValue(driver, this.loginTxtFld, username);
		performAction.clearTextFieldValue(driver, this.passwordTxtFld);
		performAction.setTextFieldValue(driver, this.passwordTxtFld, password);
		
		if (rememberMe) {
			if (!this.rememberMeChkBx.isSelected()) {
				performAction.clickOnElement(driver, this.rememberMeChkBx);
			}
		} else if (!rememberMe) {
			if (this.rememberMeChkBx.isSelected()) {
				performAction.clickOnElement(driver, this.rememberMeChkBx);
			}
		}
	}
	
	/**
	 * This method is to click on Authenticate button
	 */
	public void clickOnAuthenticate(){
		performAction.clickOnElement(driver, this.authenticateBtn);
		Log.info("Clicked on Authenticate button");
	}
	
	/**
	 * This method is to click on Did you forget your password link
	 */
	public void clickOnForgotPassword(){
		performAction.clickOnElement(driver, this.forgotPasswordLnk);
		Log.info("Clicked on Did you forget your password link");
	}
	
	/**
	 * This method is to click on Register a new account link
	 */
	public void clickOnRegisterNewAccount(){
		performAction.clickOnElement(driver, this.registerNewAccountLnk);
		Log.info("Clicked on Register a new account link");
	}
	
	public String getLoginErrorMsg(){
		String errorMsg = performAction.getText(driver, this.loginErrorMSg);
		Log.info("Returning error message as " + errorMsg);
		return errorMsg;
	}

}
