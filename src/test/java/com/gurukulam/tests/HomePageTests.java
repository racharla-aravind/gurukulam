package com.gurukulam.tests;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.pages.NavigationBar;
import com.gurukulam.pages.RegistrationPage;
import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.Utilities;

public class HomePageTests {

	InputStream input = null;
    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;
	RegistrationPage registrationPage = null;
	NavigationBar navigationBar = null;
	GurukulaActions performAction = new GurukulaActions();
	Properties prop = new Properties();
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
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test()
	public void testVerifyElementsDisplayed() {
		testCaseName = "testVerifyElementsDisplayed";
		boolean expectedFlag = true;
		boolean flag = true;
		try {
			Log.startTestCase(testCaseName);
			flag = performAction.isElementEnabled(driver, homePage.loginLnk);
			Log.info("Is Login link displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			flag = performAction.isElementEnabled(driver, homePage.registerNewAccount);
			Log.info("Is Register a new account link displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			flag = performAction.isElementEnabled(driver, navigationBar.homeLnk);
			Log.info("Is Home link displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			flag = performAction.isElementEnabled(driver, navigationBar.accountDrpDwn);
			Log.info("Is Account drop down displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			String actualText = performAction.getText(driver, homePage.clickHereToLoginTxt);
			String expectedText = prop.getProperty("clickHereToLogin");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			if (!actualText.contentEquals(expectedText))
				expectedFlag = false;

			actualText = performAction.getText(driver, homePage.youDontHaveAnAccountTxt);
			expectedText = prop.getProperty("dontHaveAnAccount");
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

	@Test()
	public void testVerifyLoginLinkFunctionality() throws Exception {
		testCaseName = "testVerifyLoginLinkFunctionality";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnLogin();
			boolean flag = performAction.isElementDisplayed(driver, loginPage.loginTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test()
	public void testVerifyAuthenticateLinkFunctionality() throws Exception {
		testCaseName = "testVerifyAuthenticateLinkFunctionality";
		try {
			Log.startTestCase(testCaseName);
			navigationBar.clickOnAuthenticate();
			WebDriverWait myWait = new WebDriverWait(driver, 10);
			myWait.until(ExpectedConditions.visibilityOf(loginPage.loginTxtFld));
			boolean flag = performAction.isElementDisplayed(driver, loginPage.loginTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test()
	public void testVerifyRegisterNewAccountLinkFunctionality() throws Exception {
		testCaseName = "testVerifyRegisterNewAccountLinkFunctionality";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test()
	public void testVerifyRegisterLinkFunctionality() throws Exception {
		testCaseName = "testVerifyRegisterLinkFunctionality";
		try {
			Log.startTestCase(testCaseName);
			navigationBar.clickOnRegister();
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "HomePageTests_" + testCaseName);
			driver.close();
			Utilities.closePropertiesFile(input);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

}
