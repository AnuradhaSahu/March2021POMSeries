package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ProductsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;

	
	public ProductsPage() {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}

}
