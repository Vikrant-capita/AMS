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
import pageObjects.myLeavesObjects.myLeavesobject.leaveBalanceObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class LeaveBalance {

	public WebDriver driver;
	public leaveBalanceObject lb;
	public LoginPage lp;
	String leaveTypeText="CASUAL LEAVE (CL)";
	
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		driver=lp.validatelogin();
	}
	
	
	@Test(priority=1)
	public void validateLeaveBalance() throws InterruptedException, IOException {
		driver = lp.driver;
		
		//to click on maim my leaves button ----------need to remove later--------------
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		//---------------------------------
		
		lb=new leaveBalanceObject(driver);
		lb.getClickOnLeaveBalance();
		
		UserManagerDetailsValidation userMgr=new UserManagerDetailsValidation(driver);
		userMgr.usersManagerDetailsValidation(lb.getEmpName(), lb.getEmpID(), lb.getManagerName(), lb.getManagerID());
		
		/*
		HomePageObject hp=new HomePageObject(driver);
		
		String empName=lb.getEmpName();
		String ab=hp.getUserNameText1().split("e ")[1];
		//username=hp.userNameText;
		System.out.println("usename: "+ab);
		System.out.println(empName);
		
		Assert.assertEquals(empName, ab);
		
		excelDriven excel=new excelDriven();
		ArrayList<String> data=excel.getData(empName, "Username");
		
		String UserID=data.get(0);
		String Password=data.get(1);
		String Username=data.get(2);
		String EMPID=data.get(3);
		String ManagerName=data.get(4);
		String ManagerID=data.get(5);
		
		System.out.println(EMPID);
		
		String empID=lb.getEmpID();
		String managerName=lb.getmanagerName();
		String managerID=lb.getmanagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
		*/
		
	}
		
		@Test(priority=2)
		public void validateAllLeaveTableData() throws InterruptedException {
			lb=new leaveBalanceObject(driver);
			List<WebElement> leaveBalanceOptions=lb.getleaveBalanceOptions("2022");
			for(WebElement list:leaveBalanceOptions) {
				System.out.println("Leave balance option year list :"+list.getText());
			}
			
			Thread.sleep(3000);
			 int empIDColumeIndex=1;
			List<WebElement> empIDTableList=lb.getTableList(empIDColumeIndex);
			System.out.println("size :"+empIDTableList.size());
		
			for(WebElement list1:empIDTableList)
			{
				System.out.println("EMP ID Table List :"+list1.getText());
			
			}
			 int leaveTypeIndex=4;
			List<WebElement> leaveTypeTableList=lb.getTableList(leaveTypeIndex);
			for(WebElement list:leaveTypeTableList) {
				System.out.println("Leave type Table List :"+list.getText());
			}
		
			List<Integer> carryFwdList=new ArrayList<Integer>();
			int carryFWD=5;
			List<WebElement> CarryForwardA=lb.getTableList(carryFWD);
			System.out.println("size :"+CarryForwardA.size());
			for(WebElement list1:CarryForwardA)
			{
				carryFwdList.add(Integer.parseInt(list1.getText()));
			}
			
			List<Integer> accruedEntitlementList=new ArrayList<Integer>();
			int accruedEntitlement=7;
			List<WebElement> accruedEntitlementC=lb.getTableList(accruedEntitlement);
			System.out.println("size :"+accruedEntitlementC.size());
			for(WebElement list1:accruedEntitlementC)
			{
				accruedEntitlementList.add(Integer.parseInt(list1.getText()));
			}
				
			List<Integer> availedList=new ArrayList<Integer>();
			int availed=8;
			List<WebElement> availedD=lb.getTableList(availed);
			System.out.println("size :"+availedD.size());
			for(WebElement list1:availedD)
			{
				availedList.add(Integer.parseInt(list1.getText()));
			}
			
			
			List<Integer> balanceList=new ArrayList<Integer>();
			int balance=10;
			List<WebElement> balanceColumne=lb.getTableList(balance);
			System.out.println("size :"+balanceColumne.size());
			for(WebElement list1:balanceColumne)
			{
				balanceList.add(Integer.parseInt(list1.getText()));		
			}
			
			
			System.out.println("Carry Forward Index :"+carryFwdList);
			System.out.println("Accrued Entitlement Index :"+accruedEntitlementList);
			System.out.println("Availaed index :"+availedList);
			System.out.println("Balance index :"+balanceList);
			
			
			ArrayList<Integer> resultList=new ArrayList();
			for(int i=0; i<=carryFwdList.size()-1;i++) {
				for(int j=i; j<=accruedEntitlementList.size()-1;j++) {
					for(int k=j; k<=availedList.size()-1;k++) {
						int resultindex=(carryFwdList.get(i)+accruedEntitlementList.get(j))-availedList.get(k);
						resultList.add(resultindex);	
						System.out.println("calculated value :"+resultindex);
						break;
					}
					break;
				}
			}
			
			Assert.assertEquals(resultList, balanceList);

		}
		
				
		
		@Test(priority=3)
		public void getBalanaceLeaveData1() throws InterruptedException {
			getBalanaceLeaveData(leaveTypeText, driver);
		}
		
		
		public List<String> getBalanaceLeaveData(String leaveTypeText, WebDriver driver) throws InterruptedException {
			
			lb=new leaveBalanceObject(driver);
			List<WebElement> leaveRowData=lb.getLeaveTypesList(leaveTypeText);		//FIVE FOR FIVE     CASUAL LEAVE (CL)
			System.out.println("leave row data :"+leaveRowData.size());
			List<String> leaveRowDataListText=new ArrayList<String>();
			for(WebElement leaveRowDataList:leaveRowData) {
				System.out.println(leaveRowDataList.getText());
				leaveRowDataListText.add(leaveRowDataList.getText());
			}
			System.out.println("leave Row Data List Text :"+leaveRowDataListText);
			//leaveRowDataListText.get
			return leaveRowDataListText;
		}
		
		
		
}


//----------------------------------Done--------------------------------------
