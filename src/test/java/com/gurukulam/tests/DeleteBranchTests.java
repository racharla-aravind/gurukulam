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
import com.gurukulam.pages.BranchesPage;
import com.gurukulam.pages.CreateOrEditBranchPage;
import com.gurukulam.pages.CreateOrEditStaffPage;
import com.gurukulam.pages.DeleteBranchOrStaffPage;
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

public class DeleteBranchTests {
	
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
	DeleteBranchOrStaffPage deleteBranchPage = null;
	
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
			Log.info("Reset Your Password Page has been initiated");
			
			createOrEditBranchPage = PageFactory.initElements(driver, CreateOrEditBranchPage.class);
			Log.info("Create or Edit Branch Page has been initiated");
			
			staffPage = PageFactory.initElements(driver, StaffPage.class);
			Log.info("Staff Page has been initiated");
			
			createOrEditStaffPage = PageFactory.initElements(driver, CreateOrEditStaffPage.class);
			Log.info("Create or Edit Staff Page has been initiated");
			
			deleteBranchPage = PageFactory.initElements(driver, DeleteBranchOrStaffPage.class);
			Log.info("Delete Staff Page has been initiated");
			
			isAuthenticated = false;
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteBranchRecord(){
		testCaseName = "testDeleteBranchRecord";
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
			
			branchesPage.searchForABranch(name);
			boolean flag = branchesPage.isBranchRecordDisplayed(name);
			assertTrue(flag);
			
			branchesPage.clickOnDeleteBranch();
			deleteBranchPage.clickOnDelete();
			
			branchesPage.searchForABranch(name);
			flag = branchesPage.isBranchRecordDisplayed(name);
			assertFalse(flag);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyCancelFunctionalityOnStaffDeleteConfirmation(){
		testCaseName = "testVerifyCancelFunctionalityOnStaffDeleteConfirmation";
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
			
			branchesPage.searchForABranch(name);
			boolean flag = branchesPage.isBranchRecordDisplayed(name);
			assertTrue(flag);
			
			branchesPage.clickOnDeleteBranch();
			deleteBranchPage.clickOnCancel();
			
			branchesPage.searchForABranch(name);
			flag = branchesPage.isBranchRecordDisplayed(name);
			assertTrue(flag);
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyConfirmationTextOnStaffDeleteConfirmation(){
		testCaseName = "testVerifyConfirmationTextOnStaffDeleteConfirmation";
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
			
			branchesPage.searchForABranch(name);
			boolean flag = branchesPage.isBranchRecordDisplayed(name);
			assertTrue(flag);
			
			branchesPage.clickOnDeleteBranch();
			String actualText = deleteBranchPage.getConfirmationText();
			String expectedText = prop.getProperty("deleteConfirmationText") + " Branch";
			if (actualText.contains(expectedText))
				assertTrue(true);
			else
				assertTrue(false);
			
			deleteBranchPage.clickOnCancel();
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "DeleteStaffTests_" + testCaseName);
			if (isAuthenticated)
				navigationBar.clickOnLogout();
			driver.close();
			Utilities.closePropertiesFile(input);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

}
