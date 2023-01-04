package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveBalanceObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveCancelObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.Homepage.ValidateExceptionLeavesPendingActions;
import tests.LoginTest.LoginPage;
import utils.GetStringListFromWebElementList;
import utils.JavaDatesAndCalendar;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class leaveCancel {
	
	public WebDriver driver;
	public LoginPage lp;
	public leavePlanObject ml;
	public 	leaveCancelObject lb;
	public List<WebElement> workingDateList;
	public List<WebElement> leaveTypeList;
	public List<WebElement> leaveStatusList;
	public List<WebElement> leaveTypeList_Cancel;
	public List<String> stringWorkingDateList;
	public List<String> stringLeaveTypeList;
	public List<String> stringLeaveStatusList;
	public GetStringListFromWebElementList stringFromWebElement;
	public List<WebElement> fromDateList;
	public List<WebElement> toDateList;
	public List<String> stringLeaveTypeList_Cancel;
	public List<String> stringFromDateList;
	public List<String> stringToDateList;
	public List<WebElement> numberOfDays;
	public List<String> stringNumberOfDays;
	String stringLeaveType;
	public ValidateExceptionLeavesPendingActions userPenLeavHoliReq;
	
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		driver=lp.validatelogin();
	}
	
	
	@Test(priority=1)
	public void validateLeaveCancel() throws InterruptedException, IOException {
		
		driver = lp.driver;
		
		//to click on maim my leaves button ----------need to remove later--------------
		ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		//-----------------------------------------------
		
		lb=new leaveCancelObject(driver);
		stringFromWebElement=new GetStringListFromWebElementList();
		workingDateList=ml.getWorkingDateList();
		stringWorkingDateList=stringFromWebElement.getStringDataFromList(workingDateList);
		leaveTypeList=ml.getLeaveTypeList();
		stringLeaveTypeList=stringFromWebElement.getStringDataFromList(leaveTypeList);
		leaveStatusList=ml.getLeaveStatusList();
		stringLeaveStatusList=stringFromWebElement.getStringDataFromList(leaveStatusList);

		
		lb.getClickOnLeaveCancel();
		String empName=lb.getEmpName();
		
		UserManagerDetailsValidation userMgr=new UserManagerDetailsValidation(driver);
		userMgr.usersManagerDetailsValidation(lb.getEmpName(), lb.getEmpID(), lb.getManagerName(), lb.getManagerID());	
	}
	
	@Test(priority=2)
	public void validateLeaveScheduleTable() {
		
	leaveTypeList_Cancel=lb.getLeaveTypeList_Cancel();
	stringLeaveTypeList_Cancel=stringFromWebElement.getStringDataFromList(leaveTypeList_Cancel);
	fromDateList=lb.getFromDateList();
	stringFromDateList=stringFromWebElement.getStringDataFromList(fromDateList);
	toDateList=lb.getToDateList();
	stringToDateList=stringFromWebElement.getStringDataFromList(toDateList);
	numberOfDays=lb.getNumberOfDays();
	stringNumberOfDays=stringFromWebElement.getStringDataFromList(numberOfDays);
	
	
	
		try {
			if (lb.getTableExist()) {
				System.out.println("inside main if");
				for(int i=0;i<leaveTypeList_Cancel.size();i++) {
					System.out.println("inside main for loop");
					stringLeaveType=leaveTypeList_Cancel.get(i).getText();
					System.out.println("leave type list :"+leaveTypeList_Cancel);
					//for(int j=0;j<stringFromDateList.size();j++)
					if(!stringFromDateList.get(i).equalsIgnoreCase(stringToDateList.get(i))) {
						System.out.println("inside if");
						System.out.println("from date :"+stringFromDateList.get(i)+"\t to date :"+stringToDateList.get(i));
						
						String leaveTypeText =leaveTypeList_Cancel.get(i).getText();
						String str_date =stringFromDateList.get(i);
						String end_date =stringToDateList.get(i);
						List<String> daysBetweenDatesList=JavaDatesAndCalendar.getDaysBetweenDates(str_date,end_date);
						Assert.assertEquals(Integer.toString(daysBetweenDatesList.size()), stringNumberOfDays.get(i));
						
						System.out.println("days size :"+daysBetweenDatesList.size());
						for(int j=0;j<daysBetweenDatesList.size();j++) {
							for(int k=0;k<stringWorkingDateList.size();k++) {
								if(daysBetweenDatesList.get(j).contains(stringWorkingDateList.get(k))) {
									System.out.println("from date :"+daysBetweenDatesList.get(j) +" \t leave plan working date :"+stringWorkingDateList.get(k));
									System.out.println("leave type :"+stringLeaveType +" \t leave type working date :"+stringLeaveTypeList.get(k));

									Assert.assertEquals(stringLeaveType,stringLeaveTypeList.get(k),"Leave type not matching");
								}
							}
						}
					}
					else {
						System.out.println("inside else");

						for(int j=0; j<stringFromDateList.size();j++) {
							for(int k=0;k<stringWorkingDateList.size();k++) {
								if(stringFromDateList.get(j).contains(stringWorkingDateList.get(k))) {
									System.out.println("from date :"+stringFromDateList.get(j) +" \t leave plan working date :"+stringWorkingDateList.get(k));
									System.out.println("leave type :"+stringLeaveTypeList_Cancel.get(j) +" \t leave type working date :"+stringLeaveTypeList.get(k));

									Assert.assertEquals(stringLeaveTypeList_Cancel.get(j),stringLeaveTypeList.get(k),"Leave type not matching");
								}
							}
						}
					}
					
				}
			}
						
		}catch(Exception e) {
			System.out.println("exception Name: "+e.getMessage());
			System.out.println("No data available to cancel");
		}
		
	}
	
	@Test
	public void beforeLeaveCount() throws IOException {
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		List<String> allUserPendingActionTextAfter=userPenLeavHoliReq.validatePendingLeave1(driver);
	
	}
	
	@Test(priority=3)
	public void userLeaveCancellation() {
		try {
		if (lb.getTableExist()) {
			List<WebElement> clickOnLeaveCance=lb.getClickOnCancel();
			clickOnLeaveCance.get(0).click();
			driver.switchTo().alert().getText();
			Assert.assertEquals(driver.switchTo().alert().getText(), "Are you sure to cancel your leave?", "alert message not matching");
			//driver.switchTo().alert().accept();
			driver.switchTo().alert().dismiss();
		}
		}catch(Exception e) {
			
		}
	}
	
	

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}

