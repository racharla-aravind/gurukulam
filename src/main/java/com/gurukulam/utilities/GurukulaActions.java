package com.gurukulam.utilities;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GurukulaActions {
	
	/**
	 * This method is to perform a click operation on the web element
	 * @param driver
	 * @param element
	 */
	public void clickOnElement(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		Log.info("Performed a click operation on the webelement");
	}

	/**
	 * This method is to perform a check on element display and return true/false
	 * @param driver
	 * @param element
	 * @return flag
	 */
	public boolean isElementDisplayed(WebDriver driver, WebElement element){
		try{
			element.isDisplayed();
			Log.info("Performed an element display check and element is displayed");
			return true;
		} catch (Exception e){
			Log.info("Performed an element display check and element is NOT displayed");
			return false;
		}
	}
	
	/**
	 * This method is to perform a check on element enabled and return true/false
	 * @param driver
	 * @param element
	 * @return flag
	 */
	public boolean isElementEnabled(WebDriver driver, WebElement element){
		try{
			element.isEnabled();
			Log.info("Performed an element displayed and enabled check and element is enabled");
			return true;
		} catch (Exception e){
			Log.info("Performed an element displayed and enabled check and element is NOT enabled");
			return false;
		}
	}
	
	/**
	 * This method is to perform an explicit wait for an element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisible(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is to perform - setting a value to a text field element
	 * @param driver
	 * @param element
	 * @param testData
	 */
	public void setTextFieldValue(WebDriver driver, WebElement element, String testData){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("Performing an action set test data value " + testData + " to a text field");
			element.sendKeys(testData);
			Log.info("Performed an action setting test data value " + testData + " to a text field");
		} catch (Exception e) {
			Log.error("Problem entering a value");
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * This method is to perform - clearing a value from a text field element
	 * @param driver
	 * @param element
	 */
	public void clearTextFieldValue(WebDriver driver, WebElement element){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("Performing an action clear test data value from a text field");
			element.clear();
			Log.info("Performed an action clearing test data value from a text field");
		} catch (Exception e) {
			Log.error("Problem clearing field value");
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * This method is to perform - get a text value of an element
	 * @param driver
	 * @param element
	 */
	public String getText(WebDriver driver, WebElement element){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("Performing an action get a text value of an element");
			String text = element.getText();
			Log.info("Performed an action getting a text value of an element and text value is returning as " + text);
			return text;
		} catch (Exception e) {
			Log.error("Problem getting field text value");
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * This method is to select the drop down value by visible text
	 * @param driver
	 * @param element
	 * @param option
	 */
	public void selectDrpDwnValueByText(WebDriver driver, WebElement element, String option){
		try {
			Log.info("Performing an action select drop down value");
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(option);
			Log.info("Performed an action select drop down value");
		}  catch (Exception e) {
			Log.error("Problem selecting drop down value");
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * TODO - Try to avoid by using explict waits
	 * This method is for wait - wait time is 5 seconds 
	 * @throws InterruptedException 
	 * */
	public void waitForFewSeconds(WebDriver driver) throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}
	
	/**
	 * This method is to check for the alert display
	 * @param driver
	 * @return
	 */
	public boolean isAlertDisplayed(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}
}
