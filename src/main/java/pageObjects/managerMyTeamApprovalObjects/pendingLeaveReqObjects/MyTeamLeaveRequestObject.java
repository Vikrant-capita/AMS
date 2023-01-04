package pageObjects.managerMyTeamApprovalObjects.pendingLeaveReqObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyTeamLeaveRequestObject {

	public WebDriver driver;
	Select s ;
	
	public MyTeamLeaveRequestObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnPendingAction=By.cssSelector("#TreeMenu1_MenuTreeViewt22");
	By clickOnpendingLeaveRequest=By.xpath("//a[contains(text(),'Pending Leave Request')]");
	
	By clickOnMyTeamLeaveReq=By.cssSelector("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel2");
	
	//My leave request table
	By clickOnEmpName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_GridViewRequestLeave_LNKEmployeeName_0");
	By teamLeaveRequestTable=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridViewRequestLeave']/tbody/tr[2]/td");
	 
	By actionDD=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel2_DDLLeaveStatus");
	By submitBtn=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_ButSubmit");
	By submitMsg=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLMSG_Request");	
	
	public void getClickOnPendingAction() {
		driver.findElement(clickOnPendingAction).click();
	}
	public void getclickOnPendingLeaveRequest() {
		driver.findElement(clickOnpendingLeaveRequest).click();;
		
	}
	 
	public void getClickOnMyTeamLeaveReq() {
		driver.findElement(clickOnMyTeamLeaveReq).click();
	}
	
	public WebElement getClickOnEmpName() {
		return driver.findElement(clickOnEmpName);
		
	}
	
	public List<WebElement> getTeamLeaveRequestTable() {
	return	driver.findElements(teamLeaveRequestTable);
	}
	
	public void getActionDD(String act) {
		Select s=new Select(driver.findElement(actionDD));
		s.selectByVisibleText(act);
	}
	
	public void getSubmitBtn() {
		driver.findElement(submitBtn).click();
	}
	
	public String getSubmitMsg() {
		return driver.findElement(submitMsg).getText();
	}
	
}
