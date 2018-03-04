package com.gurukulam.tests;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gurukulam.pages.AuthenticatedPage;
import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.pages.NavigationBar;
import com.gurukulam.pages.RegistrationPage;
import com.gurukulam.pages.ResetYourPasswordPage;
import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.TestDataProvider;
import com.gurukulam.utilities.Utilities;

public class ResetYourPasswordTests {
	InputStream input = null;
    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;
	RegistrationPage registrationPage = null;
	NavigationBar navigationBar = null;
	AuthenticatedPage authenticatedPage = null;
	ResetYourPasswordPage resetYourPasswordPage = null;
	
	Actions performAction = new Actions();
	Properties prop = new Properties();
	TestDataProvider testDataProvider = new TestDataProvider();
	
	String testCaseName = null;

	@Before
	public void setup() {
		try {
			input = Utilities.loadProperties();
			prop.load(input);
			driver = GetDriver.getDriver();

			homePage = PageFactory.initElements(driver, HomePage.class);
			Log.info("Home Page has been initiated");

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			Log.info("Login Page has been initiated");
			
			registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
			Log.info("Registration Page has been initiated");
			
			navigationBar = PageFactory.initElements(driver, NavigationBar.class);
			Log.info("Navigation Bar page has been initiated");
			
			authenticatedPage = PageFactory.initElements(driver, AuthenticatedPage.class);
			Log.info("Authenticated Page has been initiated");
			
			resetYourPasswordPage = PageFactory.initElements(driver, ResetYourPasswordPage.class);
			Log.info("Reset Your Password Page has been initiated");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyElementsDisplayed(){
		testCaseName = "testVerifyElementsDisplayed";
		boolean expectedFlag = true;
		boolean flag = true;
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			String actualText = performAction.getText(driver, resetYourPasswordPage.defaultInstructionalTxt);
			String expectedText = prop.getProperty("email_instructionalTxt");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			if (!actualText.contentEquals(expectedText))
				expectedFlag = false;
			
			flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailLabel);
			Log.info("Is Email label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			Log.info("Is Email text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			if (expectedFlag) {
				Log.info("All the expected elements are displayed");
				assertTrue(expectedFlag);
			} else
				fail("One or more expected elements are not available on the Home Page");
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyResetYourPasswordForNullValue(){
		testCaseName = "testVerifyResetYourPasswordForNullValue";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			boolean flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			assertTrue(flag);
			String email = " ";
			resetYourPasswordPage.setEmail(email);
			
			String actualText = performAction.getText(driver, resetYourPasswordPage.resetPasswordErrorMsgRequired);
			String expectedText = prop.getProperty("email_null");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			actualText = performAction.getText(driver, resetYourPasswordPage.defaultInstructionalTxt);
			expectedText = prop.getProperty("email_instructionalTxt");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyResetYourPasswordForInvalidFormat(){
		testCaseName = "testVerifyResetYourPasswordForInvalidFormat";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			boolean flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			assertTrue(flag);
			String email = testDataProvider.getRandomString(10);
			resetYourPasswordPage.setEmail(email);
			
			String actualText = performAction.getText(driver, resetYourPasswordPage.resetPasswordErrorMsgInvalidFormat);
			String expectedText = prop.getProperty("email_invalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			actualText = performAction.getText(driver, resetYourPasswordPage.defaultInstructionalTxt);
			expectedText = prop.getProperty("email_instructionalTxt");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyResetYourPasswordForNonRegisteredEmail(){
		testCaseName = "testVerifyResetYourPasswordForNonRegisteredEmail";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			boolean flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			assertTrue(flag);
			String email = testDataProvider.getRandomEmailAddress("com");
			resetYourPasswordPage.setEmail(email);
			resetYourPasswordPage.clickOnResetPassword();
		
		
			String actualText = resetYourPasswordPage.getInstructionalText();
			String expectedText = prop.getProperty("email_unregistered");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyResetYourPasswordForRegisteredEmail(){
		testCaseName = "testVerifyResetYourPasswordForRegisteredEmail";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			boolean flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			assertTrue(flag);
			String email = prop.getProperty("registeredEmail");
			resetYourPasswordPage.setEmail(email);
			resetYourPasswordPage.clickOnResetPassword();
			
			String actualText = resetYourPasswordPage.getInstructionalText();
			String expectedText = prop.getProperty("resetYourPassword");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "ResetYourPasswordTests_" + testCaseName);
			driver.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

}
