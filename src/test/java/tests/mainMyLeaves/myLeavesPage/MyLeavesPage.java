package tests.mainMyLeaves.myLeavesPage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.myLeavesObjects.myLeavesPageObjects.MyLeavesPageObject;
import tests.LoginTest.LoginPage;

public class MyLeavesPage {

	public WebDriver driver;
	
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validatelogin();
	}
	
	@Test
	public void validateMyLeavesPage() throws InterruptedException{
		MyLeavesPageObject myLeavePg=new MyLeavesPageObject(driver);
		myLeavePg.getClickOnMyLeaves();
		Thread.sleep(2000);
		String pageNameText=myLeavePg.getPageName();
		String leaveplanText=myLeavePg.getLeavePlanTab();
		String myLeaveText=myLeavePg.getMyLeaveTab();
		String leaveBalanceText=myLeavePg.getLeaveBalanceTab();
		String leaveCancelText=myLeavePg.getLeaveCancelTab();
		
		
		Assert.assertEquals(pageNameText, "Leave Plan");
		Assert.assertEquals(leaveplanText, "Leave Plan");
		Assert.assertEquals(myLeaveText, "My Leave");
		Assert.assertEquals(leaveBalanceText, "Leave Balance");
		Assert.assertEquals(leaveCancelText, "Leave Cancel");
		
		
		
	}
}
