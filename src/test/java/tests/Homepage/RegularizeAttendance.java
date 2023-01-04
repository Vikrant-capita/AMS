package tests.Homepage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import pageObjects.managerMyTeamApprovalObjects.pendingExceptionObjects.PendingExceptionObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;
import tests.managerTest.myTeam.teamExceptionsList.MyTeamExceptionListTest;

public class RegularizeAttendance extends BaseTest {
	public WebDriver driver;
	public HomePageObject hp;
	public LoginPage lp;
	String submitText;
	public String myUserExcpCount;
	public List<WebElement> workingDateList;
	public String userName;
	public int UAListNumber=0;
	public String leaveTypeName;
	public List<WebElement> UAList;
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}	
	
	@Test(priority=1)
	public void activeAttendance() throws InterruptedException, IOException {
		
		//System.out.println("Attendance driver : "+driver);
		hp=new HomePageObject(driver);
		userName = hp.getUserNameText1().split("e ")[1];
		Properties prop=getProperties();
		String leaveTypeProp=prop.getProperty("leaveType");
		String categoryProp=prop.getProperty("category");
		String reasonProp=prop.getProperty("reason");
		String remarkProp=prop.getProperty("leaveRemark");
		String leaveTypeForSSProp=prop.getProperty("leaveTypeForSS");
		//to validate exception count before and after manager approval/rejection
		myUserExcpCount=hp.getMyExcpText().substring(1, 2);
		System.out.println("My User Exception count :"+myUserExcpCount);
		UAList=hp.getualist();
		System.out.println("UA list size :"+UAList.size());
		
		workingDateList=hp.getRedUAWorkingList();
		Collections.reverse(UAList);
		Collections.reverse(workingDateList);
		if(UAList.size()!=0) {
			try {		
				for(int i=0; i<UAList.size();i++) {
					//UAList.get(i).getAttribute("style").contains("Red") && 
					if(workingDateList.get(i).getText().split(" ,")[0].contains("Saturday") || workingDateList.get(i).getText().split(" ,")[0].contains("Sunday") ) {
						System.out.println("working date text in with SS :"+workingDateList.get(i).getText().split(" ,")[0]);
						System.out.println("working date text :"+workingDateList.get(i).getText());
						UAList.get(i).click();
						UAListNumber=i;
						System.out.println("UA list number in Sat Sun :"+UAListNumber);
						Thread.sleep(2000);
						 
						hp.getleaveType(leaveTypeForSSProp);
						Thread.sleep(3000);
						hp.getCategory(categoryProp);
						hp.getReason(categoryProp);
						hp.getRemark(remarkProp);
						hp.getSubmitBtn();
						driver.switchTo().alert().accept();
						Thread.sleep(8000);
						submitText=hp.getSubmitText();
						System.out.println("Submited Text outside :"+submitText);
									
					}
					else {
						System.out.println("working date text in without SS :"+workingDateList.get(i).getText().split(" ,")[0]);
						UAList.get(i).click();
						UAListNumber=i;
						System.out.println("UA list number  :"+UAListNumber);
						Thread.sleep(4000);
						 
						hp.getleaveType(leaveTypeProp);
						Thread.sleep(3000);
						hp.getCategory(categoryProp);
						hp.getReason(reasonProp);
						hp.getRemark(remarkProp);
						hp.getSubmitBtn();
						driver.switchTo().alert().accept();
						Thread.sleep(3000);
						submitText=hp.getSubmitText();
						System.out.println("Submited Text outside :"+submitText);
						Thread.sleep(1000);
						
					}
				}
			}
				catch(StaleElementReferenceException exception) {
					System.out.println("Error - StaleElement error :"+exception.getMessage());
					
				}
		}else {
			System.out.println("No Red UA List available");
		}
		
		
		if(submitText.contains("saved")) {
			System.out.println("UA list number :"+UAListNumber);
			for(int i=UAListNumber;i<=UAList.size()-1;i++) {
				System.out.println("inside for loop for green UA validation");
				String UAColor=UAList.get(i).getAttribute("style");
				Boolean flag=UAList.get(i).getAttribute("style").contains("DarkGreen");
				System.out.println("UA text :"+UAList.get(i).getText());
				Assert.assertTrue(flag);
				
				validateUserLogout();
				validateMgrPendingExceptionFlow();
				validateManagerLogout();
				validateUserAppliedAttendance();
				
			}	
		}
		else {
			//This exception is already handled
			//write code to handle this from manager account
			System.out.println("Submited Text :"+submitText);
			
			MyTeamExceptionListTest teamExpList = new MyTeamExceptionListTest();
			leaveTypeName=teamExpList.validateMyTeamExceptionList(driver, lp,userName);
			validateManagerLogout();
			validateUserAppliedAttendance();
								
		}
	}
	
	//@Test(priority=2)
	public void validateUserLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("User Logout");
	}
	
	//@Test(priority=3)
	public void validateMgrPendingExceptionFlow() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		tests.managerMyTeamApproval.pendingException.MgrPendingExceptionsTest mpe=new tests.managerMyTeamApproval.pendingException.MgrPendingExceptionsTest();
		mpe.clickOnPendingException(driver);
		
		
		mpe.validateManagerException(driver);
	}
	
	//@Test(priority=4)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
		
	//@Test(priority=5)
	public void validateUserAppliedAttendance() throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		System.out.println("UA list number :"+UAListNumber);
		Thread.sleep(2000);
		try
		{
			Boolean flag=UAList.get(UAListNumber).getText().contains(leaveTypeName);
			Assert.assertTrue(flag);
		}
		catch(StaleElementReferenceException e)
		{
			Boolean flag=UAList.get(UAListNumber).getText().contains(leaveTypeName);
			Assert.assertTrue(flag);
		}
		
		
		//Thread.sleep(2000);
	
	}
		
	@Test(enabled=false)
	public void historicalAttendance() throws InterruptedException {
		Thread.sleep(4000);
		hp=new HomePageObject(driver);
		//System.out.println("historical driver : "+driver);
		hp.getHistoryAttendance();
		hp.getYear("2022");
		hp.getMonth("October");
		Thread.sleep(2000);
		hp.getButton();
		List<WebElement> attendanceHistory1= hp.getAttendanceHistory();
		for(WebElement link:attendanceHistory1) {
			String day=link.getText();
			System.out.println("Date :"+day);
		}
	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
}
