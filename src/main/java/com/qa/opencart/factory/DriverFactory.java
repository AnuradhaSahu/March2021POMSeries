package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	/**
	 * @author rakes
	 */
	
	/**
	 * This method will return the driver
	 * @param browser
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browser=prop.getProperty("browser").trim();		
		
		System.out.println("Browser name is :"+browser);
		
		highlight=prop.getProperty("highlight");
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("safari")){
			driver=new SafariDriver();
		}
		else {
			System.out.println("Please pass the right browser..."+browser);
		}		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url").trim());		
		return driver;		
	}
	
	/**
	 * This method is used to initialise the properties
	 * @return
	 */
	public Properties init_prop() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("src//test//resources//config//config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
}
