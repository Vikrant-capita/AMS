package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

public class Synchronization {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public Synchronization(WebDriver driver) {
		this.driver=driver;
	}
	
    public void visibilityOfElement() {

    	wait = new WebDriverWait (driver, Duration.ofSeconds(30));

    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0")));
    }
    
    public void invisibilityOfEleLocated() {
    	wait =new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_LBLWorkingDate_0")));
    }
	
    //workingDate
	
	
	
}
