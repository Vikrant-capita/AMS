package pageObjects.myLeavesObjects.myLeavesPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyLeavesPageObject {
	
	public WebDriver driver;
	
	public MyLeavesPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnMyLeaves=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn4Nodes']/table//tbody/tr/td[4]/a[@id='TreeMenu1_MenuTreeViewt5']");
	
	By 	pageName=By.cssSelector(".cmnheader");
	By leavePlanTab=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_header']/span/span/span/span");
	By myLeaveTab =By.xpath("//span[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_tab']/span/span/span");
	By leaveBalanceTab=By.xpath("//span[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_tab']/span/span/span");
	By leaveCancelTab=By.xpath("//span[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_tab']/span/span/span");
	
	
	public void getClickOnMyLeaves() {
		driver.findElement(clickOnMyLeaves).click();
	}
	
	public String getPageName() {
		return driver.findElement(pageName).getText();			//Leave Plan
	}
	public String getLeavePlanTab() {
		return driver.findElement(leavePlanTab).getText();		//Leave Plan
	}
	public String getMyLeaveTab() {
		return driver.findElement(myLeaveTab).getText();		//My Leave
	}
	public String getLeaveBalanceTab() {
		return driver.findElement(leaveBalanceTab).getText();		//Leave Balance
	}
	public String getLeaveCancelTab() {
		return driver.findElement(leaveCancelTab).getText();		//Leave Cancel
	}
	
	
	
}
