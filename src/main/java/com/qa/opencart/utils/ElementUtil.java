package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	//Problem is how to pass the driver-using constructor
	private WebDriver driver;
	private JavaScriptExecutorUtility jsUtil;
	
	public ElementUtil(WebDriver driver){//creating constructor of teh class
		this.driver=driver;
		jsUtil=new JavaScriptExecutorUtility(this.driver);
	}
	   //4th approach
		public  WebElement getElement(By locator) {
			WebElement element=driver.findElement(locator);
			if(Boolean.parseBoolean(DriverFactory.highlight)) {
				jsUtil.flash(element);
			}
			//return driver.findElement(locator);// by locator will return any webelement		
			return element;
		}
		public  List<WebElement> getElements(By locator) {
			return driver.findElements(locator);// this returns list of webelemnts
		}
		//5th approach
		public  void doSendKeys(By locator,String value) {
			WebElement ele=getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		}
	
		//creating a custom wrapper class for .click method
		public void doClick(By locator) {
			getElement(locator).click();
		}
		
		public String doGetText(By locator) {
			return 	getElement(locator).getText();
		}
		
		public boolean doIsDisplayed(By locator) {
			return 	getElement(locator).isDisplayed();
		}
			
		public  List<String> getElementTextList(By locator) {
			List<WebElement> elelist=getElements(locator);
			List<String> eleTextlist=new ArrayList<String>();
			for(WebElement e:elelist) {
				String text=e.getText();
				if(!text.isEmpty()) {
					eleTextlist.add(text);
				}
			}
			return eleTextlist;
		}
		
		public  void printElementsText(By locator) {
			getElementTextList(locator).stream().forEach(e->System.out.println(e));
		}	
		
		public  void getAttributelList(By locator,String att) {
			List<WebElement> Attributelist=getElements(locator);
			for(int i=0;i<Attributelist.size();i++) {
				String attlist =Attributelist.get(i).getAttribute(att);
				System.out.println(attlist);
			}
		}
		
	/*
	 * *******************Select Dropdown methods**********************
	 */
		
		public  void doSelectDropDownbyIndex(By locator,int index) {
			Select select=new Select(getElement(locator));
			select.selectByIndex(index);
		}
		
		public  void doSelectDropDownbyVisibleText(By locator,String text) {
			Select select=new Select(getElement(locator));
			select.selectByVisibleText(text);
		}
		public  void doSelectDropDownbyValue(By locator,String value) {
			Select select=new Select(getElement(locator));
			select.selectByValue(value);
		}

		/*
		 * ****************************Action Class Methods*****************
		 */
				
		public  void HandletwoLevelmenu(By Parentlocator,By childlocator) {
			Actions act=new Actions(driver);
			act.moveToElement(getElement(Parentlocator)).perform();
			getElement(childlocator).click();
		}
		
		public  void HandleThreeLevelmenu(By Parentlocator1,By Parentlocator2,By childlocator) throws InterruptedException {
			Actions act=new Actions(driver);
			act.moveToElement(getElement(Parentlocator1)).perform();
	        Thread.sleep(2000);
			act.moveToElement(getElement(Parentlocator2)).perform();
	        Thread.sleep(2000);
			getElement(childlocator).click();
		}
		
		/*
		 * *****************************TimeUtils****************************
		 * 
		 */
		/*
		 * An expectation for checking that an element is present on the DOM of a page. This does notnecessarily mean that the element is visible.
           @Parameters:locator used to find the element
           @Returns:the WebElement once it is located
		 */
		public  WebElement doPresenceofElementLocated(By locator, int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		/*
		 * An expectation for checking that an element, known to be present on the DOM of a page, isvisible. Visibility means that the element is not only displayed but also has a height andwidth that is greater than 0.
            @Parameters:element the WebElement
            @Returns:the (same) WebElement once it is visible
		 */
		public  WebElement doVisibilityofElementLocated(By locator, int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}
		
		/*
		 * **********************Wait For ALert********************************
		 */
		
		public  Alert doWaitforAlert(int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
		    return wait.until(ExpectedConditions.alertIsPresent());
		}
		 public  void acceptAlert(int timeout) {
			 doWaitforAlert(timeout).accept();
		 }
		 public  void dismissAlert(int timeout) {
			 doWaitforAlert(timeout).dismiss();
		 }
		 public  void getAlertText(int timeout) {
			 doWaitforAlert(timeout).getText();
		 }
		 
		 /*
		 * ************************Wait For URL************************************
		 */
		 public  boolean waitForURL(String urlFraction,int timeout) {
			WebDriverWait wait =new WebDriverWait(driver,5);
		    return wait.until(ExpectedConditions.urlContains("login"));
		 }
		 public  boolean waitForURLMatchs(String urlFraction,int timeout) {
			WebDriverWait wait =new WebDriverWait(driver,5);
			return wait.until(ExpectedConditions.urlMatches("login"));
		}
		 public  boolean waitForURLTobe(String urlFraction,int timeout) {
			WebDriverWait wait =new WebDriverWait(driver,5);
		    return wait.until(ExpectedConditions.urlToBe("https://mail.rediff.com/cgi-bin/login.cgi"));
		}
		 /*
		 * ************************Wait For Title************************************
		 */	
		 public  boolean waitForTitleContains(String title,int timeout) {
				WebDriverWait wait =new WebDriverWait(driver,5);
			    return wait.until(ExpectedConditions.titleContains("login"));
		  }
		 public  boolean waitForTitleIs(String title,int timeout) {
			WebDriverWait wait =new WebDriverWait(driver,5);
		    return wait.until(ExpectedConditions.titleIs(title));
		  }
		 public  String getPageTitle(String title,int timeout) {
				waitForTitleContains(title,timeout);
				return driver.getTitle();
		}
		
		/*
		 * **************************WaitForFrame***************************************
		 */
		 public  void waitforFrame(String IDOrName,int timeout) {
				WebDriverWait wait=new WebDriverWait(driver,timeout);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
		 }
		 public  void waitforFrameString(String IDOrName,int timeout) {
				WebDriverWait wait=new WebDriverWait(driver,timeout);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
		 }
		 public  void waitforFramebyIndex(int Frameindex,int timeout) {
				WebDriverWait wait=new WebDriverWait(driver,timeout);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Frameindex));
		  }
		 public  WebElement waitForElementtobeclickable(By locator,int timeout) {
				WebDriverWait wait=new WebDriverWait(driver,timeout);
				return wait.until(ExpectedConditions.elementToBeClickable(locator));
		 }
		 public  void clickWhenReady(By locator,int timeout) {
				waitForElementtobeclickable(locator,timeout);
		}
		
		 public  void waitforVisibilityofElement(By locator,int timeout) {
				WebDriverWait wait= new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		 
		}
		public  List<WebElement> waitforvisiblityofelements(By locator,int timeout) {
				WebDriverWait wait= new WebDriverWait(driver,10);
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
			
		public  void printElementText(By locator,int timeout) {
				waitforvisiblityofelements(locator,timeout)
				.stream().forEach(e->System.out.println(e.getText()));
		}
		
		
		
}
