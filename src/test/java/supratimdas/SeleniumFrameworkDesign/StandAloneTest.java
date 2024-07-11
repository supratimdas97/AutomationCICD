package supratimdas.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import supratimdas.pageobjects.LandingPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productname="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();  // Just by adding WebDriverManager dependecy to pox.xml file and giving this line we can call chromeDriver object

		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		
		
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage= new LandingPage(driver);
		
		
		// creating object of landing page class over here
		// now above driver object is having life over here, so we will send that as an argument to landingpage class
		
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("das.supratim98@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Testing@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
		
		
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3")); // with this css selector we are collecting the common class name for all the products from the website

	WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);

		// first we are iterating in all the products, then filterting it
		//at first, the 1st element value will be stored in the product variable filtering it
		// we will get the text of the filter and match with our desired product
		// now from the products list we are getting the "b" tag because inside this only the product name is present which will compare with our input
	    // after we are validating to get the first result and we are storing that value into prod "variable"
	    // and if it does not return any thing the we are asking to return null
	
	
	  prod.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]")).click();
	
	  // We need to click on add to cart button for the selected product
	  // in the above line we are mentioning prod.findElememt- meaning now it will get the button from the fetched product
	
	
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	  // we need to fetch the green success toast message after adding the product to cart
	  // above we are using explicit wait method
	  // we in above method we are waiting for the green toast message and getting the element after it's appearance
	  
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); // for clicking on add to cart button
	
	  
	List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection h3")); 
	// fetching the elements from the cart
	// after that storing that into a variable
	  
//	cartProducts.stream().filter(cartProduct-> cartProduct.getText().equals(productname)); 
	
	//in the above line by using stream taking another variable named cartProduct and after iteration the product we are getting just keeping it and comparing it with our desired input
	
   Boolean match=	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productname));
  
   Assert.assertTrue(match); // if the item is present in cart page it will return true and that true will go inside and 


	// instead of writing filter, we are using anymatch function, 
	// so if any result retrieved it will compare with our equals( given input)
    // will store the value as boolean format whether true or false
   
   driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click(); //checkout button click
   
   Actions a= new Actions(driver);
   a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); // searching india from the search box
	
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
   
   driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();  // selecting the 2nd item from the search
   
   Thread.sleep(2000);   
   driver.findElement(By.cssSelector(".action__submit")).click(); // clicking on submit button but it's not working
 
   
	}

}
