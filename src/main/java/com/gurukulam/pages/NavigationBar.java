package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;

public class NavigationBar {
	
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();

	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Home')]")
	public WebElement homeLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]")
	public WebElement accountDrpDwn;

	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Authenticate')]")
	public WebElement authenticateLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Register')]")
	public WebElement registerLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Settings')]")
	public WebElement settingsLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Password')]")
	public WebElement passwordLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Sessions')]")
	public WebElement sessionsLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Account')]//following::ul//span[contains(text(),'Log out')]")
	public WebElement logoutLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Entities')]")
	public WebElement entitiesDrpDwn;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Entities')]//following::ul//span[contains(text(),'Branch')]")
	public WebElement branchLnk;
	
	@FindBy(xpath=".//*[@id='navbar-collapse']/ul//span[contains(text(),'Entities')]//following::ul//span[contains(text(),'Staff')]")
	public WebElement staffLnk;
	
	
	/**
	 * Constructor - initializes  web driver
	 * @param driver
	 */
	public NavigationBar(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Click on Authenticate link displayed under Account drop down
	 */
	public void clickOnAuthenticate(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.authenticateLnk);
		Log.info("Clicked on Authenticate link");
	}
	
	/**
	 * Click on Register link displayed under Account drop down
	 */
	public void clickOnRegister(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.registerLnk);
		Log.info("Clicked on Register link");
	}
	
	/**
	 * Click on Settings link displayed under Account drop down
	 */
	public void clickOnSettings(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.settingsLnk);
		Log.info("Clicked on Settings link");
	}
	
	/**
	 * Click on Password link displayed under Account drop down
	 */
	public void clickOnPassword(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.passwordLnk);
		Log.info("Clicked on Password link");
	}
	
	/**
	 * Click on Sessions link displayed under Account drop down
	 */
	public void clickOnSessions(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.sessionsLnk);
		Log.info("Clicked on Sessions link");
	}
	
	/**
	 * Click on Branch link displayed under Account drop down
	 */
	public void clickOnBranch(){
		performAction.clickOnElement(driver, this.entitiesDrpDwn);
		Log.info("Clicked on Entities drop down");
		performAction.clickOnElement(driver, this.branchLnk);
		Log.info("Clicked on Branch link");
	}
	
	/**
	 * Click on Staff link displayed under Account drop down
	 */
	public void clickOnStaff(){
		performAction.clickOnElement(driver, this.entitiesDrpDwn);
		Log.info("Clicked on Entities drop down");
		performAction.clickOnElement(driver, this.staffLnk);
		Log.info("Clicked on Staff link");
	}
	
	/**
	 * Click on Log out link displayed under Account drop down
	 */
	public void clickOnLogout(){
		performAction.clickOnElement(driver, this.accountDrpDwn);
		Log.info("Clicked on Account drop down");
		performAction.clickOnElement(driver, this.logoutLnk);
		Log.info("Clicked on Log out link");
	}
}
