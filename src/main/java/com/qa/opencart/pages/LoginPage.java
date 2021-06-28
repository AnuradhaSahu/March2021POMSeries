package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1.By locator
	By emailId=By.id("input-email");
	By password=By.id("input-password");
	By loginButton=By.xpath("//input[@value='Login']");
	By forgotPwdLink=By.linkText("Forgotten Password");
	By footerLinks=By.xpath("//footer//div[@class='row']//a");
	By newCustomerContinuebutton=By.xpath("//a[contains(text(),'Continue')]");
	By diffLists=By.xpath("//div[@class='list-group']/a");
	
	//2.create constructor of this class-bcoz we need to pass on the public webdriver to private webdriver which is only for this class-this is a holding driver
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}	
	
	//3.Page actions if we will not use driver then we will get null pinter exception	
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	public boolean isforgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un,String pwd) {
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();	
		elementUtil.doPresenceofElementLocated(emailId, Constants.DEFAULT_TIMEOUT).sendKeys(un);;
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public List<String> getFooterLinksTest() {
		List<WebElement> footerList= elementUtil.getElements(footerLinks);
		List<String> footerTextList=new ArrayList<String>();
		for(WebElement e:footerList) {
			footerTextList.add(e.getText());
		}
		return footerTextList;
	}
	
	public boolean newCustomerContinueButton() {
		return elementUtil.doIsDisplayed(newCustomerContinuebutton);
	}
	
	public List<String> getDiffLinksonLoginPage() {
		List<WebElement> textDiffLink = elementUtil.getElements(diffLists);
		List<String> textlinks=new ArrayList<String>();
		
		for(WebElement e:textDiffLink) {
			textlinks.add(e.getText());
		}
		return textlinks;
	}
		
}
