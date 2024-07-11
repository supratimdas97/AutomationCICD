package supratimdas.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import supratimdas.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	
	public ProductCatalogue(WebDriver driver)  
	{                                     
		//initialization
		
		super(driver);
		
		this.driver= driver;  
		PageFactory.initElements(driver, this);
		
	
	}

 
	
	//pageFactory 
	
	@FindBy(css=".mb-3")               // now we are getting the element for product list            
	List<WebElement> products;     // just because it's findElements- plural so, we are keeping it in list
	
	@FindBy(css=".ng-animating")                        
	WebElement spinner;
	
	
	By productsBy= By.cssSelector(".mb-3");
	By addToCart = By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]");
	By toastMessage= By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		
		waitForElementToBeAppear(productsBy);
		return products;
	}
	
	// Creating Action Method
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod= getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToBeAppear(toastMessage);
		waitForElementToDisappear(spinner);
	    
	}
	
}	