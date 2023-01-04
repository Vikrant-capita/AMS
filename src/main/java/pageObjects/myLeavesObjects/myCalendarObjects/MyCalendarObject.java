package pageObjects.myLeavesObjects.myCalendarObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyCalendarObject {

	public WebDriver driver;
	public SimpleDateFormat sdfMonth;
	public Date d;
	public String month;
	public String substringMonth;
	
	public MyCalendarObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By clickOnMyCalendar=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn4Nodes']/table[3]/tbody/tr[2]/td[4]");
	By empID=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/caption");
	//By calendarDateList=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]/td/a[1]");
	By calendarDateList=By.xpath("//a[contains(@title,'November')]");
	//Tittle= October 31		getText=31
	//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]
	
	//By calendarStatusList1=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]/td/a[2]");
	
	//Homepage Elements			
	//By workingDateList1=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]");
			//Wednesday   ,  09 Nov 2022
	//By homePageAttendanceStatus=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]");
	//WFH  WO  UA
	
	//By monthCaleder = By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/td/table/tbody/tr/td[2]");
	//Current Month Name 
	//By clickOnPreviousMonth = By.xpath("//a[@title='Go to the previous month']");
	// click on oct month
	
	
	//2nd by yo
	
	By calendarStatusList1=By.xpath("//a[contains(@title,'November')]/parent::td/a[2]");
	//below one will only take month of nov *******************
	By workingDateList1=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]/span[contains(text(),'Nov')]");
	//By homePageAttendanceStatus=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]");
	//WFH  WO  UA
	//below list only take nov status *******************
	By homePageAttendanceStatus=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]/span[contains(text(),'Nov')]/parent::td/parent::tr/td[4]");
	By monthCaleder = By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/td/table/tbody/tr/td[2]");
	//Current Month Name 
	By clickOnPreviousMonth = By.xpath("//a[@title='Go to the previous month']");
	// click on oct month
	
	//======================================
	
	//======================================
	
//	public void getClickOnMyCalendar()
//	{
//		driver.findElement(clickOnMyCalendar).click();
//	}
	
	public void getClickOnMyCalendar() {
		driver.findElement(clickOnMyCalendar).click();
	}
	
	public String  getEmpID() {
		return driver.findElement(empID).getText();
	}
	
	public List<WebElement> getCalendarDateList() {
		sdf();
		List<WebElement> calendarDateAndMonthList=driver.findElements(By.xpath("//a[contains(@title,'"+month+"')]"));
		return calendarDateAndMonthList;
	}
	
	public List<WebElement> getCalendarStatusList() {
		sdf();
		List<WebElement> calendarStatusList=driver.findElements(By.xpath("//a[contains(@title,'"+month+"')]/parent::td/a[2]"));
		return calendarStatusList;
	}
	
	public List<WebElement> getWorkingDateList() {
		sdf();
		List<WebElement> workingDateList=driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]/span[contains(text(),'"+substringMonth+"')]"));
		return workingDateList;
	}
	
	public List<WebElement> getAttendanceStatus() {
		sdf();
		List<WebElement> attendantStatus=driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]/span[contains(text(),'"+substringMonth+"')]/parent::td/parent::tr/td[4]"));
		return attendantStatus;
	}
	
	public String getMonthCalender()
	{
		return driver.findElement(monthCaleder).getText();
	}

	public void getPrevMonthclick()
	{
		driver.findElement(clickOnPreviousMonth).click();;
	}
	
	public void sdf() {
	sdfMonth=new SimpleDateFormat("MMMM");
	d=new Date();
	month=sdfMonth.format(d);   //this will give "November"
	substringMonth=month.substring(0, 3);
}
}

