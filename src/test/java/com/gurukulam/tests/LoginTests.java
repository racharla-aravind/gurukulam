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

public class LoginTests {
	
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
	
	boolean isAuthenticated = false;
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
			
			isAuthenticated = false;
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
			performAction.waitForElementVisible(driver, loginPage.loginTxtFld);
			flag = performAction.isElementEnabled(driver, loginPage.loginLabel);
			Log.info("Is Login label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.loginTxtFld);
			Log.info("Is Login text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.passwordLabel);
			Log.info("Is Password label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.passwordTxtFld);
			Log.info("Is Password text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.rememberMeChkBx);
			Log.info("Is Remember me check box displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.rememberMeLabel);
			Log.info("Is Remember me label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.authenticateBtn);
			Log.info("Is Authenticate button displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, loginPage.forgotPasswordLnk);
			Log.info("Is Did you forget your password? link displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			String actualText = performAction.getText(driver, loginPage.dontHaveAnAccountTxt);
			String expectedText = prop.getProperty("dontHaveAnAccount");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			if (!actualText.contentEquals(expectedText))
				flag = false;

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
	public void testVerifyAuthenticationWithNullValues(){
		testCaseName = "testVerifyAuthenticationWithNullValues";
		try {
			Log.startTestCase(testCaseName);
			String username = "";
			String password = "";
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			String actualText = loginPage.getLoginErrorMsg();
			String expectedText = prop.getProperty("invalidLoginCredentials");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			isAuthenticated = performAction.isElementDisplayed(driver, authenticatedPage.successfulLoggedInMsg);
			assertFalse(isAuthenticated);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyAuthenticationWithInvalidCredentails(){
		testCaseName = "testVerifyAuthenticationWithInvalidUsername";
		try {
			Log.startTestCase(testCaseName);
			String username = testDataProvider.getRandomString(8);
			String password = testDataProvider.getRandomString(8);
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			String actualText = loginPage.getLoginErrorMsg();
			String expectedText = prop.getProperty("invalidLoginCredentials");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			isAuthenticated = performAction.isElementDisplayed(driver, authenticatedPage.successfulLoggedInMsg);
			assertFalse(isAuthenticated);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyAuthenticationWithInvalidUsername(){
		testCaseName = "testVerifyAuthenticationWithInvalidUsername";
		try {
			Log.startTestCase(testCaseName);
			String username = testDataProvider.getRandomString(8);
			String password = prop.getProperty("password"); 
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			String actualText = loginPage.getLoginErrorMsg();
			String expectedText = prop.getProperty("invalidLoginCredentials");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			isAuthenticated = performAction.isElementDisplayed(driver, authenticatedPage.successfulLoggedInMsg);
			assertFalse(isAuthenticated);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testVerifyAuthenticationWithInvalidPassword(){
		testCaseName = "testVerifyAuthenticationWithInvalidPassword";
		try {
			Log.startTestCase(testCaseName);
			String username = prop.getProperty("username");
			String password = testDataProvider.getRandomString(8);
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			String actualText = loginPage.getLoginErrorMsg();
			String expectedText = prop.getProperty("invalidLoginCredentials");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			isAuthenticated = performAction.isElementDisplayed(driver, authenticatedPage.successfulLoggedInMsg);
			assertFalse(isAuthenticated);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void successfulLogin(){
		testCaseName = "successfulLogin";
		try {
			Log.startTestCase(testCaseName);
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			String actualText = performAction.getText(driver, authenticatedPage.successfulLoggedInMsg);
			String expectedText = prop.getProperty("successfulLogin") + " \"" + username + "\".";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(actualText, expectedText);
			isAuthenticated = true;
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyForgotPasswordFunctionality(){
		testCaseName = "testVerifyForgotPasswordFunctionality";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnForgotPassword();
			boolean flag = performAction.isElementEnabled(driver, resetYourPasswordPage.emailTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyRegisterNewAccountFunctionality(){
		testCaseName = "testVerifyRegisterNewAccountFunctionality";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			loginPage.clickOnRegisterNewAccount();
			boolean flag = performAction.isElementEnabled(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "LoginTests_" + testCaseName);
			if (isAuthenticated)
				navigationBar.clickOnLogout();
			driver.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

}
