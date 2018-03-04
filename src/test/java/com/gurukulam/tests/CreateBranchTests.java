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

import com.gurukulam.pages.AuthenticatedPage;
import com.gurukulam.pages.BranchesPage;
import com.gurukulam.pages.CreateOrEditBranchPage;
import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.pages.NavigationBar;
import com.gurukulam.pages.RegistrationPage;
import com.gurukulam.utilities.Actions;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.TestDataProvider;
import com.gurukulam.utilities.Utilities;

public class CreateBranchTests {

	InputStream input = null;
    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;
	RegistrationPage registrationPage = null;
	NavigationBar navigationBar = null;
	AuthenticatedPage authenticatedPage = null;
	BranchesPage branchesPage = null;
	CreateOrEditBranchPage createOrEditBranchPage = null;
	
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
			
			branchesPage = PageFactory.initElements(driver, BranchesPage.class);
			Log.info("Reset Your Password Page has been initiated");
			
			createOrEditBranchPage = PageFactory.initElements(driver, CreateOrEditBranchPage.class);
			Log.info("Create or Edit Branch Page has been initiated");
			
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
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			flag = performAction.isElementEnabled(driver, branchesPage.createNewBranchBtn);
			Log.info("Is Create a new Branch displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, branchesPage.queryTxtFld);
			Log.info("Is Query text field displayed " + flag);
			if (!flag)
				expectedFlag = flag;
			
			flag = performAction.isElementEnabled(driver, branchesPage.searchBranchBtn);
			Log.info("Is Search a Branch displayed " + flag);
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
	public void testVerifyNameFieldForNullValue(){
		testCaseName = "testVerifyNameFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = "";
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForLessThan5Characters(){
		testCaseName = "testVerifyNameFieldForLessThan5Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(4);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("branchNameLessThan5");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForMoreThan20Characters(){
		testCaseName = "testVerifyNameFieldForMoreThan20Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(21);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("branchNameMoreThan20");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForInvalidCharacters(){
		testCaseName = "testVerifyNameFieldForInvalidCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("branchNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForDigits(){
		testCaseName = "testVerifyNameFieldForDigits";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = String.valueOf(testDataProvider.getRandomNumberOfLength(6));
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("branchNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForSpecialCharacters(){
		testCaseName = "testVerifyNameFieldForSpecialCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomSpecialCharacters(6);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("branchNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForSpaceCharacters(){
		testCaseName = "testVerifyNameFieldForSpaceCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = "      ";
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testVerifyCodeFieldForNullValue(){
		testCaseName = "testVerifyCodeFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = "";
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForLessThan2Characters(){
		testCaseName = "testVerifyCodeFieldForLessThan2Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = testDataProvider.getRandomString(1);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("branchCodeLessThan2");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForMoreThan10Characters(){
		testCaseName = "testVerifyCodeFieldForMoreThan10Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = testDataProvider.getRandomString(11);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("branchCodeMoreThan10");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForInvalidCharacters(){
		testCaseName = "testVerifyCodeFieldForInvalidCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = testDataProvider.getRandomString(2)+testDataProvider.getRandomNumberOfLength(2)+testDataProvider.getRandomSpecialCharacters(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("branchCodeInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForSpaceCharacters(){
		testCaseName = "testVerifyCodeFieldForSpaceCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = "          ";
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
				
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForLowerCaseCharacters(){
		testCaseName = "testVerifyCodeFieldForLowerCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = testDataProvider.getRandomString(5).toLowerCase();
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			
			String actualText = createOrEditBranchPage.getCodeFieldErrMsg();
			String expectedText = prop.getProperty("branchCodeInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			createOrEditBranchPage.clickOnCancel();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldFor5Characters(){
		testCaseName = "testVerifyNameFieldFor5Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(5);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);;
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForMoreThan5LessThan20Characters(){
		testCaseName = "testVerifyNameFieldForMoreThan5LessThan20Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(6);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);;
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldFor20Characters(){
		testCaseName = "testVerifyNameFieldFor20Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(20);
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);;
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForLowerCaseCharacters(){
		testCaseName = "testVerifyNameFieldForLowerCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10).toLowerCase();
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);;
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForUpperCaseCharacters(){
		testCaseName = "testVerifyNameFieldForUpperCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10).toUpperCase();
			String code = testDataProvider.getRandomString(4)+testDataProvider.getRandomNumberOfLength(2);;
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldFor2Characters(){
		testCaseName = "testVerifyCodeFieldFor2Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = testDataProvider.getRandomString(2);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForMoreThan2AndLessThan10Characters(){
		testCaseName = "testVerifyCodeFieldForMoreThan2AndLessThan10Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = testDataProvider.getRandomString(5);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldFor10Characters(){
		testCaseName = "testVerifyCodeFieldFor10Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = testDataProvider.getRandomString(10);
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCodeFieldForDigits(){
		testCaseName = "testVerifyCodeFieldForDigits";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testVerifyCodeFieldForUpperCaseCharacters(){
		testCaseName = "testVerifyCodeFieldForUpperCaseCharacters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnBranch();
			
			String name = testDataProvider.getRandomString(10);
			String code = testDataProvider.getRandomString(5).toUpperCase();
			
			branchesPage.clickOnCreateNewBranch();
			createOrEditBranchPage.setCreateOrEditBranchDetails(name, code);
			createOrEditBranchPage.clickOnSave();
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "CreateBranchTests_" + testCaseName);
			if (isAuthenticated)
				navigationBar.clickOnLogout();
			driver.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
}
