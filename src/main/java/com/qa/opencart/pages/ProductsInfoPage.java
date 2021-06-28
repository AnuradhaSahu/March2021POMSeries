package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductsInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	Map<String, String> productInfoMap;

	public ProductsInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	private By productHeader=By.cssSelector("div#content h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity=By.id("input-quantity");
	private By addtoCartBtn=By.id("button-cart");
	private By successMessage=By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public String getproductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		 return elementUtil.waitforvisiblityofelements(productImages, Constants.DEFAULT_TIMEOUT).size();
	}
	
	public Map<String, String> getProductInfo() {
		//productInfoMap=new LinkedHashMap<String,String>();// order based hashmap
		//productInfoMap=new HashMap<String,String>();// stores value in k-v pair
		productInfoMap=new TreeMap<String,String>();// it stores value in ascending order k-v pair
		productInfoMap.put("name", getproductHeaderText());
		getProductMetaData();
		getProductPriceData();	
		return productInfoMap;
		}
		

	private void getProductMetaData() {
		List<WebElement> metaDataList=elementUtil.getElements(productMetaData);
		System.out.println("Total product meta data :"+metaDataList.size());
		
		//meta Data
		for(WebElement e:metaDataList) {
			//barnd=apple
			String meta[]=e.getText().split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
	        }	
         }
	
	private void getProductPriceData() {
		//price
		List<WebElement> priceList=elementUtil.getElements(productPriceData);
		System.out.println("Total product price data :"+priceList.size());
		
		String price=priceList.get(0).getText().trim();
		String exprice=priceList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("exprice", exprice);
	}

	
	
	
	
	
	
	
	
	
	
	
}
