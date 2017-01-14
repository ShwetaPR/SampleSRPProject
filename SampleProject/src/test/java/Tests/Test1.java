package Tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.BaseClass;
import utility.BrowserFactory;
import PageClasses.LoginPage;

public class Test1 extends BaseClass {

	private WebDriver driver = null;
	public String site = prop.getProperty("URL");
	private String browser = prop.getProperty("browser");
	private String userName = prop.getProperty("username");
	private String password = prop.getProperty("password");
	private static Logger Log = Logger.getLogger(Test1.class.getName());

	@BeforeMethod
	public void beforeTestPreRequisites() {
		BrowserFactory bfactory = new BrowserFactory();
		bfactory.beforeSuite(browser);

		this.driver = BrowserFactory.getDriver();

	}

	@AfterMethod
	public void afterTestTearDowns() {
		Log.info("Tearing down browser window");
		driver.quit();

	}

	@Test(priority = 1)
	public void Test_1() throws IOException, InterruptedException {

		driver.get(site);

		LoginPage lp = new LoginPage();

		Log.info("Starting Test1");
		lp.login(userName, password)
				.composeMail("parlikar.shweta@gmail.com", "automatedText")
				.verifySentmail("automatedText");

	}

}
