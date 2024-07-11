package supratimdas.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{    // when a class implements the interface then it has to implements the methods which are under that interface

	int count= 0;
	int maxTry= 1;  // we need to define how many times we want to retry
	
	@Override
	public boolean retry(ITestResult result) {   // we are writing this code, which will tell if a code is failing, whether we should again retry it to run it again
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}
	
	

}
