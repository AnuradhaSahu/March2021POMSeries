package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isforgotPwdLinkExist());
	}
	
	@Test
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void loginPageFooterLinksTest() {
		List<String> footerList=loginPage.getFooterLinksTest();
		Assert.assertEquals(footerList.size(),15);
		Assert.assertTrue(footerList.contains("About Us"));
	}
	
	@Test
	public void loginPageCotinueButtonTest() {
		Assert.assertTrue(loginPage.newCustomerContinueButton());
	}
	
	@Test
	public void loginPageDiffLinkTest() {
		List<String> textlinks=loginPage.getDiffLinksonLoginPage();
		Assert.assertEquals(textlinks.size(), 13);
		Assert.assertTrue(textlinks.contains("Order History"));
	}

}
