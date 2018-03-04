package com.gurukulam.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Utilities {
	
	
	/**
	 * This method is to take the screenshots
	 * @throws : Exception
	 */
	public static void takeSnapShot(WebDriver webdriver, String testCaseName) throws Exception {
		File currDir = new File(".");
		String fileWithPath = currDir.getAbsolutePath();
		fileWithPath = fileWithPath.substring(0, fileWithPath.length() - 1) + "reports/screenshots/" + testCaseName + ".png";
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	/**
	 * This method is to application properties file
	 * @return InputStream object
	 * @throws : IOException
	 */
	public static InputStream loadProperties() throws IOException{
		InputStream input = null;

		String filename = "application.properties";
		input = Utilities.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
			return null;
		} else {
			return input;
		}
	}
	

	/**
	 * This method is to application properties file
	 * @param InputStream object
	 * @throws : IOException
	 */
	public static void closePropertiesFile(InputStream input) {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
