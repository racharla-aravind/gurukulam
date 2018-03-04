package com.gurukulam.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.gurukulam.utilities.OsCheck;


public class GetDriver {

	/**
	 * Get the web driver instance based on the OS type and browser 
	 */

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	 
    public static WebDriver getDriver() {
    	try {
    	
    		String	browserName = System.getProperty("browserName");
        	if (browserName == null){
        		browserName = "chrome";
        	}
        	String driverPath = copyDriverToTemp(browserName);
        	WebDriver driver = null;
        	if (browserName.toLowerCase().contains("firefox")) {
                    driver = new FirefoxDriver();
    		}
    		if (browserName.toLowerCase().contains("safari")){
    			driver = new SafariDriver();
    		}
    		if (browserName.toLowerCase().contains("internet")) {
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    			System.setProperty("webdriver.ie.driver", driverPath);
    			driver = new InternetExplorerDriver(caps);
    		}
    		if (browserName.toLowerCase().contains("chrome")) {
    			System.setProperty("webdriver.chrome.driver", driverPath);
                System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
    			ChromeOptions chromeOptions = new ChromeOptions();
    			chromeOptions.addArguments("--log-level=3","test-type","star-maximized");
    			driver = new ChromeDriver(chromeOptions);
    		}
            setWebDriver(driver);
            return webDriver.get();
		} catch (IOException e) {
			return null;
		}
    	
    }
 
    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
    
    static void quitDriver() {
	  if (webDriver.get() != null) {
	      webDriver.get().quit();
	  }
    }    
    
    static String copyDriverToTemp(String browserName) throws IOException {
    	String path = null;
    	OsCheck.OSType ostype=OsCheck.getOperatingSystemType();
    	switch (ostype){
    		case Windows:
				if(browserName.toLowerCase().contains("chrome")){
					path = copyFileToTemp("drivers/chromedriver.exe", "chromedriver", ".exe" );
				}
				if(browserName.toLowerCase().contains("internet") || browserName.toLowerCase().contains("ie")){
	    			if(OsCheck.getOSArch().equalsIgnoreCase("64")){
						path = copyFileToTemp("drivers/IEDriverServer-64.exe", "IEDriverServer-64", ".exe" );
	    			}else{
						path = copyFileToTemp("drivers/IEDriverServer-32.exe", "IEDriverServer-32", ".exe" );
	    			}
				}
    			
    			break;
    		case MacOS:
				if(browserName.toLowerCase().contains("chrome")){
					path = copyFileToTemp("drivers/chromedriver-macosx", "chromedriver", "");
				}
    			break;
    		case Linux:
    			if(OsCheck.getOSArch().equalsIgnoreCase("64")){
    				if(browserName.toLowerCase().contains("chrome")){
    					path = copyFileToTemp("drivers/chromedriver-linux-64", "chromedriver" + Long.toString(System.currentTimeMillis()), "");
    				}
    			}else if(browserName.toLowerCase().contains("chrome")){
    					path = copyFileToTemp("drivers/chromedriver-linux-32", "chromedriver" + Long.toString(System.currentTimeMillis()), "");
    			}
    			break;
    		case Other:
    			break;
    	}
    	return path;
    }
    
    static String copyFileToTemp(String sourcePath, String prefix, String suffix) throws IOException{
    	File tempFile = null;
    	InputStream inputStream = GetDriver.class.getClassLoader().getResourceAsStream(sourcePath);
		tempFile = File.createTempFile(prefix, suffix, null);
    	tempFile.setExecutable(true);
    	tempFile.deleteOnExit();
    	OutputStream fileStream = new FileOutputStream(tempFile);
    	
    	try
	        {
	            final byte[] buf;
	            int i = 0;
	            buf = new byte[1024];
	            while((i = inputStream.read(buf)) != -1)
	            {
	                fileStream.write(buf, 0, i);
	            }
	        }
        finally
	        {
	            inputStream.close();
	            fileStream.close();
	        }
    	return tempFile.getAbsolutePath();
    }
}
