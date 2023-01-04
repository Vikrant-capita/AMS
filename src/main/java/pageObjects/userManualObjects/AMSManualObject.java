package pageObjects.userManualObjects;

import java.time.Clock;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AMSManualObject  {

	public	WebDriver driver;

	public 	AMSManualObject(WebDriver driver) {
		super();
		this.driver=driver;
		
	}
	
	By clickOnAMSManual=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn9Nodes']/table/tbody/tr[2]/td[4]/a[@id='TreeMenu1_MenuTreeViewt10']");
	By clickOnAMSFAQ=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn9Nodes']/table/tbody/tr[2]/td[4]/a[@id='TreeMenu1_MenuTreeViewt11']");

		
	public void getClickOnAMSManual() {
		driver.findElement(clickOnAMSManual).click();
	}
	
	public void getClickOnAMSFAQ() {
		driver.findElement(clickOnAMSFAQ).click();
	}




}
