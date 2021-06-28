package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By emailId=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	private By subscribeYes=By.xpath("(//label[@class='radio-inline'])[1]");
	private By subscribeNo=By.xpath("(//label[@class='radio-inline'])[2]");
	private By agreeCheckBox=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit']");
	private By successMsg=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Registercd");

	
	
}
