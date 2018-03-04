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
import com.gurukulam.pages.CreateOrEditStaffPage;
import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.pages.NavigationBar;
import com.gurukulam.pages.RegistrationPage;
import com.gurukulam.pages.StaffPage;
import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.TestDataProvider;
import com.gurukulam.utilities.Utilities;

public class CreateStaffTests {
	InputStream input = null;
    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;
	RegistrationPage registrationPage = null;
	NavigationBar navigationBar = null;
	AuthenticatedPage authenticatedPage = null;
	BranchesPage branchesPage = null;
	CreateOrEditBranchPage createOrEditBranchPage = null;
	StaffPage staffPage = null;
	CreateOrEditStaffPage createOrEditStaffPage = null;
	
	GurukulaActions performAction = new GurukulaActions();
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
			Log.info("Branch Page has been initiated");
			
			createOrEditBranchPage = PageFactory.initElements(driver, CreateOrEditBranchPage.class);
			Log.info("Create or Edit Branch Page has been initiated");
			
			staffPage = PageFactory.initElements(driver, StaffPage.class);
			Log.info("Staff Page has been initiated");
			
			createOrEditStaffPage = PageFactory.initElements(driver, CreateOrEditStaffPage.class);
			Log.info("Create or Edit Staff Page has been initiated");
			
			isAuthenticated = false;
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
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			
			navigationBar.clickOnStaff();
			
			String staffName = "";
			String branch = name;
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
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
			
			navigationBar.clickOnStaff();
			
			String staffName = "         ";
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("requiredField");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForMoreThan50Characters(){
		testCaseName = "testVerifyNameFieldForMoreThan50Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(51);
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("staffNameMoreThan50");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
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
			
			navigationBar.clickOnStaff();
			
			String staffName = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("staffNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
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
			
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomSpecialCharacters(5);
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("staffNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForCharactersDigits(){
		testCaseName = "testVerifyNameFieldForCharactersDigits";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(4) + testDataProvider.getRandomNumberOfLength(2);
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
				
			String actualText = createOrEditStaffPage.getNameFieldErrMsg();
			String expectedText = prop.getProperty("staffNameInvalid");
			Log.info("Expected Text = " + expectedText + " and Actual Text = " + actualText);
			assertEquals(expectedText, actualText);
			
			createOrEditStaffPage.clickOnCancel();
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldForLessThan50Characters(){
		testCaseName = "testVerifyNameFieldForLessThan50Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(49);
			String branch = name;
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
			createOrEditStaffPage.clickOnSave();
			
			staffPage.searchForAStaff(staffName);
			boolean flag = staffPage.isStaffRecordDisplayed(staffName);
			assertTrue(flag);
				
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyNameFieldFor50Characters(){
		testCaseName = "testVerifyNameFieldFor50Characters";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
		
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(50);
			String branch = name;
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
			createOrEditStaffPage.clickOnSave();
			
			staffPage.searchForAStaff(staffName);
			boolean flag = staffPage.isStaffRecordDisplayed(staffName);
			assertTrue(flag);
				
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
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(10).toLowerCase();
			String branch = name;
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
			createOrEditStaffPage.clickOnSave();
				
			staffPage.searchForAStaff(staffName);
			boolean flag = staffPage.isStaffRecordDisplayed(staffName);
			assertTrue(flag);
			
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
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(10).toUpperCase();
			String branch = name;
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
			createOrEditStaffPage.clickOnSave();
				
			staffPage.searchForAStaff(staffName);
			boolean flag = staffPage.isStaffRecordDisplayed(staffName);
			assertTrue(flag);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyBranchFieldForNullValue(){
		testCaseName = "testVerifyBranchFieldForNullValue";
		try {
			Log.startTestCase(testCaseName);
			
			homePage.clickOnLogin();
			
			loginPage.setAuthenticationDetails(prop.getProperty("username"), prop.getProperty("password"), true);
			loginPage.clickOnAuthenticate();
			performAction.waitForElementVisible(driver, authenticatedPage.successfulLoggedInMsg);
			isAuthenticated = performAction.isElementEnabled(driver, authenticatedPage.successfulLoggedInMsg);
			assertTrue(isAuthenticated);
			
			String name = testDataProvider.getRandomString(10);
			String code = String.valueOf(testDataProvider.getRandomNumberOfLength(5));
			
			createOrEditBranchPage.createNewBranch(name, code);
			navigationBar.clickOnStaff();
			
			String staffName = testDataProvider.getRandomString(10).toUpperCase();
			String branch = "";
			
			staffPage.clickOnCreateNewStaff();
			createOrEditStaffPage.setCreateOrEditStaffDetails(staffName, branch);
			createOrEditStaffPage.clickOnSave();
				
			staffPage.searchForAStaff(staffName);
			boolean flag = staffPage.isStaffRecordDisplayed(staffName);
			assertTrue(flag);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "CreateStaffTests_" + testCaseName);
			if (isAuthenticated)
				navigationBar.clickOnLogout();
			driver.close();
			Utilities.closePropertiesFile(input);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
}
