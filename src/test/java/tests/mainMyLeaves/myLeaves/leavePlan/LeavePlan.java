package tests.mainMyLeaves.myLeaves.leavePlan;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import resources.BaseTest;
import tests.Homepage.ValidateExceptionLeavesPendingActions;
import tests.Homepage.ValidateHomepage;
import tests.LoginTest.LoginPage;
import tests.managerMyTeamApproval.pendingLeaveRequest.MgrMyTeamLeaveRequestTest;
//import timeVerification.ValidationLeaveTypeAndCategoryList;
import utils.CalenderHandle;
import utils.LoginWithDiffUser;
import utils.excelDriven.excelDriven;
import utils.excelDriven.validationLeaveTypeAndCategoryList;

public class LeavePlan extends BaseTest{
	
	public WebDriver driver;
	public leavePlanObject ml;
	public String username;
	public String[] monthYearText1;
	public String[] monthYearText2;
	public CalenderHandle ch;
	String expectedDate;
	String monthDisplayed;
	String toDate;
	String toMonth;
	String toYear;
	public String calendarStatusText;
	public String scheduleTimeText;
	public String submitmsg;
	public LoginPage lp;
	public ValidateExceptionLeavesPendingActions userPenLeavHoliReq;
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test(priority=0)
	public void validateMyLeaves() throws InterruptedException, IOException {
		
	//to click on maim my leaves button ----------need to remove later--------------
	ml=new leavePlanObject(driver);
	scheduleTimeText=ml.getScheduleTime();
	
	System.out.println("schedule time in leave plan :"+scheduleTimeText);
	ml.getClickOnMyLeaves();
	
	//-----------------------------------------------
	
	Thread.sleep(2000);
	ml.getLeavePlan();
	String empName=ml.getEmployeeName();
	HomePageObject hp=new HomePageObject(driver);
	String ab=hp.getUserNameText1().split("e ")[1];
	//username=hp.userNameText;
	System.out.println("usename: "+ab);
	System.out.println(empName);
	System.out.println("outer class data "+username);
	
	Assert.assertEquals(empName, ab);
	
	excelDriven excel=new excelDriven();
	ArrayList<String> data=excel.getData(empName, "Username");
	
	String UserID=data.get(0);
	String Password=data.get(1);
	String Username=data.get(2);
	String EMPID=data.get(3);
	String ManagerName=data.get(4);
	String ManagerID=data.get(5);
	
	String empID=ml.getEmpID();
	String managerName=ml.getmanagerName();
	String managerID=ml.getmanagerID();
	//String managerID=ml.get
	
	Assert.assertEquals(empID, EMPID);
	Assert.assertEquals(managerName, ManagerName);
	Assert.assertEquals(managerID, ManagerID);
	}
	
	
	@Test(priority=1)
	public void validateLeavePlanFlow() throws IOException, InterruptedException {
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*
		ml.getFromDateClick();
		
		monthYearText1=ml.getMonthYear1();
		System.out.println("from month year :"+monthYearText1[0]);
		
		WebElement clickOnRightArrow1=ml.getclickOnRightArrow1();
		
		List<WebElement> allDateList1=ml.getDateList();
		
		*/
		
		Properties prop=getProperties();
		String fromMonth=prop.getProperty("fromMonth");
		String fromYear=prop.getProperty("fromYear");
		String fromDate=prop.getProperty("fromDate");
		String fromDateFullText=fromDate.concat("-"+fromMonth.substring(0,3)).concat("-"+fromYear);
		System.out.println("from date full text :"+fromDateFullText);
		
		ml.getFromDateClick(fromDateFullText);
		
//		WebElement monthYearEle1= ml.getMonthYearEle1();
//		
//		ch=new CalenderHandle();
//		ch.getCalendor(fromMonth, fromYear, fromDate, monthYearText1,clickOnRightArrow1, allDateList1,monthYearEle1);
//		Thread.sleep(2000);
		
		
		
		toMonth=prop.getProperty("toMonth");
		toYear=prop.getProperty("toYear");
		toDate=prop.getProperty("toDate");
		String toDateFullText=toDate.concat("-"+toMonth.substring(0,3)).concat("-"+toYear);
		System.out.println("to date full text :"+toDateFullText);
		
		ml.getToDateClick(toDateFullText);
		/*
		ml.getclickOnRightArrow2();
		driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_day_3_6")).click();
		System.out.println("to date selected ");
		
		
		ml.getToDateClick();
		monthYearText2=ml.getMonthYear2().split(", ");
		System.out.println("to month year :"+monthYearText2[0]);
		WebElement clickOnRightArrow2=ml.getclickOnRightArrow2();
		List<WebElement> allDateList2=ml.getDateList2();
		
		toMonth=prop.getProperty("toMonth");
		toYear=prop.getProperty("toYear");
		toDate=prop.getProperty("toDate");
		
		WebElement monthYearEle2= ml.getMonthYearEle2();
		*/
		
		//ch.getCalendor(toMonth, toYear, toDate, monthYearText2, clickOnRightArrow2, allDateList2,monthYearEle2);
//		Thread.sleep(2000);
//		// 'To date' greater than 'from date'
//		if(ml.getCalDateError().isDisplayed()) {
//			String calDateError=ml.getCalDateError().getText();
//		    System.out.println(" Condition 7 : Cal date Error Text :" +ml.getCalDateError().getText());
//			Assert.assertEquals(calDateError, "To date should be greater than equal From date.");
//			Assert.assertTrue(false);
//		}
		
		Thread.sleep(2000);
		
		CalendarDatesValidation calError= new CalendarDatesValidation(driver);
		calError.getLeaveTypeAndCat();	
		
		
//		ml.getLeaveType("Work From Home(WFH)");
//		Thread.sleep(4000);
//		ml.getCategory("--NA--");
//		ml.getRemark("Applying for CL");
//		Thread.sleep(2000);
//		String monthYearDisplayed=ml.getMonthYearCalDisplay().getText();
//		// monthDisplayed=ml.getMonthDisplay();
//		System.out.println("Month Displayed :"+monthYearDisplayed);
		//ml.getClickOnSubmitBtn();
		
		//calError.calendarErrorValidation(fromMonth, fromYear,fromDate, toMonth,toYear, toDate, scheduleTimeText);
	
		
	}
	
	
	

	@Test(priority=2, enabled=true)
	public void validateCalendor() throws InterruptedException {
		//
		System.out.println("month Displayed :"+monthDisplayed);
		System.out.println("Expected date  :"+expectedDate);
		//Assert.assertEquals(monthDisplayed,expectedDate);		//validate passed month and year with calendor month and year
		Thread.sleep(1000);
		List<WebElement> calendarList=ml.getCalendarDateList();
		for(int i=0; i<=calendarList.size()-1; i++) {
			String text=calendarList.get(i).getText();
			System.out.println("text :" +text);
			System.out.println("to date :" +toDate);
			if(toDate.contains(text)) {
				calendarStatusText=ml.getCalendarStatusList();
				System.out.println("calendar text inside loop :"+ calendarStatusText);
				break;
			}
		}
		if(calendarStatusText.contains("Scheduled")||calendarStatusText.contains("WO")) {
		Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void validateStatusOfSubmittedRequest() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> workingDateList=ml.getWorkingDateList();
		System.out.println("working date size list :"+workingDateList.size());
		for(int i=0;i<=workingDateList.size()-1;i++) {
			String[] workingDateArrText=workingDateList.get(i).getText().split("-");
			System.out.println("working date text :"+ workingDateList.get(i).getText());
			System.out.println("working last date text :"+workingDateList.get(i).getText());
			if(workingDateArrText[0].contains(toDate)&&workingDateArrText[1].contains(toMonth)&&workingDateArrText[1].contains(toYear)) {
				boolean flag=ml.getLeaveStatusList().get(i).getText().contains("Waiting for approval");
				Assert.assertTrue(flag);
			}
		}
	}
	
	
	@Test(priority=5)
	public void validateUserLogout() throws InterruptedException {
		lp.validateLogout();
	}
	
	@Test(priority=6)
	//Login with Manager and verify the request and approve or reject
	public void validateManagerLoginApproval() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		
		
		//===== Write code for users request validation and approval/ rejection==================
		MgrMyTeamLeaveRequestTest TLR=new MgrMyTeamLeaveRequestTest();
		TLR.validateManagerMyPendingAction1(driver);
		TLR.validateMyTeamLeaveRequest1(driver);
		TLR.validateManagerMyPendingAction1(driver);	
	}
	
	@Test(priority=7)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
	
	
	@Test(priority=8)
	public void validateUserAppliedLeaveReq() throws InterruptedException, IOException {
		
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		Thread.sleep(2000);
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		List<String> pendingActionText=userPenLeavHoliReq.validatePendingLeave1(driver);
		System.out.println("User Pending action text :"+pendingActionText);
		
	}
	
	
	
	
	
	
	
	
	
		
	
	@AfterTest(enabled=true)
	public void tearDown() {
		
	driver.quit();
	}
}

