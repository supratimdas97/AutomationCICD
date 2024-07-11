package supratimdas.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import supratimdas.pageobjects.CartPage;
import supratimdas.pageobjects.OrderPage;

public class AbstractComponent {
	
	
	 WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);  // to initialize 
		
	}
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")  // for add to cart button
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")  // for check the orders from the header field button
	WebElement orderHeader;
	


	public void waitForElementToBeAppear(By findBy)
	{
       WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5)); 
		
		List<WebElement> products= driver.findElements(findBy);
	}
	
	
	public void waitForWebElementToBeAppear(WebElement findBy)    // if cust giving wrong input during login, this is to get the error message popup
	{
       WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5)); 
		
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	
	{
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
//	    wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();  
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();  
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
}
