package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void getAccountPageTitle() {
		String title=accountPage.getAccountPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageHeaderTest() {
		String header=accountPage.getAccPageHeader();
		System.out.println("Account pagee header is "+header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test
	public void accPageSectionListTest() {
		List<String> secList=accountPage.getAccountSectionList();
		softAssert.assertEquals(secList.size(), Constants.ACCOUNT_PAGE_SECTION_COUNT);
		Assert.assertEquals(secList, Constants.EXPECTEDSECTIONLIST);
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"iMac"},
			{"Macbook Pro"},
			{"Apple"},
			{"Macbook Air"}};
	}
	
	@Test(dataProvider="productData")
	public void searchTest(String productName) {
		searchResultsPage=accountPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getSearchProductListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Apple","Apple Cinema 30\""}};
	}
	@Test(dataProvider="productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultsPage=accountPage.doSearch(productName);
		searchResultsPage.selectProduct(mainProductName);
	}

	
}
