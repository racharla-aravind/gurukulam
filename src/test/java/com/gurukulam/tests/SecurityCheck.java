package com.gurukulam.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gurukulam.pages.HomePage;
import com.gurukulam.pages.LoginPage;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.GurukulaActions;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.Utilities;

public class SecurityCheck {

    WebDriver driver = null;
	HomePage homePage = null;
	LoginPage loginPage = null;

	GurukulaActions performAction = new GurukulaActions();
	String testCaseName = null;

	@Before
	public void setup() {
		try {
			
			driver = GetDriver.getDriver();

			homePage = PageFactory.initElements(driver, HomePage.class);
			Log.info("Home Page has been initiated");

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			Log.info("Login Page has been initiated");

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void sampleSecurityCheck(){
		testCaseName = "sampleSecurityCheck";
		try {
			Log.startTestCase(testCaseName);
			String username = "<script>   alert ('Hello Test!')  </script>";;
			String password = "<script>   alert ('Hello Test!')  </script>";;
			boolean rememberMe = true;
			homePage.clickOnLogin();
			loginPage.setAuthenticationDetails(username, password, rememberMe);
			loginPage.clickOnAuthenticate();
			
			boolean flag = performAction.isAlertDisplayed(driver);
			assertFalse(flag);
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Utilities.takeSnapShot(driver, "SecurityTests_" + testCaseName);
			driver.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
}
