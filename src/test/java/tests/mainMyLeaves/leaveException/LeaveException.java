package tests.mainMyLeaves.leaveException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.leaveExceptionObject.LeaveExceptionObject;
import tests.LoginTest.LoginPage;
import utils.CalenderHandle;
import utils.excelDriven.excelDriven;

public class LeaveException {

	public WebDriver driver;
	
	@Test
	public void validateleaveException() throws InterruptedException, IOException {
		
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		LeaveExceptionObject le=new LeaveExceptionObject(driver);
		le.getClickOnLeaveException();
		Thread.sleep(2000);
		String empName=le.getEmpName();
		
		HomePageObject hp=new HomePageObject(driver);
		String ExpectedUserNameText=hp.getUserNameText1().split("e ")[1];
		Assert.assertEquals(empName, ExpectedUserNameText);
		System.out.println("actual name :"+empName+  " Expected Name"+ExpectedUserNameText);
		
		excelDriven excel=new excelDriven();
		ArrayList<String> data=excel.getData(empName, "Username");
		
		String UserID=data.get(0);
		String Password=data.get(1);
		String Username=data.get(2);
		String EMPID=data.get(3);
		String ManagerName=data.get(4);
		String ManagerID=data.get(5);
		
		System.out.println(EMPID);
		System.out.println(ManagerName);
		System.out.println(ManagerID);
		
		String empID=le.getEmpID();
		String managerName=le.getManagerName();
		String managerID=le.getManagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
		
		le.getFromDateClick();
		WebElement fromMonthYearEle=le.getFromMonthYear();
		String[] fromMonthYearText=le.getFromMonthYear().getText().split(", ");
		WebElement fromClickOnRightArrow=le.getFromClickOnRightArrow();
		List<WebElement> fromAllDateList=le.getFromAllDateList();
		
		CalenderHandle ch=new CalenderHandle();
		ch.getCalendor("December", "2022", "20", fromMonthYearText,fromClickOnRightArrow, fromAllDateList,fromMonthYearEle);
		Thread.sleep(2000);
		
		le.getToDateClick();
		WebElement toMonthYearEle=le.getToMonthYear();
		String[] toMonthYearText=le.getToMonthYear().getText().split(", ");
		WebElement toClickOnRightArrow =le.getToClickOnRightArrow();
		List<WebElement> toAllDateList=le.getToAllDateList();
		
		ch.getCalendor("December", "2022", "22", toMonthYearText,toClickOnRightArrow, toAllDateList, toMonthYearEle);
		
		List<WebElement> directorList=le.getDirectDropdown("Abhishek Basu");
		Thread.sleep(2000);
		le.getLeaveType("PRIVILEGE LEAVE (PL)");
		Thread.sleep(2000);
		le.getCategory("General");
		le.getRemarkBox();
		//le.getsubmitBTN();
		
	}
	
	
	
	//============================= submitte button to be pressed========================================
	
}
