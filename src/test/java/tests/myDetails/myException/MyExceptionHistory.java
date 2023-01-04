package tests.myDetails.myException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myDetailsObjects.myExceptionObject.MyExceptionHistoryObject;
import pageObjects.myDetailsObjects.myExceptionObject.MyExceptionsListObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class MyExceptionHistory  {
	
	public WebDriver driver;
	public MyException myExcep;
	public MyExceptionHistoryObject myExHistobj;
	public MyExceptionsListObject myException;
	public String absentCount;
	public String workingDate;
	public List<WebElement> workingDateList;
    boolean flag;
    
    
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test(priority=0)
	public void validateMyExcepHist() throws IOException, InterruptedException {
		MyExceptionHistoryObject myExHist=new MyExceptionHistoryObject(driver);
		
		//Here EMP name, ID , Manager name, ID are validating by calling method
		
		myExHist.getClickOnMyExceptions();
		Thread.sleep(2000);
		myExHist.getClickOnMyExcepHist();
		
		UserManagerDetailsValidation usermangr=new UserManagerDetailsValidation(driver);
		usermangr.usersManagerDetailsValidation(myExHist.getEmpName(),myExHist.getEmpID(),myExHist.getManagerName(),myExHist.getManagerID());
		
	}
	
	@Test(priority=1)
	public void validateException() throws InterruptedException 
	{
		myException=new MyExceptionsListObject(driver);
		myException.getMyExceptionList();
		absentCount = myException.getAbsentCount();
		if(Integer.parseInt(absentCount)>0) {
			System.out.println("absent count : "+absentCount);
			WebElement workingDate1=myException.getWorkingDate();
			workingDate=workingDate1.getText();
			workingDateList = myException.getWorkingDateList();
			//System.out.println("inside validate exception :"+workingDate);
			myException.getTypeList("Work From Home(WFH)");
			Thread.sleep(4000);
			/*
			wait = new WebDriverWait (driver, Duration.ofSeconds(30));
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0")));
	   		*/
//			Synchronization sync=new Synchronization(driver);
//			sync.visibilityOfElement();
					
			myException.getCategorylist("--NA--");
			myException.getReasonlist("--NA--");
			myException.getRemarklist("Updated by selenium");
			myException.getAllMarkBtn();
			//=================to be add==wait till element to be disappears from page======================================
				
		//	sync.invisibilityOfEleLocated();
				flag=true;
			}
			else {
				flag=false;
			}
					
			
	}
	
	@Test(priority=2)
	public void validateExceptionHistoryStatus() throws InterruptedException
	{
		Thread.sleep(4000);
		myException=new MyExceptionsListObject(driver);
		myExHistobj = new MyExceptionHistoryObject(driver);
		myExHistobj.getClickOnMyExcepHist();
		List<String> histworkingdate = myExHistobj.getHistoryWorkingDate();
		List<String> histStatusList=myExHistobj.getStatusList();
		
		for(int i=0;i<myException.getWorkingDateList().size();i++)
		{
			for(int j=0;j<histworkingdate.size();j++)
			{
				if(workingDateList.get(i).equals(histworkingdate.get(j)))
				{
					
					
					for(int k=j;k<histStatusList.size();k++)
					{
						if(histStatusList.get(k).contains("Waiting for approval"))
						{
							System.out.println("history date : "+histworkingdate.get(j)+", Working Date : "+workingDateList.get(i));
							System.out.println("history status matched***** : "+histStatusList.get(k));
							for(int l=k;l<myExHistobj.getDeleteButton().size();l++)
							{
								Thread.sleep(5000);
								myExHistobj.getDeleteButton().get(l).click();
								Thread.sleep(5000);
								driver.switchTo().alert().accept();
								Thread.sleep(4000);
								System.out.println("delete msg : "+myExHistobj.getDeleteMessage());
								Assert.assertEquals(myExHistobj.getDeleteMessage(), "Deleted..","delete message verification failed..");
								break;
							}
						
							break;
						}
						
						 
					}
					break;
				}
				
			}
			
		}
		
	
	}
	
	
	
	
}
