package com.qa.opencart.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class TimeUtility {
	/*
	 * Static waits and implicit wait 
	 */	
	public static  void longTime() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	public static void midTime() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void shortTime() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void applyImplicitWait(WebDriver driver,int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}
	

	
}
