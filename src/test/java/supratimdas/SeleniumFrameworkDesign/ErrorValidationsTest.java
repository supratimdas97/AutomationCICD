package supratimdas.SeleniumFrameworkDesign;

	import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import supratimdas.TestComponents.BaseTest;
import supratimdas.pageobjects.CartPage;
import supratimdas.pageobjects.CheckoutPage;
import supratimdas.pageobjects.ConfirmationPage;
import supratimdas.pageobjects.ProductCatalogue;
	


public class ErrorValidationsTest extends BaseTest {
	
	
  @Test(groups= {"ErrorHandling"})      // by setting group before method and from xml file we we set the group, it will only execute groupwise
  public void LoginErrorValidation() throws IOException, InterruptedException
			
  {
	  String productname="ZARA COAT 3";
			
		
			
		ProductCatalogue productCatalogue= landingPage.loginApplication("ds.supratim98@gmail.com", "Testin@123");
			
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
		}
  
  @Test
  
  public void ProductErrorValidation() throws IOException, InterruptedException
	
  {
	  String productname="ZARA COAT 3";
			
	  ProductCatalogue productCatalogue= landingPage.loginApplication("das.supratim98@gmail.com", "Testing@123");
			
			
			List<WebElement> products= productCatalogue.getProductList();
			
			productCatalogue.addProductToCart(productname);
			
			CartPage cartpage= productCatalogue.goToCartPage();
			
			Boolean match= cartpage.VerifyProductDisplay("ZARA COAT 45678");
			 Assert.assertFalse(match);  
			
			
			
  }

	}



