package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	private By searchHeaderName=By.cssSelector("div#content h1");
	private By productResults=By.cssSelector("div.caption a");
	
	public String getSearchHeaderName() {
		return elementUtil.doGetText(searchHeaderName);
	}
	
	public int getSearchProductListCount() {
		return elementUtil.waitforvisiblityofelements(productResults, Constants.DEFAULT_TIMEOUT).size();
	}
	
	public ProductsInfoPage selectProduct(String mainProductName) {
		List<WebElement> searchList=
				elementUtil.waitforvisiblityofelements(productResults, Constants.DEFAULT_TIMEOUT);
		for(WebElement e: searchList) {
			String text=e.getText();
			if(text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductsInfoPage(driver);
	}
}
