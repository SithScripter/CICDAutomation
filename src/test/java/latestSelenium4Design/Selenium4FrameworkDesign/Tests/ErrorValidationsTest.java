package latestSelenium4Design.Selenium4FrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import latestSelenium4Design.Selenium4FrameworkDesign.TestComponents.*;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void invalidLoginCredentials() throws IOException, InterruptedException {
		
		int a=5;
		int b=5;
		Assert.assertTrue((a==b));
		System.out.println("invalid login credentials method 1");
	}
	
	@Test(enabled = true)
	public void invalidLoginCredentials2() throws IOException, InterruptedException {
		
		System.out.println("invalid login credentials method 2");
	}

}
