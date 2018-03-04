package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.Log;

public class RegistrationPage {
	
	WebDriver driver = null;
	Actions performAction = new Actions();
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//label[text()='Login']")
	public WebElement loginLabel;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='login']")
	public WebElement loginTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//label[text()='E-mail']")
	public WebElement emaildLabel;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='email']")
	public WebElement emailTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//label[text()='New password']")
	public WebElement newPasswordLabel;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='password']")
	public WebElement newPasswordTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//label[text()='New password confirmation']")
	public WebElement newPasswordConfirmationLabel;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='confirmPassword']")
	public WebElement newPasswordConfirmationTxtFld;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//button[text()='Register']")
	public WebElement registerBtn;
	
	@FindBy(xpath="(//h1[text() = 'Registration']/following-sibling::div)[6]")
	public WebElement clickHereToLoginTxt;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::div//a[text()='login']")
	public WebElement loginLnk;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='login']/following-sibling::div")
	public WebElement loginFieldErrMsg;
	
	@FindBy(xpath="//h1[text() = 'Registration']/following-sibling::form//input[@name='email']/following-sibling::div")
	public WebElement emailFieldErrMsg;
	
	@FindBy(xpath="(//h1[text() = 'Registration']/following-sibling::div)[2]")
	public WebElement passwordErrMsg;
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method is to set the value for login field
	 * @param loginName
	 */
	public void setLogin(String loginName){
		performAction.clearTextFieldValue(driver, this.loginTxtFld);
		performAction.setTextFieldValue(driver, this.loginTxtFld, loginName);
		Log.info("Login Name field value has been set to " + loginName);
	}
	
	/**
	 * This method is to set the value for email field
	 * @param email
	 */
	public void setEmail(String email){
		performAction.clearTextFieldValue(driver, this.emailTxtFld);
		performAction.setTextFieldValue(driver, this.emailTxtFld, email);
		Log.info("Email field value has been set to " + email);
	}
	
	/**
	 * This method is to set the value for password field
	 * @param password
	 */
	public void setPassword(String password){
		performAction.clearTextFieldValue(driver, this.newPasswordTxtFld);
		performAction.setTextFieldValue(driver, this.newPasswordTxtFld, password);
		Log.info("Password field value has been set to " + password);
	}
	
	/**
	 * This method is to set the value for password confirmation field
	 * @param password
	 */
	public void setPasswordConfirmation(String password){
		performAction.clearTextFieldValue(driver, this.newPasswordConfirmationTxtFld);
		performAction.setTextFieldValue(driver, this.newPasswordTxtFld, password);
		Log.info("Password Confirmation field value has been set to " + password);
	}
	
	/**
	 * This method is to click on the Register button
	 */
	public void clickOnRegister(){
		performAction.clickOnElement(driver, this.registerBtn);
		Log.info("Clicked on Register button");
	}
	
	/**
	 * This method is to click on the Login link
	 */
	public void clickOnLogin(){
		performAction.clickOnElement(driver, this.loginLnk);
		Log.info("Clicked on Login link");
	}
	
	/**
	 * This method is to get the error message displayed for Login field
	 * @return
	 */
	public String getLoginFieldErrMsg(){
		String errorMsg = performAction.getText(driver, this.loginFieldErrMsg);
		Log.info("Login field error message is returning as " + errorMsg);
		return errorMsg;
	}
	
	/**
	 * This method is to get the error message displayed for Email field
	 * @return
	 */
	public String getEmailFieldErrMsg(){
		String errorMsg = performAction.getText(driver, this.emailFieldErrMsg);
		Log.info("Email field error message is returning as " + errorMsg);
		return errorMsg;
	}
	
	/**
	 * This method is to get the error message displayed for Password field
	 * @return
	 */
	public String getPasswordFieldErrMsg(){
		String errorMsg = performAction.getText(driver, this.passwordErrMsg);
		Log.info("Password field error message is returning as " + errorMsg);
		return errorMsg;
	}
	
	/**
	 * This method is to fill the details in Register form
	 * @param loginName
	 * @param email
	 * @param password
	 * @param confirmPassword
	 */
	public void fillRegisterDetails(String loginName, String email, String password, String confirmPassword){
		this.setLogin(loginName);
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordConfirmation(confirmPassword);
	}

}
