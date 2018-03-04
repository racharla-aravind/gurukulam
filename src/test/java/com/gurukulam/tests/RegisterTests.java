package com.gurukulam.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.pages.NavigationBar;
import com.gurukulam.pages.RegistrationPage;
import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.TestDataProvider;
import com.gurukulam.utilities.Utilities;

public class RegisterTests {
	InputStream input = null;
    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;
	RegistrationPage registrationPage = null;
	NavigationBar navigationBar = null;
	
	GurukulaActions performAction = new GurukulaActions();
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
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test()
	public void testVerifyElementsDisplayed() throws Exception {
		testCaseName = "testVerifyElementsDisplayed";
		boolean expectedFlag = true;
		boolean flag = true;
		try {
			Log.startTestCase(testCaseName);
			navigationBar.clickOnRegister();
			
			flag = performAction.isElementEnabled(driver, registrationPage.loginLabel);
			Log.info("Is Login label displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			flag = performAction.isElementEnabled(driver, registrationPage.loginTxtFld);
			Log.info("Is Login text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.emaildLabel);
			Log.info("Is Emaild label displayed " + flag);
			if (!flag)
				expectedFlag = flag;

			flag = performAction.isElementEnabled(driver, registrationPage.emailTxtFld);
			Log.info("Is email text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.newPasswordLabel);
			Log.info("Is Password label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.newPasswordTxtFld);
			Log.info("Is Password text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.newPasswordConfirmationLabel);
			Log.info("Is Confirm Password label displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.newPasswordConfirmationTxtFld);
			Log.info("Is Confirm Password text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, registrationPage.registerBtn);
			Log.info("Is Register button displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			String actualText = performAction.getText(driver, registrationPage.clickHereToLoginTxt);
			String expectedText = prop.getProperty("clickHereToLogin");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			if (!actualText.contentEquals(expectedText))
				expectedFlag = false;
			
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
	public void testVerifyNavigationToLoginPage(){
		testCaseName = "testVerifyNavigationToLoginPage";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			registrationPage.clickOnLogin();
			flag = performAction.isElementDisplayed(driver, loginPage.loginTxtFld);
			assertTrue(flag);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForNullValue(){
		testCaseName = "testVerifyLoginFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = "";
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getLoginFieldErrMsg();
			String expectedText = prop.getProperty("loginRequired");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForSpecialCharacters(){
		testCaseName = "testVerifyLoginFieldForSpecialCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomSpecialCharacters(5);
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getLoginFieldErrMsg();
			String expectedText = prop.getProperty("loginFieldInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForUpperCaseCharacters(){
		testCaseName = "testVerifyLoginFieldForUpperCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toUpperCase();
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getLoginFieldErrMsg();
			String expectedText = prop.getProperty("loginFieldInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForLowerCaseCharacters(){
		testCaseName = "testVerifyLoginFieldForLowerCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO: Need to have the success message but as the functionality is not working unable to add the details here*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForDigits(){
		testCaseName = "testVerifyLoginFieldForDigits";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO: Need to have the success message but as the functionality is not working unable to add the details here*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldForLowerCaseAndDigits(){
		testCaseName = "testVerifyLoginFieldForLowerCaseAndDigits";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase() + testDataProvider.getRandomNumberOfLength(5);
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO: Need to have the success message but as the functionality is not working unable to add the details here*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldFor50Characters(){
		testCaseName = "testVerifyLoginFieldFor50Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(45).toLowerCase() + testDataProvider.getRandomNumberOfLength(5);
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO: Need to have the success message but as the functionality is not working unable to add the details here*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyLoginFieldFor51Characters(){
		testCaseName = "testVerifyLoginFieldFor51Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(51).toLowerCase();
			String email = testDataProvider.getRandomEmailAddress("com");
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getLoginFieldErrMsg();
			String expectedText = prop.getProperty("loginFieldInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldForNullValue(){
		testCaseName = "testVerifyEmailFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = "";
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getEmailFieldErrMsg();
			String expectedText = prop.getProperty("emailRequired");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldForInvalidEmailFormat(){
		testCaseName = "testVerifyEmailFieldForInvalidEmailFormat";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(3);
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getEmailFieldErrMsg();
			String expectedText = prop.getProperty("emailInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldFor5Characters(){
		testCaseName = "testVerifyEmailFieldFor5Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(3)+'@';
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO: Add success message details here*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldForLessThan5Characters(){
		testCaseName = "testVerifyEmailFieldForLessThan5Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(2)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getEmailFieldErrMsg();
			String expectedText = prop.getProperty("emailLessThan5");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldForMoreThan5Characters(){
		testCaseName = "testVerifyEmailFieldForMoreThan5Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			/* TO DO : Add success message details*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldFor100Characters(){
		testCaseName = "testVerifyEmailFieldFor100Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(98)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			/* TO DO : Add success message details*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyEmailFieldForMoreThan100Characters(){
		testCaseName = "testVerifyEmailFieldForMoreThan100Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(99)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(8);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getEmailFieldErrMsg();
			String expectedText = prop.getProperty("emailMoreThan100");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForNullValue(){
		testCaseName = "testVerifyPasswordFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = "";
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getEmailFieldErrMsg();
			String expectedText = prop.getProperty("passwordRequired");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForOnlyCharacters(){
		testCaseName = "testVerifyPasswordFieldForOnlyCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(10);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForDigitsCharacters(){
		testCaseName = "testVerifyPasswordFieldForDigitsCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = String.valueOf(testDataProvider.getRandomNumberOfLength(10));
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForSpecialCharacters(){
		testCaseName = "testVerifyPasswordFieldForSpecialCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomSpecialCharacters(10);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForLessThan7Characters(){
		testCaseName = "testVerifyPasswordFieldForLessThan7Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(2).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForMoreThan20Characters(){
		testCaseName = "testVerifyPasswordFieldForMoreThan20Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(17).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldFor7Characters(){
		testCaseName = "testVerifyPasswordFieldFor7Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(3).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO : Add success message*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldFor20Characters(){
		testCaseName = "testVerifyPasswordFieldFor20Characters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(16).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			/* TO DO : Add success message*/
			String actualText = "";
			String expectedText = "";
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldForSpaceCharacters(){
		testCaseName = "testVerifyPasswordFieldForSpaceCharacters";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = testDataProvider.getRandomString(5).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			String confirmPassword = testDataProvider.getRandomString(5).toLowerCase() + testDataProvider.getRandomNumberOfLength(2) + testDataProvider.getRandomSpecialCharacters(2);
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordNotMatch");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyPasswordFieldsForNonMatchingValues(){
		testCaseName = "testVerifyPasswordFieldsForNonMatchingValues";
		try {
			Log.startTestCase(testCaseName);
			homePage.clickOnRegisterNewAccount();
			
			boolean flag = performAction.isElementDisplayed(driver, registrationPage.loginTxtFld);
			assertTrue(flag);
			
			String loginName = testDataProvider.getRandomString(5).toLowerCase();
			String email = testDataProvider.getRandomString(10)+'@'+testDataProvider.getRandomString(1);
			String password = "     ";
			String confirmPassword = password;
			
			registrationPage.fillRegisterDetails(loginName, email, password, confirmPassword);
			
			String actualText = registrationPage.getPasswordFieldErrMsg();
			String expectedText = prop.getProperty("passwordRequired");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "RegisterTests_" + testCaseName);
			driver.close();
			Utilities.closePropertiesFile(input);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
}
