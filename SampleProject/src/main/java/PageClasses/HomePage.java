package PageClasses;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.BaseClass;
import utility.BrowserFactory;
import Tests.Test1;

public class HomePage extends BasePage {

	public WebDriver driver = null;
	private static Logger Log = Logger.getLogger(Test1.class.getName());
	private static HomePage homeInstance = null;
	
	String compose = propOR.getProperty("compose");
	String sendTo = propOR.getProperty("sendTo");
	String subject = propOR.getProperty("subjectbox");
	String messageBody = propOR.getProperty("messageBody");
	String sendBtn = propOR.getProperty("sendBtn");
	

	public HomePage() throws IOException {
		super();
		if (driver == null) {

			this.driver = BrowserFactory.getDriver();

		}
	}
	
	
	 public static HomePage getInstance() throws IOException{
	        if(homeInstance==null){
	        	homeInstance = new HomePage();
	        }
	        return homeInstance;
	    }

	public HomePage composeMail(String recipient, String text)
			throws IOException {
		
		

		WebDriverWait wdWait = new WebDriverWait(driver, 15);
		wdWait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[text()='COMPOSE']")));
		// Hamcrest assert to validate COmpose button is present
		assertThat(driver.findElement(By.xpath("//div[text()='COMPOSE']"))
				.getText(), is(equalTo("COMPOSE")));

		driver.findElement(By.xpath("//div[text()='COMPOSE']")).click();
		WebDriverWait wdWait1 = new WebDriverWait(driver, 15);
		wdWait1.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//span[text()='To']")));

		driver.findElement(By.xpath("//textarea[@aria-label='To']")).sendKeys(
				recipient);
		driver.findElement(By.name("subjectbox")).sendKeys(text);
		driver.findElement(By.xpath("//div[@aria-label='Message Body']"))
				.sendKeys(text);

		wdWait1.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[text()='Send']")));
		driver.findElement(By.xpath("//div[text()='Send']")).click();

		return (new HomePage());
	}

	public void verifySentmail(String text) throws InterruptedException {

		Boolean result = false;

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(.,'Sent Mail')]")).click();
		if (driver.findElements(By.xpath("//span[text()='" + text + "']")) != null) {
			System.out.println("Email Sent Successfully... :)");
			result = true;
		} else {
			System.out.println("Email Sending Failed... :(");
			result = false;
			Assert.assertTrue(result, "Email is not sent");
		}

	}

}
