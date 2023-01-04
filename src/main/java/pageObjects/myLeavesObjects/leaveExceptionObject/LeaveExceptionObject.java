package pageObjects.myLeavesObjects.leaveExceptionObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LeaveExceptionObject {
	
	public WebDriver driver;
	Select s;
	
	public LeaveExceptionObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnLeaveException=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn4Nodes']/table[4]/tbody");
	
	By empName=By.id("ContentPlaceHolderBody_LBLPEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_LBLPEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_LBLPRMName");
	By managerID=By.id("ContentPlaceHolderBody_LBLPRMID");
	
	//From date calendar
	By fromClickOnCal=By.id("ContentPlaceHolderBody_TXTFromLeaveDate");
	By fromMonthAndYear=By.id("ContentPlaceHolderBody_TXTFromLeaveDate_CalendarExtender_title");
	By fromClickOnRightArrow=By.id("ContentPlaceHolderBody_TXTFromLeaveDate_CalendarExtender_nextArrow");
	By fromAllDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TXTFromLeaveDate_CalendarExtender_daysTable']/tbody/tr/td/div");

	//To date calendar
	By toClickOnCal=By.id("ContentPlaceHolderBody_TXTToLeaveDate");
	By toMonthAndYear=By.id("ContentPlaceHolderBody_TXTToLeaveDate_CalendarExtender_title");
	By toClickOnRightArrow=By.id("ContentPlaceHolderBody_TXTToLeaveDate_CalendarExtender_nextArrow");
	By toAllDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TXTToLeaveDate_CalendarExtender_daysTable']/tbody/tr/td/div");

	By directdropdown=By.id("ContentPlaceHolderBody_DDLDirector");
	By leaveTypeDropdown=By.id("ContentPlaceHolderBody_DDLLeavePlanType");
	By categoryDropdown=By.id("ContentPlaceHolderBody_ddlCategories");
	By remarkBox=By.id("ContentPlaceHolderBody_TXTRemarks");
	By submitBTN=By.id("ContentPlaceHolderBody_IMGBTNSubmit");
	
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
	
	
	
	
	public void getFromDateClick() {
		driver.findElement(fromClickOnCal).click();
		
	}	
	public WebElement getFromMonthYear() {
		return driver.findElement(fromMonthAndYear);
	}
	
	public WebElement getFromClickOnRightArrow() {
		return driver.findElement(fromClickOnRightArrow);
	}
	
	public List<WebElement> getFromAllDateList() {
		List<WebElement> allDates=driver.findElements(fromAllDateList);
		return allDates;
	}

	

	public void getToDateClick() {
		driver.findElement(toClickOnCal).click();
		
	}
	
	public WebElement getToMonthYear() {
		return driver.findElement(toMonthAndYear);
	}
	
	public WebElement getToClickOnRightArrow() {
		return driver.findElement(toClickOnRightArrow);
	}
	
	public List<WebElement> getToAllDateList() {
		List<WebElement> allDates=driver.findElements(toAllDateList);
		return allDates;
	}
	
	
	public List<WebElement> getDirectDropdown(String directorName) {
		s=new Select(driver.findElement(directdropdown));
		s.selectByVisibleText(directorName);
		List<WebElement> directorList=s.getOptions();
		return directorList;
	}
	//Abhishek Basu
	
	public List<WebElement> getLeaveType(String leaveType) {
		s=new Select(driver.findElement(leaveTypeDropdown));
		//s.selectByVisibleText(leaveType);
		List<WebElement> leaveTypeList=s.getOptions();
		s.selectByVisibleText(leaveType);
		return leaveTypeList;
	}
	
	public void getCategory(String categoryName) {
		s=new Select(driver.findElement(categoryDropdown));
		s.selectByVisibleText(categoryName);
		
	}

	public void getRemarkBox() {
		driver.findElement(remarkBox).sendKeys("Leaves Exception Remark");;
	}
	
	public void getsubmitBTN() {
		driver.findElement(submitBTN).click();		
	}
	
	public void getClickOnLeaveException() {
		driver.findElement(clickOnLeaveException).click();;
		
	}
}
