package supratimdas.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import supratimdas.resources.ExtentReporterNG;

public class Listerners extends BaseTest implements ITestListener{    // ITestListener is some methods provided by TestNG
	
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal <ExtentTest>();  //Thread safe, by this each object creation will have it's own thread, it won't inturrpt the other overriding variable
	
	@Override
	public void onTestStart(ITestResult result)  // Under ITestListener, this is a by deafult method, which will always execute first
	{
		test= extent.createTest(result.getMethod().getMethodName());     // so, first it will create extent report then it will go to actual execution
	
		extentTest.set(test);          //Unique thread id

	}
	
	
	@Override
	public void onTestSuccess(ITestResult result)     // under this block passed test case execution details we are checking
	{
		extentTest.get().log(Status.PASS, "Test passed");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result)     // under this block passed test case execution details we are checking
	{
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		String filepath = null;                          
		try {                                                             // use are using try/catch block to get the screenshot
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());	// now we will write the scenario in that way so it will take screenshot when Test case will be failed
	}
	
	
	@Override
	public void onFinish(ITestContext context)     // under this block passed test case execution details we are checking
	{
		extent.flush();
	}
	

}
