package tests.managerMyTeamApproval.pendingLeaveRequest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//import junit.framework.Assert;
import pageObjects.homePageObjects.managerHomePageObjects;
import pageObjects.managerMyTeamApprovalObjects.pendingLeaveReqObjects.MyTeamLeaveRequestObject;
import tests.LoginTest.LoginPage;

public class MgrMyTeamLeaveRequestTest {

	public WebDriver driver;
	public LoginPage lp;
	boolean leavePendingActionDisplayed;
	public managerHomePageObjects  mgrHP;
	public String pendingActLeavesText;
	
	
	
	
//	public MgrMyTeamLeaveRequestTest(WebDriver driver) {
//		// TODO Auto-generated constructor stub
//		this.driver=driver;
//	}

	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validateManagerLogin();
		driver=lp.driver;
	}
	
	@Test
	public void validateManagerMyPendingAction() {
		validateManagerMyPendingAction1(driver);
	}
	
	public void validateManagerMyPendingAction1(WebDriver driver) {
		
		mgrHP=new managerHomePageObjects(driver);
		try {
			leavePendingActionDisplayed=mgrHP.getLeavesPendingAction().isDisplayed();
			System.out.println(leavePendingActionDisplayed);
			pendingActLeavesText=mgrHP.getLeavesPendingAction().getText();
			System.out.println("pending action leaves text :"+pendingActLeavesText);
			
		}
		catch(NoSuchElementException exception) {
					System.out.println("No leaves pending for approval for Manager");
		}
	
	}
	
	
	@Test
	public void validateMyTeamLeaveRequest() throws InterruptedException {
		validateMyTeamLeaveRequest1(driver);
		
		
	}
	public void validateMyTeamLeaveRequest1(WebDriver driver) throws InterruptedException {
		if(leavePendingActionDisplayed) {
			MyTeamLeaveRequestObject teamLeaveReq=new MyTeamLeaveRequestObject(driver);
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Thread.sleep(2000);
			mgrHP.getLeavesPendingAction().click();
			System.out.println("pending action leaves text :"+pendingActLeavesText);
			//teamLeaveReq.getclickOnPendingLeaveRequest();
			//teamLeaveReq.getClickOnMyTeamLeaveReq();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,100)");
			String empNameText=teamLeaveReq.getClickOnEmpName().getText();
			System.out.println("Emp Name :"+empNameText);
			Thread.sleep(1000);
			teamLeaveReq.getClickOnEmpName().click();
						
			List<WebElement> leaveRequestList= teamLeaveReq.getTeamLeaveRequestTable();
			System.out.println("request list size :"+leaveRequestList.size());
			for(WebElement leaveRequest:leaveRequestList) {
				String leaveRequestText=leaveRequest.getText();
				System.out.println("Leave request text :"+leaveRequestText);
			}
			Thread.sleep(5000);
			teamLeaveReq.getActionDD("Reject");
			teamLeaveReq.getSubmitBtn();
			Thread.sleep(3000);
			String submitMsg=teamLeaveReq.getSubmitMsg();
			System.out.println("Leave submitted text :"+submitMsg);
			Assert.assertEquals(submitMsg, "Saved..");
			
		}
	}
	
	
}
