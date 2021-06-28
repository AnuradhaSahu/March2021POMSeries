package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductsInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountPage;
	public SearchResultsPage searchResultsPage;
	public ProductsInfoPage productsInfoPage;
	public SoftAssert softAssert;

	
	@BeforeTest
	public void setUp() {
		softAssert=new SoftAssert();
		df=new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
		loginPage=new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
