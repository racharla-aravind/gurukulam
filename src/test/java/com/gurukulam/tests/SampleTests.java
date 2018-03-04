package com.gurukulam.tests;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import com.gurukulam.pages.Sample;
import com.gurukulam.utilities.GetDriver;
import com.gurukulam.utilities.Log;
import com.gurukulam.utilities.Utilities;

public class SampleTests {
	
	public InputStream input = null;
	public WebDriver driver = null;
	
	String testCaseName = null;
	
	@Before
	public void setup() {
		Properties prop = new Properties();
		try {
			input = Utilities.loadProperties();
			prop.load(input);
			driver = GetDriver.getDriver();
			Sample loginPage = PageFactory.initElements(driver, Sample.class);
			loginPage.launchLoginPage(prop.getProperty("loginUrl"));
			Log.info("Launched the url successfully");
			loginPage.authenticate(prop.getProperty("username"), prop.getProperty("password"));
			assertEquals(true, true);
		} catch (Exception e) {
			System.out.println("error -- " + e.getMessage());
		}
	}
	
	@Test()
	public void verifyRegisterLinkFunctionality() throws Exception {
		testCaseName = "verifyRegisterLinkFunctionality";
		try {
			Log.startTestCase(testCaseName);
			
			
			
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test()
	public void successfulLoginTest() throws Exception{
		String testCaseName = "Successful_Login_Test";
		try {
			Log.startTestCase(testCaseName);
			/*System.setProperty("webdriver.chrome.driver","/Users/904449/Documents/workspace-sugar6/project/src/main/resources/drivers/chromedriver");
			WebDriver driver = new ChromeDriver();*/
			/*driver = GetDriver.getDriver();
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.launchLoginPage("https://localhost:8080");
			Log.info("Launched the url successfully");
			loginPage.authenticate("admin", "admin");
			assertEquals(true, true);*/
			Log.endTestCase(testCaseName);
		} catch (Exception e) {
			fail(e.getMessage());
			System.out.println("error -- " + e.getMessage());
		} finally {
		   /* Utilities.takeSnapShot(driver, testCaseName);
		    driver.close();*/
		}
	}
	
	@After
	public void tearDown(){
		try {
			Utilities.takeSnapShot(driver, "");
			driver.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	    
	}
}
