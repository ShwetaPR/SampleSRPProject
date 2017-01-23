package PageClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import utility.BrowserFactory;



public class BasePage {
	
	protected WebDriver driver =null;
	public static Logger logger = Logger.getLogger(BasePage.class);
	public File orFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\OR.properties");
	public Properties propOR = new Properties();
	
	public BasePage() throws IOException{
		
		 driver = BrowserFactory.getDriver();
		 FileInputStream finOR = null;
		try {
			finOR = new FileInputStream(orFile);
		} catch (FileNotFoundException e) {
			
			logger.error("Caught File Not Found Exception for OR file ");
			
		}
		 propOR.load(finOR);
	}
	
	

}
