package supratimdas.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import supratimdas.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		// first we will setup the properties class
		
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//supratimdas//resources//GlobalData.properties");
		
		// fis object will help to convert the path of Global data properties file into a input stream object
		
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser"); // we are using java ternary operator, where if the condition true it will execute first statement else second statement
		
	//	String browserName= prop.getProperty("browser"); // we wrote the browser value in properties file and passing it
		
		if(browserName.contains("chrome"))
		{
			
		ChromeOptions options= new ChromeOptions();
		WebDriverManager.chromedriver().setup();  // Just by adding WebDriverManager dependecy to pox.xml file and giving this line we can call chromeDriver object

		if(browserName.contains("headless"))
		{
		
		options.addArguments("headless");  // for running headless mode
		}
		
		 driver= new ChromeDriver(options);   // we are passing that here 
		 driver.manage().window().setSize(new Dimension(1440,900));  // to run in full screen
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			// firefox initilization, same as above chromedriver
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge initialization,  same as above chromedriver
		}
		
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		return driver;
		
	}
	
	  public String getScreenshot(String testCaseName, WebDriver driver) throws IOException   // this method taking screenshots
	  { 
		  TakesScreenshot ts=  (TakesScreenshot)driver;
		 File source=  ts.getScreenshotAs(OutputType.FILE);
		 
		 File file= new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");   // this is path where the screenshot will be stored
		 
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	  }
	  
	
	@BeforeMethod(alwaysRun= true)
	
	public LandingPage launchApplication() throws IOException
	{ 
		//if we have 100 cases for that first case always will open the launch page of the website
		// to land on the first login page commonly for all 
		
		driver= initializeDriver();
		landingPage= new LandingPage(driver);
		
		// creating object of landing page class over here
		// now above driver object is having life over here, so we will send that as an argument to landingpage class
		
		landingPage.goTo();  // asking to goto method of landing page where the url of the website is present
		return landingPage;
		
	}
	
//	@AfterMethod (alwaysRun= true)
//	public void teardown()
//	{
//		driver.close();
//	}
//	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		// read json file from the path and storing that into a string
		// in the path we are giving json file path of purchase order
		String jsonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
	// now we need to convert the json string to a hashmap
		
		ObjectMapper mapper= new ObjectMapper();
	
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	
	     return data;
	}
	

}
