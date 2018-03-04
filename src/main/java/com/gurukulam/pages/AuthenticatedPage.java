package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.GurukulaActions;

public class AuthenticatedPage {
	
	WebDriver driver = null;
	GurukulaActions performAction = new GurukulaActions();

	@FindBy(xpath="//h1[text() = 'Welcome to Gurukula!']/following-sibling::div")
	public WebElement successfulLoggedInMsg;
}
