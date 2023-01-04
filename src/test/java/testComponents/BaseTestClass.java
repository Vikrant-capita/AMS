package testComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseTestClass {

	public WebDriver driver;
	public String getScreenshotPath(String methodname,WebDriver driver) throws IOException
	{
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 //C:\Users\P50096390\Documents\Projects\AMS Project\AMS\screenshots
	    // String  destinationfile = "C:\\Users\\P50096390\\Documents\\Projects\\AMS Project\\AMS\\screenshots\\"+methodname+".png";
	    
	     String  destinationfile = System.getProperty("user.dir")+"\\screenshots\\"+methodname+".png";

	     //String  destinationfile = System.getProperty("user.dir")+"\\ExtentReports\\"+methodname+".png";
	     FileUtils.copyFile(scrFile, new File(destinationfile));
		 return destinationfile;
	}
	
	
	
	
	
}
