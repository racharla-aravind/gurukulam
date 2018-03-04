package com.gurukulam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gurukulam.utilities.Actions;

public class AuthenticatedPage {
	
	WebDriver driver = null;
	Actions performAction = new Actions();

	@FindBy(xpath="//h1[text() = 'Welcome to Gurukula!']/following-sibling::div")
	public WebElement successfulLoggedInMsg;
}
