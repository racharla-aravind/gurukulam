package com.gurukulam.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.Utilities;

public class HomePage {
	
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();
	
	@FindBy(xpath="//h1[text() = 'Welcome to Gurukula!']/following-sibling::div/div")
	public WebElement clickHereToLoginTxt;
	
	@FindBy(xpath="(//h1[text() = 'Welcome to Gurukula!']/following-sibling::div/div)[2]")
	public WebElement youDontHaveAnAccountTxt;
	
	@FindBy(xpath="//h1[text() = 'Welcome to Gurukula!']/following-sibling::div//a[text()='login']")
	public WebElement loginLnk;
	
	@FindBy(xpath="//h1[text() = 'Welcome to Gurukula!']/following-sibling::div//a[text()='Register a new account']")
	public WebElement registerNewAccount;
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 * @throws IOException 
	 */
	public HomePage(WebDriver driver) throws IOException{
		this.driver = driver;
		Properties prop = new Properties();
	    InputStream input = Utilities.loadProperties();
		prop.load(input);
		this.driver.get(prop.getProperty("loginUrl"));
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
	 * Click on Login link
	 */
	public void clickOnLogin(){
		performAction.clickOnElement(driver, this.loginLnk);
		Log.info("Clicked on Login link");
	}
	
	/**
	 * Click on Register a new account link
	 */
	public void clickOnRegisterNewAccount(){
		performAction.clickOnElement(driver, this.registerNewAccount);
		Log.info("Clicked on Register a new account link");
	}
}
