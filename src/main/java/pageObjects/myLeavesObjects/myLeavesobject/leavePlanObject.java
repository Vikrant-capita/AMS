package pageObjects.myLeavesObjects.myLeavesobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class leavePlanObject {


	public WebDriver driver;
	Select s ;
	
	public leavePlanObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnMyLeaves=By.cssSelector("#TreeMenu1_MenuTreeViewt5");
	By leavePlan =By.cssSelector("#__tab_ContentPlaceHolderBody_TabContainer1_TabPanel3");
	By employeeName=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPEmployeeName");
	By empID =By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPEmployeeID");
	By managerName=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPRMName");
	By managerID=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPRMID");
	
	//Calendor
	By fromDate=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate");
	By monthYear1=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_title");
	By clickOnRightArrow1=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_nextArrow");
	By dateList1=By.xpath("//tbody[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_daysBody']/tr/td/div");
	
	By toDate=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate");
	By monthYear2=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_title");
	By clickOnRightArrow2=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_nextArrow");
	By dateList2 =By.xpath("//tbody[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_daysBody']/tr/td/div");
	
	//to date should be greater than from date
	By calDateError=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_CustomValidator1");
	
	//
	By leaveType=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_DDLLeavePlanType");
	By category=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_ddlCategories");
	By remark=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTRemarks");
	By submitBtn=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_IMGBTNSubmit");
	By monthDisplay=By.xpath("//table[@title='Scheduled ']/tbody/tr/td/table/tbody/tr/td[2]");
    
	By monthYearCalDisplay=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_CalPlanSchedule1_CalPlanSchedule']/tbody//tr/td/table/tbody/tr/td[2]");	
	By submitMessage=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLMSG");
	

	
	//calendar validation for signle date 
	By calendarDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_CalPlanSchedule1_CalPlanSchedule']/tbody/tr/following-sibling::tr[2]/td/a");
	By calendarStatusList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_CalPlanSchedule1_CalPlanSchedule']/tbody/tr/following-sibling::tr[2]/td/a[2]");
	
	//	Last leave request table
	By workingDateList=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]/td/div/table/tbody/tr/following-sibling::tr[2]/td[1]");
	//25-Nov-2022
	By leaveStatusList=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]/td/div/table/tbody/tr/following-sibling::tr[2]/td[2]");
	//Waiting for approval
	By leaveTypeList=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]/td/div/table/tbody/tr/following-sibling::tr[2]/td[3]");
	//Work From Home(WFH)
	
	//HomePage schedule time
	By scheduleTime=By.xpath("//span[@id='ContentPlaceHolderTitle_Lbltopmsg1']");
	
	public void getClickOnMyLeaves() {
		driver.findElement(clickOnMyLeaves).click();
	}
	
	public void getLeavePlan() {
		driver.findElement(leavePlan).click();
	}
	
	public String getEmployeeName() {
		String empName=driver.findElement(employeeName).getText();
		return empName;
	}
	
	public String getEmpID() {
		String EmpID=driver.findElement(empID).getText();
		return EmpID;
	}
	
	public String getmanagerName() {
		String ManagerName=driver.findElement(managerName).getText();
		return ManagerName;
	}
	public String getmanagerID() {
		String ManagerID=driver.findElement(managerID).getText();
		return ManagerID;
	}
	
	public void getFromDateClick(String fromdate) {
		driver.findElement(fromDate).sendKeys(fromdate);
		
	}
	
	public String[] getMonthYear1() {
		 return driver.findElement(monthYear1).getText().split(",");
	}
	
	// without spliting
	public WebElement getMonthYearEle1() {
		 return driver.findElement(monthYear1);
	}
	
	public WebElement getclickOnRightArrow1() {
		return driver.findElement(clickOnRightArrow1);
	}
	
	public List<WebElement> getDateList() {
		List<WebElement> allDates=driver.findElements(dateList1);
		return allDates;
	}
	
	public void getToDateClick(String todate) {
		driver.findElement(toDate).sendKeys(todate);;
		
	}
	
	
	public String getMonthYear2() {
		return driver.findElement(monthYear2).getText();
	}
	
	public WebElement getMonthYearEle2() {
		return driver.findElement(monthYear2);
	}
	
	public WebElement getclickOnRightArrow2() {
		return driver.findElement(clickOnRightArrow2);
	}
	
	public List<WebElement> getDateList2() {
		List<WebElement> allDates=driver.findElements(dateList2);
		return allDates;
	}
	
	public WebElement getCalDateError() {
		return driver.findElement(calDateError);
	}
		
	public void getLeaveType(String ltype) {
		s = new Select(driver.findElement(leaveType));
		s.selectByVisibleText(ltype);
	}
	
	public void getCategory(String cat) {
		s = new Select(driver.findElement(category));
		s.selectByVisibleText(cat);
	}
	
	public void getRemark(String rm) {
		driver.findElement(remark).sendKeys(rm);
	}
	
	public WebElement getMonthYearCalDisplay() {
		return driver.findElement(monthYearCalDisplay);
	}
	
	public void getClickOnSubmitBtn() {
		driver.findElement(submitBtn).click();
	}
	
	public String getSubmitMessage() {
		return driver.findElement(submitMessage).getText();
	}
	
	public String getMonthDisplay() {
		return driver.findElement(monthDisplay).getText();
	}
	
	// calendar validation
	public List<WebElement> getCalendarDateList() {
		return driver.findElements(calendarDateList);
	}
	
	public String getCalendarStatusList() {
		return driver.findElement(calendarStatusList).getText();
	}
	
	public List<WebElement> getWorkingDateList() {
		return driver.findElements(workingDateList);
	}
	
	public List<WebElement> getLeaveStatusList() {
		return driver.findElements(leaveStatusList);
	}
	
	public List<WebElement> getLeaveTypeList() {
		return driver.findElements(leaveTypeList);
	}
	public String getScheduleTime() {
		return driver.findElement(scheduleTime).getText();
		
	}
	

	
	
}
