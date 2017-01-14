package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class BaseClass {
	protected static WebDriver driver = null;
	protected Properties prop = new Properties();
	
	public File configFile = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\Config.properties");

	
	public BaseClass(){
		
		try{
			
			
			FileInputStream finConfig = new FileInputStream(configFile);
			prop.load(finConfig);
			
		}catch(FileNotFoundException fnfe)
		{
			System.out.println("FileNotFoundException fnfe");
		} catch (IOException e) {
			System.out.println("IOException e");
		}
		
	}
	
	
	
	
	
	
}
