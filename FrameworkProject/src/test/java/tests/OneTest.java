package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class OneTest extends Base {
	
	public WebDriver driver;

	@Test
	public void oneTestMethod() throws IOException {
		
		driver = initializeDriver();
		driver.get("https://www.google.co.in/");
		System.out.println("This is One Test");
		System.out.println("This is new line");
		System.out.println("This is second change");
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
