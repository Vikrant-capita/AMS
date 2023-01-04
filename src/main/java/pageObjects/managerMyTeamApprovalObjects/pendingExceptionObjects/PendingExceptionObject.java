package pageObjects.managerMyTeamApprovalObjects.pendingExceptionObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PendingExceptionObject {

	public WebDriver driver;
	Select s ;
	
	public PendingExceptionObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnPendingException=By.cssSelector("#TreeMenu1_MenuTreeViewt22");
	
	//emp and manager details
	By empName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMName");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMID");
	
	
	By exceptionCount =By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLException");
	By exceptionDetails=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[1]");
	
	By checkboxEmpName=By.xpath("//span[contains(text(),'Vikrant  Bingi')]//preceding::input[1]");
	By approveEmpName=By.xpath("//span[contains(text(),'Vikrant  Bingi')]//following::input[1]");
	By rejectEmpName=By.xpath("//span[contains(text(),'Vikrant  Bingi')]//following::input[2]");
	
	By submitMsg=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLMSG");
	

	
	
	public void getClickOnPendingException() {
		driver.findElement(clickOnPendingException).click();
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
	
	public String getExceptionCount() {
		return driver.findElement(exceptionCount).getText();
	}
	public List<WebElement> getExceptionDetails() {
		return driver.findElements(exceptionDetails);
	}
	
	public void getCheckboxEmpName() {
		driver.findElement(checkboxEmpName).click();
	}
	public void getApproveEmpName() {
		driver.findElement(approveEmpName).click();
	}
	public void getRejectEmpName() {
		driver.findElement(rejectEmpName).click();
	}
	public String getSubmitMsg() {
		return driver.findElement(submitMsg).getText();
	}
}
