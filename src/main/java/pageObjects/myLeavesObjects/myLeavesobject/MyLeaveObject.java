package pageObjects.myLeavesObjects.myLeavesobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyLeaveObject {
 
	public WebDriver driver;
	public Select s;
	
	public MyLeaveObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myLeave =By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1");
	By empName= By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMName");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMID");
	By leaveType=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_DDLAbsenceLeaveType");
	
	
	
	public void getMyLeave() {
		driver.findElement(myLeave).click();
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
	
	public List<WebElement> getLeaveType(String ltype) {
		s = new Select(driver.findElement(leaveType));
		List<WebElement> leaveTypesOptions=	s.getOptions();
		s.selectByVisibleText(ltype);
		return leaveTypesOptions;
	}
	
	
	
	
}
