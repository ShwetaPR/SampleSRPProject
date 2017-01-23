package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public final class LocatorFactory {
	
	
	@SuppressWarnings("unused")
	private WebDriver driver = null;
	public static Logger logger = Logger.getLogger(LocatorFactory.class);
	@SuppressWarnings("unused")
	private void LocatorFactory() {

		this.driver = BrowserFactory.getDriver();

	}
	
	
	public enum LOCATOR_TYPE {
		CLASS_NAME, CSS_SELECTOR, ID , LINK_TEXT, NAME, PARTIAL_LINK_TEXT, TAG_NAME, XPATH;
	};
	

	
	public static By byLocator(LOCATOR_TYPE type , String reference)
	{
		switch(type) {
		
		case CLASS_NAME : return By.className(reference);
		
		case CSS_SELECTOR : return By.cssSelector(reference);
		
		case ID: return By.id(reference);
		case NAME : return By.name(reference);
		case LINK_TEXT :return By.linkText(reference);
		case PARTIAL_LINK_TEXT : return By.partialLinkText(reference);
		case TAG_NAME : return By.tagName(reference);
		case XPATH : return By.xpath(reference);
	
		}
		return null;
	
		
	}
	
	

}
