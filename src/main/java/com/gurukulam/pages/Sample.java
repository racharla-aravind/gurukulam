package com.gurukulam.pages;

import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Sample {
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="rememberMe")
	private WebElement autoLoginCheckBox;
	
	@FindBy(xpath=".//button[contains(text(),'Authenticate')]")
	private WebElement authenticateButton;
	
	@FindBy(partialLinkText="Did you forget your password?")
	private WebElement forgotPasswordLink;
	
	@FindBy(partialLinkText="Register a new account")
	private WebElement registerLink;
	
	private WebDriver driver;
	
	/**
	 * Constructor to initiate the page class using web driver instance
	 */
	public Sample(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * This method is to launch the page using webdriver's get method
	 */
	 	
	public void launchLoginPage(String url) throws Exception {
		if(url != null) {
			driver.get(url);
		} else {
			throw new Exception("URL must not be null");
		}
	}
	
	/**
	 * This method is to fill the user name or login id
	 * @throws : NoSuchElementException, Exception
	 */
	public void fillUsername(String username) {
		try {
			if(username != null) {
				WebDriverWait myWait = new WebDriverWait(driver,90);
				myWait.until(ExpectedConditions.elementToBeClickable(authenticateButton));
				this.username.clear();
				this.username.click();
				this.username.sendKeys(username);
			} else {
				throw new Exception("Please enter username");
			}
		} catch (NoSuchElementException e){
			
		} catch (Exception e) {
		
		}
	}
	
	/**
	 * This method is to fill the passpord
	 * @throws : NoSuchElementException, Exception
	 */
	public void fillPassword(String pwd) {
		try {
			if(password != null) {
				WebDriverWait myWait = new WebDriverWait(driver,90);
				myWait.until(ExpectedConditions.elementToBeClickable(authenticateButton));
				this.password.clear();
				this.password.click();
				this.password.sendKeys(pwd);
			} else {
				throw new Exception("Please enter password");
			}
		} catch (NoSuchElementException e) {
			
		} catch (Exception e) {
			
		}
	}
	
	
	/**
	 * This method is to click on authenticate button/link
	 * @throws : NoSuchElementException, Exception
	 */
	public void clickAuthenticate() {
		try {
			authenticateButton.click();
			
		} catch (NoSuchElementException e) {
		
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is to authenticate the user

	 * @return GurukulaWelcomePage object
	 * @throws : NoSuchElementException, Exception
	 */
	public void authenticate(String usrname, String pwd) {
		try {
			fillUsername(usrname);
			fillPassword(pwd);
			clickAuthenticate();
	
		} catch (NoSuchElementException e) {

			throw e;
		} catch (Exception e) {
			
			throw e;
		}
	}
}
