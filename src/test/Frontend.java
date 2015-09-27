package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Frontend {

	  private WebDriver driver = null;
	  private WebDriverWait driverWait = null;
	  private final static String CHROMEWEBDRIVER = "webdriver.chrome.driver";
	  private final static String CHROMEDRIVERPATH = "/Users/Sarah/Documents/workspace/WalmartFrontend/chromedriver";//Modify the path to where it stores the chromedriver 
	  private final static String WALMART_WEB_URL = "http://www.walmart.com";
	  private final static String SIGNIN_XPATH = "//a[contains(.,'Sign In')]";
	  private final static String LOGIN_USERNAME = "login-username";
	  private final static String LOGIN_PASSWORD = "login-password";
	  private final static String EMAILADDRESS = "wishhappyforever@gmail.com";
	  private final static String PASSWORD = "happy1234";
	  private final static String RECENTORDERS = "recent-orders-heading";
	  private final static String LOGO = "//a[@class='logo js-logo hide-content-max-l']";
	  private final static String SEARCHBAR = "query";
	  private final static String TV = "tv";
	  private final static String SCEPTREX322BV = "//a[starts-with(.,'SCEPTRE X322BV-MQC 32\" LED')]";
	  private final static String ADDTOCART = "//button[contains(.,'Add to Cart')]";
	  private final static String VIEWCART = "//a[@id='PACViewCartBtn']";
	  private final static String YOURCART = "//h3[contains(.,'Your cart:')]";
	  private final static String ITEMAMOUNT = "//*[@id='spa-layout']/div/div/div[1]/div/h3/span";
	  private final static String ITEMINFO = "//*[@id='CartItemInfo']/span";
	  private final static String ITEMTITLE = "SCEPTRE X322BV-MQC 32\" LED";
	
	  

	  @Before
	  public void createDriver() {
		//Point chrome driver to the chrome driver path
	    System.setProperty(CHROMEWEBDRIVER, CHROMEDRIVERPATH);
	    driver = new ChromeDriver();
	    driverWait = new WebDriverWait(driver, 30);
	  }


	  @Test
	  public void shouldBeAbleToPerformAGoogleSearch() throws InterruptedException {
			//In browser URL address bar, input Walmart web url and enter
		  	openURL(WALMART_WEB_URL);
		    
		    //1. Login using existing account
		  	signInExistingAccount();
		  	
		    //2. Perform a search on home page from a pool of key words given below
		  	searchItem(TV);
		    
		    //3. Identify an item from the result set that you can add to cart
		    //find the element in result set and click into it
		    WebElement tv = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SCEPTREX322BV)));
		    tv.click();
		    
		    //4. Add the item to cart
		    WebElement addToCart = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ADDTOCART)));
		    addToCart.click();
		    
		    //5. Validate that item added is present in the cart and is the only item in the cart
		    WebElement viewCart = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(VIEWCART)));
		    viewCart.click();
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(YOURCART)));
		    
		    //Validate only item
		    WebElement itemAmount = driver.findElement(By.xpath(ITEMAMOUNT));
		    Assert.assertEquals(itemAmount.getText(), "1 item");
		    
		    //Validate item is present
		    String itemInfo = driver.findElement(By.xpath(ITEMINFO)).getText();
		    Assert.assertTrue("Item not found!", itemInfo.contains(ITEMTITLE));
		    
	  }
	
	  public void openURL(String URL){
			 driver.get(URL);
	  }
	  
	  public void signInExistingAccount(){
		    //Wait until sign in button appears, click it
		    WebElement signIn = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGNIN_XPATH)));
		    signIn.click();
		    //Find login username and password elements, fill in and submit
		    WebElement loginUsername = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(LOGIN_USERNAME)));
		    WebElement loginPassword = driver.findElement(By.id(LOGIN_PASSWORD));
		    loginUsername.sendKeys(EMAILADDRESS);
		    loginPassword.sendKeys(PASSWORD);
		    loginPassword.submit();
		    //Wait until "Welcome to your Walmart account!" appears
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(RECENTORDERS)));
	  }

	  public void searchItem(String item){
		  	//go to home page
		    WebElement logo = driver.findElement(By.xpath(LOGO));
		    logo.click();
		    //input item in search bar
		    WebElement searchBar = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(SEARCHBAR)));
		    searchBar.sendKeys(item);
		    searchBar.submit();
	  }
	  
	  
	  @After
	  public void quitDriver() {
	    driver.quit();
	  }
}
