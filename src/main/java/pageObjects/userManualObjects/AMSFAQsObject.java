package pageObjects.userManualObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AMSFAQsObject {

	public	WebDriver driver;

	public 	AMSFAQsObject(WebDriver driver) {
		//super();
		this.driver=driver;
		
	}
	
	By clickOnAMSFAQ=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn9Nodes']/table/tbody/tr[2]/td[4]/a[@id='TreeMenu1_MenuTreeViewt11']");

		

	public void getClickOnAMSFAQ() {
		driver.findElement(clickOnAMSFAQ).click();
	}
	
	
	
	
}
