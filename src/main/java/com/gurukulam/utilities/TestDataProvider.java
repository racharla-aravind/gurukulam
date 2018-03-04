package com.gurukulam.utilities;

import java.util.Random;

import com.github.javafaker.Faker;

public class TestDataProvider {
	
	/**
	 * This method generates a valid random First Name
	 * @return firstName
	 */
	public String getRandomFirstName() {
		Faker faker = new Faker();
		String firstName = faker.firstName();
		Log.info("Faker object returning first name as " + firstName);
		return firstName;
	}

	/**
	 * This method generates a random email address
	 * @param domain
	 * @return emailAddress
	 */
	public String getRandomEmailAddress(String domain) {
		Faker faker = new Faker();
		String emailAddress = null;
		if (domain != "")
			emailAddress =  faker.firstName() + "@example." + domain;
		else
			emailAddress =  faker.firstName() + "@example";
		Log.info("Faker object returning email address as " + emailAddress);
		return emailAddress;
	}
	
	/**.
	 * This method generates a random number
	 * @param numberOfDigits
	 * @return randomNumber
	 * */
	public int getRandomNumberOfLength(final int numberOfDigits) {
	    Random randomGenerator = new Random();
	    int ndigits = 1;
	    for (int i = 1; i <= numberOfDigits ; i++)
	    	ndigits = 10 * ndigits;
	    int randomNumber = randomGenerator.nextInt(ndigits);
	    Log.info("Generate random number method returning value as " + randomNumber);
	      return randomNumber;
	  }

	/**.
	 * This method generates a random string  
	 * @param length
	 * @return sb
	 * */
	static String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public String getRandomString(final int length) {
		Random rnd = new Random();
	   StringBuilder randomString = new StringBuilder(length);
	   for (int i = 0; i < length; i++) 
		   randomString.append(charSet.charAt(rnd.nextInt(charSet.length())));
	   Log.info("Generate random string method returning value as " + randomString);
	   return randomString.toString();
	}
	
	/**.
	 * This method generates a random string  
	 * @param length
	 * @return sb
	 * */
	static String specialCharSet = "!@#$%^&*()";
	public String getRandomSpecialCharacters(final int length) {
		Random rnd = new Random();
	   StringBuilder randomString = new StringBuilder(length);
	   for (int i = 0; i < length; i++) 
		   randomString.append(specialCharSet.charAt(rnd.nextInt(specialCharSet.length())));
	   Log.info("Generate random specials method returning value as " + randomString);
	   return randomString.toString();
	}
}
