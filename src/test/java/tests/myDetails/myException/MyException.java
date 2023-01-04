package tests.myDetails.myException;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myDetailsObjects.myExceptionObject.MyExceptionsListObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveCancelObject;
import tests.LoginTest.LoginPage;
import utils.Synchronization;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class MyException {
	public WebDriver driver;
	public MyExceptionsListObject myException;
	public List<WebElement> workingDatelist1;
	public String workingDate;
	public List<String> homePageMonthList;
	public List<String> homePageDateList;
	public Synchronization sync;
	public WebDriverWait wait;
	public String absentCount;
	public boolean flag;
	
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test(priority=0)
	public void validateMyExceptionPage() throws InterruptedException, IOException   {
				
		//validate my exception list option and My exception
		myException=new MyExceptionsListObject(driver);
		workingDatelist1=myException.getHpWorkingDate();
		homePageDateStatus();
		Thread.sleep(1000);
		myException.getMyExceptionList();
		String myexceptionText=myException.getClickOnMyExceptionListOpt();
		String myExceptionHistoryText=myException.getClickOnMyExceptionHistoryOpt();
		
		System.out.println(myexceptionText);
		System.out.println(myExceptionHistoryText);
		
		Assert.assertEquals(myexceptionText, "My Exceptions list");
		Assert.assertEquals(myExceptionHistoryText, "My Exceptions History");
	}
	
	@Test(priority=1)
	public void validateMyExceptionList() throws IOException, InterruptedException {
		
		Thread.sleep(1000);
		myException.getClickOnMyExceptionListOpt();		
		
//		UserManagerDetailsValidation usermangr=new UserManagerDetailsValidation(driver);
//		usermangr.usersManagerDetailsValidation(myException);
		
		String empName=myException.getEmpName();
		
		HomePageObject hp=new HomePageObject(driver);
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
		
		String empID=myException.getEmpID();
		String managerName=myException.getManagerName();
		String managerID=myException.getManagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
		
	}
	
	
	@Test(priority=2)
	public void validateIconCount() {
		absentCount=myException.getAbsentCount();
		String presentCount=myException.getPresentCount();
		String shortAttendance=myException.getShortAttendanceCount();
		
		List<String> workingDateList=new ArrayList<String>();
		List<WebElement> workingDateList1=myException.getWorkingDateList();
		for (WebElement workingDates:workingDateList1) {
			String workingDateText=workingDates.getText();
			workingDateList.add(workingDateText);
		}
		System.out.println("Working Date list size :"+workingDateList1.size());
		System.out.println("Working date list array size :"+workingDateList.size());
		System.out.println("Working date list array  :"+workingDateList);
		
		System.out.println("Absent Text :"+absentCount);
		
		Assert.assertEquals(absentCount, Integer.toString(workingDateList.size()));
		
	}
	
	
	@Test(priority=3)
	public void validateException() throws InterruptedException {
		if(Integer.parseInt(absentCount)>0) {
		WebElement workingDate1=myException.getWorkingDate();
		workingDate=workingDate1.getText();
		System.out.println("inside validate exception :"+workingDate);
		myException.getType("Work From Home(WFH)");
		Thread.sleep(4000);
		/*
		wait = new WebDriverWait (driver, Duration.ofSeconds(30));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0")));
   		*/
//		Synchronization sync=new Synchronization(driver);
//		sync.visibilityOfElement();
				
		myException.getCategory("--NA--");
		myException.getReason("--NA--");
		myException.getRemark("Updated by selenium");
		myException.getMarkBtn();
		//=================to be add==wait till element to be disappears from page======================================
			
	//	sync.invisibilityOfEleLocated();
			flag=true;
		}
		else {
			flag=false;
		}
	}
		
	
	
	
	@Test(priority=4)
	public void validateHPUAStatus() throws InterruptedException {
		if (flag==true) {
		System.out.println("HP month :"+homePageMonthList);
		System.out.println("HP dates :"+homePageDateList);
		System.out.println("HP month size :"+homePageMonthList.size());
		System.out.println("HP dates size :"+homePageDateList.size());
		System.out.println("working date :"+workingDate);
		String[] workingDatelist=workingDate.split("-");
		System.out.println(workingDatelist[0]);
		System.out.println(workingDatelist[1]);
		myException.getClickOnCAPITAAMS();
		Thread.sleep(4000);
		List<WebElement> HPStatusList=myException.getHPUAStatus();
		
		for(int i=0; i<=homePageMonthList.size()-1;i++) {
			for ( int j=i; j<=homePageDateList.size()-1;j++) {
				if(homePageMonthList.get(i).contains(workingDatelist[1]) && homePageDateList.get(j).contains(workingDatelist[0])) {
					for(int k=i;k<=HPStatusList.size()-1;k++) {
						System.out.println("green UA validation");
						String styleAtt=HPStatusList.get(i).getAttribute("style");
						Assert.assertTrue(HPStatusList.get(i).getAttribute("style").contains("DarkGreen"));
						
						
					}break;
				}
			}break;
		}
		
		}
		
		
	}
	
	
	
	
	public void homePageDateStatus() {
		
	    homePageMonthList = new ArrayList<>();
		homePageDateList = new ArrayList<>();
		//List<WebElement> workingDatelist1=myException.getHpWorkingDate();
		for(WebElement workingDateText:workingDatelist1) {
			String dateText=workingDateText.getText().split(", ")[1].split(" ")[0];			 //date only print "20"
			String monthAndYearText=workingDateText.getText().split(", ")[1].split(" ")[1];  //Month and year will print
			homePageMonthList.add(monthAndYearText);
			homePageDateList.add(dateText);
			System.out.println("Month splitted :"+monthAndYearText);
			System.out.println("date :"+dateText);
			}
		System.out.println("Working Date :"+workingDate);
				
		
	}
	
	
	@AfterClass(enabled=false)
	public void tearDown() {
		driver.quit();
	}
}
