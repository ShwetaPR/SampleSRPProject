package PageClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.BaseClass;
import utility.BrowserFactory;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class LoginPage extends BaseClass {

	public WebDriver driver = null;

	public LoginPage() throws IOException {
		super();
		if (driver == null) {

			this.driver = BrowserFactory.getDriver();

		}
	}

	public HomePage login(String userName, String password) throws IOException,
			InterruptedException {

		driver.findElement(By.id("Email")).sendKeys(userName);
		Thread.sleep(3000);
		driver.findElement(By.id("next")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Passwd")).sendKeys(password);
		Thread.sleep(3000); // SP: better use instead of Thread.sleep is to wait for element presence by creating wrapper for fetching element
		driver.findElement(By.id("signIn")).click();
		
		verifySignoutButton();
		
		return (new HomePage());
	}

	public void verifySignoutButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
				.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")));
		
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
		
		
	   // Hamcrest assert - to verify signout button is avaialable
		assertThat(driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).getText(),is(equalTo("Sign out")));
		
		// to minimize signout frame
		driver.findElement(By
				.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
	}
}
