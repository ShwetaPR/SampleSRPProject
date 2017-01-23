package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;




public class ElementActions extends WaitMethods {

	public static Logger logger = Logger.getLogger(ElementActions.class);
	public WebDriver driver = null;

	public ElementActions() {
		this.driver = BrowserFactory.getDriver();
	}

	public static boolean ClickAndWait(By locator) {
		boolean result = false;
		WebElement we = null;

		if (BrowserFactory.getDriver() == null) {
			logger.error("Driver is NULL");
		}

		we = getElementWithWait(locator);

		if (we != null) {
			try {
				we.click();
				result = true;
			} catch (Exception e) {
				logger.error("WebElement cannot be clicked");
				return false;
			}
		} else {
			logger.info("WebElement cannot be clicked as Element is NULL ");

			result = false;
		}

		return result;

	}

	public static boolean ClickElement(WebElement we) {
		boolean result = false;

		if (BrowserFactory.getDriver() == null) {
			logger.error("Driver is NULL");
		}

		if (we != null) {
			try {
				we.click();
				result = true;
			} catch (Exception e) {
				logger.error("WebElement cannot be clicked");
				return false;
			}
		} else {
			logger.info("WebElement cannot be clicked as Element is NULL ");

			result = false;
		}

		return result;

	}

	public static boolean TypeTextwithWait(By locator, String text) {
		boolean result = false;
		WebElement we = null;

		if (locator == null) {
			throw new IllegalArgumentException("Locator cannot be NULL");

		}
		if (text == null) {
			throw new IllegalArgumentException("Text cannot be NULL");
		}

		try {

			we = getElementWithWait(locator);
			if (we.isDisplayed()) {
				we.sendKeys(text);
				if (we.getText().equals(text))
					result = true;
				else
					result = false;
			}
		} catch (Exception e) {
			logger.info("Typing text into text box threw exception {}", e);
			result = false;

		}

		return result;
	}

	public static boolean TypeText(WebElement we, String text) {
		boolean result = false;

		if (we == null) {
			throw new IllegalArgumentException("Locator cannot be NULL");

		}
		if (text == null) {
			throw new IllegalArgumentException("Text cannot be NULL");
		}

		try {

			we.sendKeys(text);

			result = true;

		} catch (Exception e) {
			logger.info("Typing text into text box threw exception {}", e);
			result = false;

		}

		return result;
	}

	public static WebElement getElement(By locator) {
		WebElement we = null;

		if (locator == null) {
			throw new IllegalArgumentException("The locator cannot be null cannot be null.");
		}

		try {
			we = BrowserFactory.getDriver().findElement(locator);

		} catch (Exception e) {
			logger.warn("Getting element {} threw exception {} ", e);

		}
		return we;
	}
	
	public static boolean clearTextField(WebElement we) {
		boolean result = false;

		if (we == null) {
			logger.error("WebElement cannot be NULL");
			return false;
		}

		try {
			we.clear();
			result = true;
		} catch (Exception e) {
			logger.error("Cannot clear textfield ");
			result = false;
		}
		return result;
	}
	


}
