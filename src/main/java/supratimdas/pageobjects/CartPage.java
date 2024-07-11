package supratimdas.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supratimdas.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkoutEle;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;  
		PageFactory.initElements(driver, this);
	}

	
	public Boolean VerifyProductDisplay(String productname)
	
	{
		 Boolean match=	productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
		 return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
