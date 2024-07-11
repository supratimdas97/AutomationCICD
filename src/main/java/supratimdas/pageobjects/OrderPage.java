package supratimdas.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supratimdas.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkoutEle;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver= driver;  
		PageFactory.initElements(driver, this);
	}

	
	public Boolean VerifyOrderDisplay(String productname)
	
	{
		 Boolean match=	productNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
		 return match;
	}
	

}
