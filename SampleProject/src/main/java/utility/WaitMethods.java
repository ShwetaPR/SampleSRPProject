package utility;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitMethods {
	/**
	 * Manage WebDriver instances.
	 * 
	 * @author Shweta Parlikar
	 * 
	 */
	private static WebDriver driver = BrowserFactory.getDriver();
	

	private static Logger LOGGER = Logger.getLogger(WaitMethods.class);

	/**
	 * Waits for presence of an element
	 * 
	 * @param locator
	 *            locator of the element
	 * @return return the found WebElement
	 */
	public static boolean waitForElementPresent(By locator) {

		boolean found = false;
		WebElement we = null;
		final long startTime = System.currentTimeMillis();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(
				BrowserFactory.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);

		while ((System.currentTimeMillis() - startTime) < 91000)

		{
			try {
				we = wait.until(ExpectedConditions
						.presenceOfElementLocated(locator));
				if (we != null) {
					found = true;
					break;
				}
			} catch (StaleElementReferenceException stale) {
				LOGGER.info(
						"StaleElement Element reference exception thrown while waiting for locator.");
				return false;
			} catch (NoSuchElementException noSuch) {
				LOGGER.info("No Such Element exception thrown while waiting for locator");
				return false;
			}

			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			LOGGER.info("Total time taken is -----{}. ");

		}

		return found;
	}

	/**
	 * Waits for element to be visible
	 * 
	 * @param locator
	 *            locator of the element
	 */
	public static boolean waitForElementVisible(By locator) {
		boolean found = false;
		
		WebElement we = null;

		final long startTime = System.currentTimeMillis();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(
				BrowserFactory.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);

		while ((System.currentTimeMillis() - startTime) < 91000)

		{
			try {
				we = wait.until(ExpectedConditions
						.visibilityOfElementLocated(locator));

				if (we != null) {
					found = true;
					break;
				}
			} catch (StaleElementReferenceException stale) {
				LOGGER.info(
						"StaleElement Element reference exception thrown while waiting for WebElement {}");
				return false;
			} catch (NoSuchElementException noSuch) {
				LOGGER.info("No Such Element exception thrown while waiting for WebElement");
				return false;
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		LOGGER.info("Total time taken is ----- "+String.valueOf(totalTime));

		return found;

	}

	/**
	 * Method to return an element with wait
	 * 
	 * @param locator
	 *            locator of the element
	 */
	public static WebElement getElementWithWait(By locator) {
		WebElement we = null;
		boolean found = false;
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 91000) {
			try {
				FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout(30, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(StaleElementReferenceException.class)
						.ignoring(NoSuchElementException.class);
				we = wait.until(ExpectedConditions
						.visibilityOfElementLocated(locator));
				found = true;
			} catch (NoSuchElementException nse) {
				LOGGER.info("No Such elemment Exception occured while locating "
						+ locator);
				found = false;
			} catch (StaleElementReferenceException stale) {
				LOGGER.info(
						"Stale Element Reference Exception occured while locating {} ");
				found = false;
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time taken waiting for element " + totalTime);
		LOGGER.info("Total time taken waiting for element {}");
		if (found = true) {
			return we;
		}

		else {
			return null;
		}
	}

	public static WebElement getElement(By locator) {
		WebElement we = null;

		if (locator == null) {
			throw new IllegalArgumentException(
					"The locator cannot be null cannot be null.");
		}

		try {
			we = driver.findElement(locator);

		} catch (Exception e) {
			LOGGER.warn("Getting element {} threw exception {} ", e);

		}
		return we;
	}

	

}
