
package supratimdas.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import supratimdas.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;  
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country1;
	
	
	By results= By.cssSelector(".ta-results");
//	WebElement selectCountry;
	
	
	public void selectCountry(String countryName)
	{
		 Actions a= new Actions(driver);
		   a.sendKeys(country1, countryName).build().perform(); // searching india from the search box
			
		   waitForElementToBeAppear(By.cssSelector(".ta-results"));
		   
		  selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}


	
	
	

}
