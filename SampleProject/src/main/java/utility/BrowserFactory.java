package utility;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BrowserFactory extends BaseClass {

	public BrowserFactory() {
		super();
	}

	public static int TIMEOUT = 30; 
	private static Logger Log = Logger
			.getLogger(BrowserFactory.class.getName());
	private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(String browser) {

		System.out.println("Executing Before Suite..");
		switch (browser) {
		case "Firefox":
			System.out.println("Broswer is Firefox");
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir")
							+ "//src//main//resources//geckodriver.exe");
			initFirefoxDriver();
			break;

		case "Chrome":
			System.out.println("Broswer is Chrome");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "//src//main//java//resources//chromedriver.exe");
			initChromeDriver();
			break;

		default:
			System.out.println("Default driver is Firefox Driver");
			initFirefoxDriver();
			break;

		}
	}

	public static WebDriver getDriver() {

		return threadDriver.get();
	}

	public static void setWebDriver(WebDriver webDriver) {
		threadDriver.set(webDriver);
	}

	/**
	 * Set Default timeout for WebDriver
	 * 
	 * @param timeout
	 *            - seconds to wait web element
	 */

	public static void setDefaultTimeout(int timeout) {
		TIMEOUT = timeout;
	}

	/**
	 * Set Wait and Script timeouts for WebDriver
	 * 
	 * @param timeout
	 *            - seconds to wait element and running script
	 */
	public static void setTimeout(int timeout) {
		BrowserFactory.getDriver().manage().timeouts()
				.implicitlyWait(timeout, TimeUnit.SECONDS);
		BrowserFactory.getDriver().manage().timeouts()
				.setScriptTimeout(timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * Set Wait and Script timeouts for WebDriver
	 * 
	 * @param timeout
	 *            - milliseconds to wait element and running script
	 */
	public static void setTimeoutMls(int timeout) {
		BrowserFactory.getDriver().manage().timeouts()
				.implicitlyWait(timeout, TimeUnit.MILLISECONDS);
		BrowserFactory.getDriver().manage().timeouts()
				.setScriptTimeout(timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * initialization FirefoxDriver
	 * 
	 * @param capabilities
	 *            - desired capabilities
	 */
	public static void initFirefoxDriver(Capabilities capabilities) {
		Log.info("Initialization Firefox Driver");
		setWebDriver(new FirefoxDriver(capabilities));
		setTimeout(TIMEOUT);
		getDriver().manage().window().maximize();
	}

	/**
	 * initialization ChromeDriver
	 * 
	 * @param capabilities
	 *            - desired capabilities
	 */
	public static void initChromeDriver(Capabilities capabilities) {
		Log.info("Initialization Chrome Driver");
		setWebDriver(new ChromeDriver(capabilities));
		setTimeout(TIMEOUT);
		getDriver().manage().window().maximize();
	}

	/**
	 * initialization FirefoxDriver
	 */
	public static void initFirefoxDriver() {
		Log.info("Initialization Firefox Driver");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();

	}

	/**
	 * initialization ChromeDriver
	 * 
	 */
	public static void initChromeDriver() {
		Log.info("Initialization Chrome Driver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Arrays.asList("--start-maximized", "--test-type",
				"--ignore-certificate-errors", "--disable-popup-blocking",
				"--allow-running-insecure-content", "--disable-translate",
				"--always-authorize-plugins"));
		setWebDriver(new ChromeDriver(options));
		// setTimeout(TIMEOUT);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite...");
		getDriver().quit();
	}

}
