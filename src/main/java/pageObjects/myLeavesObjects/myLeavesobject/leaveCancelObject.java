package pageObjects.myLeavesObjects.myLeavesobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class leaveCancelObject {

	public WebDriver driver;
	Select s ;
	
	public leaveCancelObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By leaveCancel=By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel4");
	
	By empName =By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label1");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label2");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label3");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label4");
	
	By tableExist=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel");
	By leaveTypeList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr/td[4]");
	By fromDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr/td[5]");
	By toDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr/td[6]");
	By numberOfDays=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr/td[7]");
	
	By leaveScheduleRowData=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr["+1+"]/td");
	
	By clickOnCancel=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel4_GridViewLeaveCancel']/tbody/tr/td[1]/input");
	
	public void getClickOnLeaveCancel() {
		driver.findElement(leaveCancel).click();
	}

	
	public String getEmpName() {
		return driver.findElement(empName).getText();
		
	}
	public String getEmpID() {
		return driver.findElement(empID).getText();
		
	}
	
	public String getManagerName() {
		return driver.findElement(managerName).getText();
		
	}
	public String getManagerID() {
		return driver.findElement(managerID).getText();
		
	}
	
	public boolean getTableExist() {
		return driver.findElement(tableExist).isDisplayed();
	}
	
	public List<WebElement> getLeaveTypeList_Cancel() {
		return driver.findElements(leaveTypeList);
	}
	
	public List<WebElement> getFromDateList() {
		return driver.findElements(fromDateList);
	}
	
	public List<WebElement> getToDateList() {
		return driver.findElements(toDateList);
	}
	
	public List<WebElement> getNumberOfDays() {
		return driver.findElements(numberOfDays);
	}
	
	public void getLeaveScheduleRowData() {
		//drive		
			
	}
	
	public List<WebElement> getClickOnCancel() {
		return driver.findElements(clickOnCancel);
	}
	
}
