package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	  WebDriver driver;
	public Properties prop;
	
	//@SuppressWarnings("deprecation")
	public WebDriver initializeDriver() throws IOException {
		Properties prop=getProperties();
		String browserName=prop.getProperty("browser"); //!=null ? System.getProperty("browser"):System.getProperty("browser");
		String url = prop.getProperty("prodUrl");
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get(url);
		}
		
		else if(browserName.contains("firefox")) {
			
		}
		
		//driver.manage().timeouts().im
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//return driver;
		return driver;
		
		
	}
	
	public Properties getProperties() throws IOException {
		Properties prop=new Properties();
		//FileInputStream fis= new FileInputStream("C:\\Users\\P50096390\\Documents\\Projects\\AMS Project\\AMS\\src\\test\\java\\resources\\data.properties");
		//FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		//FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//data.properties");
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\data.properties");
		prop.load(fis);
		return prop;
		
	}
	

}
