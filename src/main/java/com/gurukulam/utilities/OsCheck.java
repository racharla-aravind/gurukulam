package com.gurukulam.utilities;

public final class OsCheck {
	  /**
	   * types of Operating Systems
	   */
	  public enum OSType {
	    Windows, MacOS, Linux, Other
	  };

	  // cached result of OS detection
	  protected static OSType detectedOS;

	  /**
	   * detect the operating system from the os.name System property and cache
	   * the result
	   * 
	   * @returns - the operating system detected
	   */
	  public static OSType getOperatingSystemType() {
	    if (detectedOS == null) {
	      String OS = System.getProperty("os.name", "generic").toLowerCase();
	      if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
	        detectedOS = OSType.MacOS;
	      } else if (OS.indexOf("win") >= 0) {
	        detectedOS = OSType.Windows;
	      } else if (OS.indexOf("nux") >= 0) {
	        detectedOS = OSType.Linux;
	      } else {
	        detectedOS = OSType.Other;
	      }
	    }
	    return detectedOS;
	  }
	  
	  public static String getOSArch(){
		  String arch = "64";
		  if (OsCheck.getOperatingSystemType() == OSType.Windows){
			  if (System.getenv("ProgramFiles(x86)") != null){
				  arch = "64";
			  }else{
				  arch = "32";
			  }
		  }else{
			  if (System.getProperty("os.arch").indexOf("64") != -1){
				  arch = "64";
			  }else{
				  arch = "32";
			  }
		  }
		  return arch;
	  }
	}