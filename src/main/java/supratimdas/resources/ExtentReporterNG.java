package supratimdas.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()
	{
		

		String path= System.getProperty("user.dir") + "\\reports\\index.html";  
		
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);    // this expects a path wheere the report should be created, this is responsible for creating report
		
		
		reporter.config().setReportName("Web Automation Results");      // the file we are creating we can change or set the name by this
		
		reporter.config().setDocumentTitle("Test Results");             // setting the document title

		
		ExtentReports extent= new ExtentReports();                     // we will define this globally so other methods can access this
		
		extent.attachReporter(reporter);                       // here we need to pass object of Extent Spark Reporter, so the main class will have knowledge about the object
		
		extent.setSystemInfo("Tester", "Supratim Das");   // We are providing tester name for the html file report
	
	//	extent.createTest(path);  -> instaed of writing here, sending to Listeners class
		
		return extent;
		
	}
}
