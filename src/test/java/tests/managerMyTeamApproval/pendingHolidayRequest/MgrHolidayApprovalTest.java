package tests.managerMyTeamApproval.pendingHolidayRequest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestNGException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.managerMyTeamApprovalObjects.pendingHolidayReqObjects.PendingHolidayReqObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;

public class MgrHolidayApprovalTest extends BaseTest {

	public WebDriver driver;
	public LoginPage lp;
	public int limit=1;
	
	
//	public MgrHolidayApprovalTest(WebDriver driver) {
//		this.driver=driver;
//	}
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		driver=lp.validateManagerLogin();
		driver=lp.driver;
	}
	
	@Test
	public void validateholidayReq() throws InterruptedException {
		validateholidayReq1(driver);
	}
	
	public int validateholidayReq1(WebDriver driver) throws InterruptedException {
		PendingHolidayReqObject holiReq=new PendingHolidayReqObject(driver);
		Thread.sleep(2000);
		try{
			String holidayCount=holiReq.getClickOnHoliday().getText();
			System.out.println("Holiday Count before approve/reject :"+holidayCount);
			int holidayCountBefore=Integer.parseInt(holidayCount.substring(9, holidayCount.length()-1));
			//System.out.println("holiday count Before accept/reject:"+holidayCountBefore);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)");
			holiReq.getClickOnPendingHoliReq();
			//holiReq.getClickOnHoliday().click();
		
		//if(holiReq.getHolidayReqTable().isDisplayed())
			
			List<WebElement> holiReqTableList=holiReq.getHolidayTableList();
			System.out.println("holiday count table list :"+ holiReqTableList.size());
			List<WebElement> userNameList=holiReq.getUserNameList();
			
			//Username List= name conatains "Vikrant bingi"
			if(userNameList.size()>3)
			{
				limit=userNameList.size()-2;
			}
		
			int n=0;
			for(int i=0; i<userNameList.size();i++) {
				if(n<limit)
				{
					holiReq.getClickoncheckBox();
					holiReq.getClickOnReject();
					String alertText=driver.switchTo().alert().getText();
					System.out.println("Aler pop text :"+driver.switchTo().alert().getText());
					Assert.assertEquals(alertText, "Are you sure to Reject?" ,"Alert text not matching");
					
					driver.switchTo().alert().accept();
					n++;
				}
				
			}
			
			holiReq.getClickOnCapitaAMS();
			Thread.sleep(1000);
			//String holidayCountAfterText=holiReq.getClickOnHoliday().getText();
			
			if(holidayCountBefore>1) {
			String holidayCountAfterText=holiReq.getClickOnHoliday().getText();
			int holidayCountAfter=Integer.parseInt(holidayCountAfterText.substring(9, holidayCountAfterText.length()-1));
			System.out.println("Holiday Count After Accept/Reject :"+holidayCountAfter);
			Assert.assertEquals(holidayCountBefore, holidayCountAfter+1);
			}
//			else {
//				Assert.assertFalse(holiReq.getClickOnHoliday().isDisplayed());      //this will not work when before holiday count is 1
//			}
			//return limit;
		}
		catch(NoSuchElementException exception) {
			System.out.println("No Pending holiday available");
		}
		return limit;
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
