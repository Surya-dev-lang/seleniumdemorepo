package tests;

import java.io.IOException;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedStatus) throws IOException, InterruptedException {
		
		log = LogManager.getLogger(LoginTest.class.getName());

		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropDown().click();
		log.debug("Clicked on Login Dropdown");
		landingpage.loginOption().click();

		LoginPage loginpage = new LoginPage(driver);
		// loginpage.emailAddressField().sendKeys(prop.getProperty("email"));
		// loginpage.passwordField().sendKeys(prop.getProperty("password"));
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Entered Username Email ID");
		loginpage.passwordField().sendKeys(password);
		log.debug("Entered Password");
		loginpage.loginButton().click();
		Thread.sleep(2000);

		AccountPage accountpage = new AccountPage(driver);
		String actualStatus = null;

		try {
			if (accountpage.editAccountInfoOption().isDisplayed()) {
				actualStatus = "Success1";
				log.debug("Succeffily Logged In");
			}

		} catch (Exception e) {
			actualStatus = "Failure";
		}

		Assert.assertEquals(expectedStatus, actualStatus);

	}

	@BeforeMethod
	public void openApplication() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "srknt.sahu90@yahoo.com", "12345", "Success" },
				{ "dummy@gmail.com", "12456", "Failure" } };
		return data;
	}

}
