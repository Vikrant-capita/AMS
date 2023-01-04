package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.MyLeaveObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class MyLeave {
	
	public WebDriver driver;
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validatelogin();
	}
	
		
	
	//@SuppressWarnings("deprecation")
	@Test
	public void validateMyLeave() throws InterruptedException, IOException {
			
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		
	
		MyLeaveObject leaveplan=new MyLeaveObject(driver);
		leaveplan.getMyLeave();
		String empName=leaveplan.getEmpName();
		
		UserManagerDetailsValidation userMgr=new UserManagerDetailsValidation(driver);
		userMgr.usersManagerDetailsValidation(leaveplan.getEmpName(), leaveplan.getEmpID(), leaveplan.getManagerName(), leaveplan.getManagerID());
		
				
		List<WebElement> leaveTypeOptions=leaveplan.getLeaveType("CASUAL LEAVE (CL)");
		
		System.out.println("List size :"+leaveTypeOptions.size());
		
//		excelDriven exceld=new excelDriven();
//		exceld.getData(String LeaveType, S Options,LeaveType);
		
		for(WebElement list:leaveTypeOptions) {
			System.out.println(list.getText());
			
		}
		
	}
	

}
