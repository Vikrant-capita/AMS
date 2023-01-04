package tests.myDetails;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.myDetailsObjects.myHolidayListObjects.MyHolidayObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveBalanceObject;
import tests.Homepage.ValidateExceptionLeavesPendingActions;
import tests.LoginTest.LoginPage;
import tests.managerMyTeamApproval.pendingHolidayRequest.MgrHolidayApprovalTest;
import utils.DateConversionFormat;
import utils.UserManagerDetailsValidation;

public class demoHoliday {

	
	
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
	
	
	@Test(priority=1)
	public void myHolidayList() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		md=new MyHolidayObject(driver);
		
		//pendingCountBeforeSubmit();
		
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
		public void pendingCountBeforeSubmit() throws IOException {
			userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
			List<String> allUserPendingActionText=userPenLeavHoliReq.validatePendingLeave1(driver);
			for(String list:allUserPendingActionText)
			{
				if((!list.contains("No pending request"))&& list.contains("Holidays") ) {
					holidayCountBeforeSubmit=Integer.parseInt(list.substring(list.length()-1));
					System.out.println("User pending action list before submit :"+list);
				}
			}
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
						//System.out.println("selected list : "+lists.get(i).getText());
						n++;
					}
					
				}
				//System.out.println("selected size(2) : "+selectedlist.size());
				md.getSubmitBtn();
				Thread.sleep(2000);
				submitedList=md.getSubmittedHolidayNameList();
				
				if(selectedlist.size()>holidayBalanceCount|| (selectedlist.size()+submitedList.size()>holidayBalanceCount) ) {
					alertMessage= md.getSubmitMsg();
					System.out.println("User Submitted Msg :"+alertMessage);
					Assert.assertEquals(alertMessage, "Already Credited or you can select maximum 2","You have selected more holiday list");
				}
				else
				{
					alertMessage= md.getSubmitMsg();
					System.out.println("User Submitted Msg :"+alertMessage);
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
//			if(selectedlist.size()==submitedList.size()) {
//				Assert.assertEquals(selectedlist,submitedList,"List not matched");
//				System.out.println("Selected and submitted count not matched");
//			}
//			else {
//				Assert.assertTrue(false,"Selected and submitted count not matched");
//				System.out.println("Selected and submitted count not matched");
//			}
			md.getClickOnCapitaAMS();
			pendingCountAfterSubmit();
		}
		
		//================================Pending count After Submit========================
		public void pendingCountAfterSubmit() throws IOException {
			userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
			List<String> allUserPendingActionText=userPenLeavHoliReq.validatePendingLeave1(driver);
			for(String list:allUserPendingActionText)
			{
				if((!list.contains("No pending request"))&& list.contains("Holidays") ) {
					holidayCountAfterSubmit=Integer.parseInt(list.substring(list.length()-1));
					//System.out.println("User pending action list before submit :"+list);
				}
			}
			if(holidayCountBeforeSubmit+selectedlist.size()>=holidayBalanceCount) {
				Assert.assertEquals(holidayCountAfterSubmit, holidayBalanceCount,"Submitted holiday count not matched with balance count");
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
			
	
	
}
