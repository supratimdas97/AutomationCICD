package supratimdas.SeleniumFrameworkDesign;

	import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import supratimdas.TestComponents.BaseTest;
import supratimdas.pageobjects.CartPage;
import supratimdas.pageobjects.CheckoutPage;
import supratimdas.pageobjects.ConfirmationPage;
import supratimdas.pageobjects.OrderPage;
import supratimdas.pageobjects.ProductCatalogue;
	


public class SubmitOrderTest extends BaseTest {
	
	String productname="ZARA COAT 3";
	
  @Test(dataProvider="getData", groups= {"Purchase"})
  public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
			
  {
	  
			
		//	LandingPage landingPage=launchApplication();
			
		//	LandingPage landingPage= new LandingPage(driver);
			
		//	landingPage.goTo();
			
		ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));
			
			// by calling the object of landingpage and passing the value of email & password
			
			
			List<WebElement> products= productCatalogue.getProductList();
			
			productCatalogue.addProductToCart(input.get("productname"));
			
			CartPage cartpage= productCatalogue.goToCartPage();
			
			
		//	CartPage cartpage= new CartPage(driver);
			
			Boolean match= cartpage.VerifyProductDisplay(input.get("productname"));
			 Assert.assertTrue(match);  // this is a validation which will be there only inside code not in pageobject code. Page Object will only handel actions to perform , not test case
			
			CheckoutPage checkoutpage= cartpage.goToCheckout();
			
			checkoutpage.selectCountry("India");
			
			ConfirmationPage confirmationpage= checkoutpage.submitOrder();
			
			String confirmMsg= confirmationpage.getConfirmationMsg();
			
			Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANK YOU FOR THE ORDER"));
			
			
			
			
			
			 
			// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
			
			
			// List<WebElement> products= driver.findElements(By.cssSelector(".mb-3")); // with this css selector we are collecting the common class name for all the products from the website

	//	WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);

			// first we are iterating in all the products, then filterting it
			//at first, the 1st element value will be stored in the product variable filtering it
			// we will get the text of the filter and match with our desired product
			// now from the products list we are getting the "b" tag because inside this only the product name is present which will compare with our input
		    // after we are validating to get the first result and we are storing that value into prod "variable"
		    // and if it does not return any thing the we are asking to return null
		
		
		//  prod.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]")).click();
		
		  // We need to click on add to cart button for the selected product
		  // in the above line we are mentioning prod.findElememt- meaning now it will get the button from the fetched product
		
		
		  
		//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		  // we need to fetch the green success toast message after adding the product to cart
		  // above we are using explicit wait method
		  // we in above method we are waiting for the green toast message and getting the element after it's appearance
		  
		//  Thread.sleep(3000);
		  
	//	  wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		  
	//	  driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); // for clicking on add to cart button
		
		  
	//	List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection h3")); 
		// fetching the elements from the cart
		// after that storing that into a variable
		  
//		cartProducts.stream().filter(cartProduct-> cartProduct.getText().equals(productname)); 
		
		//in the above line by using stream taking another variable named cartProduct and after iteration the product we are getting just keeping it and comparing it with our desired input
		
	//   Boolean match=	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productname));
	  
	//   Assert.assertTrue(match); // if the item is present in cart page it will return true and that true will go inside and 


		// instead of writing filter, we are using anymatch function, 
		// so if any result retrieved it will compare with our equals( given input)
	    // will store the value as boolean format whether true or false
	   
	//   driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click(); //checkout button click
	   
//	   Actions a= new Actions(driver);
//	   a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); // searching india from the search box
//		
//	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//	   
//	   driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();  // selecting the 2nd item from the search
//	   
//	   Thread.sleep(3000);   
//	   driver.findElement(By.cssSelector(".action__submit")).click(); // clicking on submit button but it's not working
//		
		}
  
  
  @Test(dependsOnMethods= {"submitOrder"})
  
  public void OrderHistoryTest()
  {
	  ProductCatalogue productCatalogue= landingPage.loginApplication("das.supratim98@gmail.com", "Testing@123");
	 OrderPage orderPage=  productCatalogue.goToOrderPage();
	 
	 Assert.assertTrue(orderPage.VerifyOrderDisplay(productname)); 
	  
  }
  
  
  
  
  @DataProvider   // by this annotation , we are sending data
  
  public Object[][] getData() throws IOException
  {
	  
	  
	  // Instead of writing the code in below Hashmap format we will write it in json format
	  
//	  HashMap<String,String> map= new HashMap<String,String>();
//	  map.put("email","das.supratim98@gmail.com");
//	  map.put("password","Testing@123");
//	  map.put("product", "ZARA COAT 3");
//	  
//	  HashMap<String,String> map1= new HashMap<String,String>();
//	  map1.put("email","das.supratim98@gmail.com");               // use diff email, password , product based on the test case
//	  map1.put("password","Testing@123");
//	  map1.put("product", "ZARA COAT 3");
	  
	 List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\supratimdas\\data\\PurchaseOrder.json");
	  
	  return new Object[][] { {data.get(0)}, {data.get(1)}};
  }
  
  
  
  
}



