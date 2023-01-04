package pageObjects.homePageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class managerHomePageObjects {
	
	public WebDriver driver;
	Select s ;
	
	public managerHomePageObjects(WebDriver driver) {
		this.driver=driver;
	}
	
	//Pending action
	By leavesPendingAction=By.id("ContentPlaceHolderBody_UserStatus1_lbLeavePend");
	
	//my pending action list with count
	By allPendingActionList=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_tbtPendingAction']//table/tbody/tr/td/a");
	
	
	public WebElement getLeavesPendingAction() {
		return driver.findElement(leavesPendingAction);
	}
	public List<WebElement> getAllPendingActionList() {
		System.out.println("driver:"+driver);
		return driver.findElements(allPendingActionList);
	}
	
	

}
