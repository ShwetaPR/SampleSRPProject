package utility;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BrowserFactory extends BaseClass {
	
	public BrowserFactory(){
		super();
	}
	//private static  WebDriver driver ;
	/*private static final Logger LOGGER = LoggerFactory
			.getLogger(BrowserFactory.class);*/
	public static int TIMEOUT = 30; // seconds

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(String browser) {

		System.out.println("Executing Before Suite..");
		switch (browser) { 
		case "FF":
			System.out.println("Broswer is Firefox");
			
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
					+ "//src//main//resources//geckodriver.exe");

			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities); 
			
			driver.manage().window().maximize();
			break;

		case "Chrome":
			System.out.println("Broswer is Chrome");
			System.setProperty(
					"webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "//src//main//resources//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments(Arrays.asList("--start-maximized", "--test-type",
					"--ignore-certificate-errors", "--disable-popup-blocking",
					"--allow-running-insecure-content", "--disable-translate",
					"--always-authorize-plugins"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;

				
		default:
			System.out.println("Default driver is Firefox Driver");
			
			
			driver = new FirefoxDriver();
			break;

		}
		
	}

	public static WebDriver getDriver() {

		return driver;
	}
	
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite...");
		getDriver().quit();
	}

	
	
}

