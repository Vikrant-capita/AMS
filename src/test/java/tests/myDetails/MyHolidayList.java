package tests.myDetails;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//import junit.framework.Assert;
import pageObjects.myDetailsObjects.myHolidayListObjects.MyHolidayObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveBalanceObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.Homepage.ValidateExceptionLeavesPendingActions;
import tests.LoginTest.LoginPage;
import tests.mainMyLeaves.myLeaves.LeaveBalance;
import tests.managerMyTeamApproval.pendingHolidayRequest.MgrHolidayApprovalTest;
import utils.DateConversionFormat;
import utils.UserManagerDetailsValidation;


public class MyHolidayList {
	public WebDriver driver;
	//public SoftAssert sa;
	public DateConversionFormat df;
	String appliedHolidayName;
	String submitMsgText;
	List<WebElement> holidayNameList;
	public LoginPage lp;
	public MyHolidayObject md;
	
	List<String> selectedlist;
	String alertMessage;
	SoftAssert sa = new SoftAssert();
	public ValidateExceptionLeavesPendingActions userPenLeavHoliReq;
	public List<String> submitedList;
	public int holidayBalanceCount;
	public int holidayCountBeforeSubmit;
	public int holidayCountAfterSubmit;
	public int countOfReject;
	public int holidayCountAfterApproval;
	public leaveBalanceObject lb;
	SimpleDateFormat sdf;
	Date d;
	int actualCountOfApproved=0;
	int actualWaitingApprovalCount=0;
	public List<String> allUserPendingActionTextBefore;
	
	
	@Test(priority=1)
	public void myHolidayList() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		md=new MyHolidayObject(driver);
		
		int pendingCountBeforeSubmit=pendingCountBeforeSubmit();
		
		md.getClickOnMyHolidayList();
		UserManagerDetailsValidation userMgr1=new UserManagerDetailsValidation(driver);
		userMgr1.usersManagerDetailsValidation(md.getEmpName(), md.getEmpID(), md.getManagerName(), md.getManagerID());
		
		sdf=new SimpleDateFormat("yyyy");
		d=new Date();
		String currentYear=sdf.format(d);
		//int currentYear=Integer.parseInt(sdf.format(d));
		md.getYear(currentYear);
		Thread.sleep(2000);
		String yelloHoliday=md.getYellowHoliday();
		sa.assertEquals(2022, "*Yellow background denotes Holidays falling on Saturday and Sunday");
		
//		List<WebElement> holidayList=md.getHolidayList();
//		for(WebElement list:holidayList) {
//			String holidayTittle=list.getText().split("- ")[1];
//			System.out.println(holidayTittle);	
//		}
		
		 
	}
	
	//=============Validation of all holidays are with future dates=============================
	@Test(priority=2)//(dependsOnMethods= {"amstest"})
	public void  validateMyHolidayList() throws InterruptedException
	{
		//driver.findElement(By.id("TreeMenu1_MenuTreeViewt1")).click();
		Thread.sleep(2000);
		//List<WebElement> lists = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label"));
		//System.out.println("size: "+lists.size());
		
		md=new MyHolidayObject(driver);
		List<WebElement> holidayListWOYellow= md.getholidayListWOYellow();
		for(WebElement list: holidayListWOYellow)
		{
			String text = list.getText();
			String schedule = text.split("- ")[1];
			System.out.println(schedule);
			df=new DateConversionFormat();
			df.dateFormatConversion(schedule);
	
		}
		//List<WebElement> yellowlist = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td//span/label"));
		List<WebElement> holidayListWithYellow= md.getholidayListWithYellow();
		for(WebElement list: holidayListWithYellow)
		{
			String text = list.getText();
			String schedule = text.split("- ")[1];
			//System.out.println(schedule);
			df=new DateConversionFormat();
			df.dateFormatConversion(schedule);
		}
	}
	
	//======================To validate Saturday and Sunday holidays are highlighted in yellow background=============
	@Test(priority=3)
	public void validateYellowHolidays() throws IOException {
		List<WebElement> lists=	md.getholidayListWithYellow();
		for(WebElement list:lists)
		{
			String text=list.getText().split(" -")[0].split("[.] ")[1];
			//System.out.println("yellow holiday text:"+text);
			if(text.contains("Saturday") || text.contains("Sunday"))
			{
				//System.out.println("Highlight yellow holiday are saturday or sunday");
				sa.assertTrue(true,"Highlight yellow holiday are saturday or sunday");
			}
			else
			{
				sa.assertTrue(false,"Highlight yellow holiday not saturday or sunday");
			}
		}
	}
	
	//================================Pending count before Submite========================
	//@Test(priority=4)
	public int pendingCountBeforeSubmit() throws IOException {
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		allUserPendingActionTextBefore=userPenLeavHoliReq.validatePendingLeave1(driver);
		for(String list:allUserPendingActionTextBefore)
		{
			if((!list.contains("No pending request"))&& list.contains("Holidays") ) {
				holidayCountBeforeSubmit=Integer.parseInt(list.substring(list.length()-1));
				System.out.println("User pending action list before submit :"+list);
				System.out.println("User pending action count before submit :"+holidayCountBeforeSubmit);
			}
			else {
				System.out.println("No pending Action ");
				holidayCountBeforeSubmit=0;
				 
			}
		}return holidayCountBeforeSubmit;
	}
	
	
	@Test(priority=4)
	public void validateSelectedHolidays() throws InterruptedException {
			List<WebElement> lists = md.getAllHolidayNameList();
			int beforelist = lists.size();
			//System.out.println("before list size (7) : "+beforelist);
			//System.out.println("submit list before submit (0) : "+ml.getsubmitedList().size());
			selectedlist = new ArrayList<>();
			int n=1;
			holidayBalanceCount=2;
			for(int i=0;i<lists.size();i++)
			{
				if(n<=holidayBalanceCount)
				{
				lists.get(i).click();
					selectedlist.add(lists.get(i).getText().split(" [....]")[0]);
					System.out.println("selected list : "+lists.get(i).getText());
					n++;
				}
				
			}
			//System.out.println("selected size(2) : "+selectedlist.size());
			md.getSubmitBtn();
			Thread.sleep(2000);
			submitedList=md.getSubmittedHolidayNameList();
			Thread.sleep(1000);
			System.out.println("submitted List size:"+submitedList.size());
			System.out.println("selected size : "+selectedlist.size());

			if((selectedlist.size()+submitedList.size()>holidayBalanceCount) ) {
				alertMessage= md.getSubmitMsg();
				System.out.println("User Submitted Msg :"+alertMessage);
				Assert.assertEquals(alertMessage, "Already Credited or you can select maximum 2","You have selected more holiday list");
			}
			else
			{
				alertMessage= md.getSubmitMsg();
				System.out.println("User Submitted Msg 1 :"+alertMessage);
				Assert.assertEquals(alertMessage,"Holiday applied","Alert message verification failed after submit");
			}
			
			
	}
		
	@Test(priority=5)
	public void validateSubmittedHolidayList() throws IOException {
		
		if(submitedList.size()>holidayBalanceCount)
		{
			//System.out.println("Alert messege : Submitted list limit exceeded");
			Assert.assertTrue(false,"Alert messege : Submitted list limit exceeded");
		}
		else
		{
			//System.out.println("submitted list within limit");
		}
		
		System.out.println("Selected list : "+selectedlist);
		System.out.println("submitted list : "+submitedList);
//		if(selectedlist.size()==submitedList.size()) {
//			Assert.assertEquals(selectedlist,submitedList,"List not matched");
//			System.out.println("Selected and submitted count not matched");
//		}
//		else {
//			Assert.assertTrue(false,"Selected and submitted count not matched");
//			System.out.println("Selected and submitted count not matched");
//		}
		md.getClickOnCapitaAMS();
		pendingCountAfterSubmit();
	}
		
		
		
		
	/*
		//================old code==================================
		//to click on second holiday checkbox
		List<WebElement> holidayCheckBoxList=md.getAllHolidayCheckBoxList();
		int beforeApplyHolidaysize=holidayCheckBoxList.size();
		holidayNameList=md.getAllHolidayNameList();
		for(int i=0;i<=holidayCheckBoxList.size()-1;i++) {
			holidayCheckBoxList.get(i+1).click();
		    String appliedHolidayNameText=holidayNameList.get(i+1).getText();
			break;
		}
		md.getSubmitBtn();
		Thread.sleep(2000);
		String submitMsgText=md.getSubmitMsg();
		System.out.println("submitted text :"+submitMsgText());
		int afterApplyHolidaysize=holidayCheckBoxList.size();
		System.out.println("after submitted holiday list size :"+afterApplyHolidaysize);
		
		//Assert.assertEquals(afterApplyHolidaysize, beforeApplyHolidaysize-1);  //after holiday applied list size is still showing same as previous..need to check diffrenet tech to locate element
		Assert.assertEquals(submitMsgText, "Holiday applied", "Holiday submit verification text/msg is not matching");
		
		//holiday table validation=================================
		if(md.gettableExist().isDisplayed()) {
			List<WebElement> holidayNameListTable= md.getHolidayNameList();
			System.out.println("holiday list size :"+holidayNameList.size());
			
			
		//=================Holiday Cancellation=========================	
			md.getCancelBtn();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			String cancelMsgText=md.getSubmitMsg();
			//Thread.sleep(2000);
			System.out.println("Cancel btn text :"+cancelMsgText);
			Assert.assertEquals(cancelMsgText, "Holiday deleted", "Holiday cancel verification text/msg is not matching");		
		*/
	
	
		
	//================================Pending count After Submit========================
	public void pendingCountAfterSubmit() throws IOException {
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		List<String> allUserPendingActionTextAfter=userPenLeavHoliReq.validatePendingLeave1(driver);
		for(String list:allUserPendingActionTextAfter)
		{
			if((!list.contains("No pending request"))&& list.contains("Holidays") ) {
				holidayCountAfterSubmit=Integer.parseInt(list.substring(list.length()-1));
				System.out.println("User pending action list before submit :"+list);
				System.out.println("User pending action list before submit :"+holidayCountAfterSubmit);
			}
			else {
				System.out.println("No pending Action ");
				holidayCountBeforeSubmit=0;
			}

			int actualSubmittedCount=holidayCountAfterSubmit-holidayCountBeforeSubmit;
			System.out.println("Actual submitted count :"+actualSubmittedCount);
	     }
			
		if(holidayCountBeforeSubmit+selectedlist.size()>=holidayBalanceCount) {
			//Assert.assertEquals(holidayCountAfterSubmit, holidayBalanceCount,"Submitted holiday count not matched with balance count");
		}
	}

	
	
	@Test(priority=6)
	public void validateUserLogout() throws InterruptedException {
		// TODO Auto-generated method stub
		lp.validateLogout();
		System.out.println("User Logout");
	}
	
	@Test(priority=7)
	public void validateMgrHolidayReqFlow() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		//driver=lp.driver;
		System.out.println("Manager logged in succesful");
		MgrHolidayApprovalTest mgrHoliAppr=new MgrHolidayApprovalTest();
		countOfReject= mgrHoliAppr.validateholidayReq1(driver); 
		System.out.println("Accepted or Rejected holiday count in manager : "+countOfReject);
		

	}
	
	@Test(priority=8)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
	
	@Test(priority=9)
	public void validateUserAppliedHolidays() throws InterruptedException, IOException {
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		Thread.sleep(2000);
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		List<String> pendingActionText=userPenLeavHoliReq.validatePendingLeave1(driver);
		System.out.println("User Pending action text :"+pendingActionText);
		for(String list:pendingActionText)
		{
			if((!list.contains("No pending request"))&& list.contains("Holidays") ) {
				holidayCountAfterApproval=Integer.parseInt(list.substring(list.length()-1));
				System.out.println("User pending action list before submit :"+list);
			}
		}
		
		int newCount= holidayCountAfterSubmit-countOfReject;
		System.out.println("Count of user submit - count of Manager approv/reject :"+newCount);
		if(newCount==holidayCountAfterApproval) {
			System.out.println("User dashboard holiday count :"+holidayCountAfterApproval);
			Assert.assertTrue(true);
		}
	}
		
	@Test(priority=10)
	public void validateHolidayListWithLeaveBalance() throws InterruptedException {
		
		leavePlanObject ml=new leavePlanObject(driver);
		//Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		
		lb=new leaveBalanceObject(driver);
		lb.getClickOnLeaveBalance();
		
		LeaveBalance lbTest=new LeaveBalance();
		List<String> rowDataList=lbTest.getBalanaceLeaveData("HOLIDAY + COH", driver);
		System.out.println("row data list size :" +rowDataList.size());
		int expectedAvailedCount= Integer.parseInt(rowDataList.get(3));
		System.out.println("Availed data :"+rowDataList.get(3));
		int expectedWaitingCount= Integer.parseInt(rowDataList.get(4));
		System.out.println("Waiting for approval data :"+rowDataList.get(4));
		md.getClickOnMyHolidayList();
		if(md.getTableExist().isDisplayed()) {
			List<String> holidayNameList= md.getSubmittedHolidayNameList();
			System.out.println("holiday list size :"+holidayNameList.size());
			List<String> holidayDatesList=md.getSubmittedHolidayDates();
			for(int i=0; i<holidayDatesList.size();i++) {
				System.out.println(" text  Date :"+holidayDatesList.get(i));
				//String holidayDate.split("-")[1];
				DateConversionFormat dfc=new DateConversionFormat();
				List<Integer> datesList =dfc.validateMonthText(holidayDatesList.get(i));
				DateFormat dateFormat = new SimpleDateFormat("dd");
				DateFormat yearFormat = new SimpleDateFormat("yyyy");
				DateFormat monthFormat = new SimpleDateFormat("MM");
				Date date1 = new Date();
				int systemDate = Integer.parseInt(dateFormat.format(date1));
				int systemYear = Integer.parseInt(yearFormat.format(date1));
				int systemMonth = Integer.parseInt(monthFormat.format(date1));
				if(systemMonth>=datesList.get(1)&&systemDate>datesList.get(0)) {
					actualCountOfApproved++;
					System.out.println("Approved count :"+actualCountOfApproved);
					Assert.assertEquals(actualCountOfApproved, expectedAvailedCount);
				}
				else if(systemMonth<=datesList.get(1)&&systemDate<datesList.get(0)&&holidayNameList.get(i).contains("Waiting for approval") ) {
					actualWaitingApprovalCount++;
					System.out.println("Waiting for approval count :"+actualWaitingApprovalCount);
					Assert.assertEquals(actualWaitingApprovalCount, expectedWaitingCount);
				}
				
				
			}
		}
		
	}
	
	
		
		
		


	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
	
}
