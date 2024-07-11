package supratimdas.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supratimdas.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver)  // this is a constructor, and it will always execute first
	{                                     // also in above line we are defining argumnet , which is passed from stand alone class
		
		
		super(driver);  // from child to parent we can send variable by super keyword and that should be caught in a constructor in parent class
		
		//initialization
		
		this.driver= driver;  
		PageFactory.initElements(driver, this); // by passing this we are initialzing the @find by
		
		// "this" always refer to current class instance variable
		// this.driver meaning, the driver variable of this class, which is initialized above 
		//we are passing landing page class driver object through argument and storing the value of it 
	}

      //	  WebElement userEmails= driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmails;
	
	@FindBy(id="userPassword")
	WebElement passwords;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	// Action method below:
	
	public ProductCatalogue loginApplication(String email, String password)  // we are sending argument from actual test( Landing page)
	
	{
		userEmails.sendKeys(email);  //passing the email value here from the landing page class
		passwords.sendKeys(password);  // passing the password value
		submit.click();
		 ProductCatalogue productCatalogue =new ProductCatalogue(driver);
		 return productCatalogue;
		
		
	 
	}
	
	//creating another Action method 
	
	public void goTo()  // this method will help us to go to landing page
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToBeAppear(errorMessage);
		return errorMessage.getText();
	}



}
