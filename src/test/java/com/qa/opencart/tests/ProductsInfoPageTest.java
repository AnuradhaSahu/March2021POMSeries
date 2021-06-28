package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductsInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetUp() {
	   accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoHeaderTest() {
		searchResultsPage=accountPage.doSearch("MacBook");
		productsInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productsInfoPage.getproductHeaderText(),"MacBook Pro");
	}
	
	@Test
	public void productImageTest() {
		searchResultsPage=accountPage.doSearch("iMac");
		productsInfoPage=searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productsInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage=accountPage.doSearch("MacBook");
		productsInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap=productsInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v)->System.out.println(k+" : "+v));
		
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertTrue(actProductInfoMap.get("price").contains("2,000"));
		softAssert.assertAll();

	}
	
	
	

}
