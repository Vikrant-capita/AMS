package tests.mainMyLeaves.myLeaves.leavePlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;
import utils.CalenderHandle;
import utils.excelDriven.validationLeaveTypeAndCategoryList;

public class ValidationLeaveTypeAndCategoryList extends BaseTest{

	public WebDriver driver;
	public leavePlanObject ml;
	ArrayList<String> leaveTypeDDList;
	List<WebElement> leaveTypeOptions;
	//List<WebElement> catOptionList;
	
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	
	@Test
	public void validationExcelLeaveTypeCategory() throws IOException, InterruptedException {
		
		ml=new leavePlanObject(driver);
		ml.getClickOnMyLeaves();
		calenderDateSelections();
		
		WebElement leaveType=driver.findElement(By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_DDLLeavePlanType"));
		WebElement category=driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_ddlCategories"));
		
		Select s=new Select(leaveType);
		Select s1=new Select(category);
		leaveTypeDDList=new ArrayList<String>();
		List<WebElement> leaveTypeOptions=s.getOptions();
		for(WebElement leavetype:leaveTypeOptions) {
			System.out.println("leave type options : "+leavetype.getText());
			String leaveTypeaArrText=leavetype.getText();
			leaveTypeDDList.add(leavetype.getText());
		}
		//Leave type dropdown list validation with excel
		leaveTypeDDVerification();
		
		//leave type Vs category list validation
		
		ArrayList<String> cataArrList=new ArrayList<String>();
		for(int i=0;i<=leaveTypeOptions.size()-1;i++) {
			Thread.sleep(2000);
			s.selectByIndex(i);
			Thread.sleep(4000);
			List<WebElement> catOptionList=s1.getOptions();
			
			for(WebElement catOpt:catOptionList) {
				String catOptText=catOpt.getText();
				System.out.println("cat opt text inside for :"+catOptText);
				cataArrList.add(catOptText);
			   			
				if(leaveTypeDDList.get(i).contains("CASUAL LEAVE (CL)") || leaveTypeDDList.get(i).contains("LEAVE WITHOUT PAY (LWP)") ||  leaveTypeDDList.get(i).contains("PRIVILEGE LEAVE (PL)")) {
				System.out.println("abc");
				}
				else{
					System.out.println("xyz");
				}
			}
		}
		
	} 
	
	
	public void leaveTypeDDVerification() throws IOException {
		validationLeaveTypeAndCategoryList excel1=new validationLeaveTypeAndCategoryList();
		ArrayList<String> excelDataList=excel1.getData1("LeaveType", "Options");
		
		for(String exceldata:excelDataList) {
			System.out.println("Excel leave type :"+exceldata);
		}
		
		for(int i=1;i<=leaveTypeDDList.size()-1;i++) {
			for(int j=i; j<=excelDataList.size()-1;j++) {
				System.out.println("Leave type DD : "+ leaveTypeDDList.get(i) +" = "+ excelDataList.get(i)+" Excel data list");
				System.out.println("Leave type matched : "+ leaveTypeDDList.get(i).equals(excelDataList.get(i)));
			}
			break;
			
		}
		
		
		//System.out.println("Actual leave type size : "+leaveTypeOptions.size());
		//System.out.println("Excel leave type size : "+excelDataList.size());
		
	}
	
	
	
	//==============='To' and 'From' date selection========================
	public void calenderDateSelections() throws InterruptedException, IOException {
       // ml.getFromDateClick();
		
		String[] monthYearText1=ml.getMonthYear1();
		System.out.println("from month year :"+monthYearText1[0]);
		
		WebElement clickOnRightArrow1=ml.getclickOnRightArrow1();
		
		List<WebElement> allDateList1=ml.getDateList();
		
		Properties prop=getProperties();
		String fromMonth=prop.getProperty("fromMonth");
		String fromYear=prop.getProperty("fromYear");
		String fromDate=prop.getProperty("fromDate");
		
		WebElement monthYearEle1= ml.getMonthYearEle1();
		
		CalenderHandle ch=new CalenderHandle();
		ch.getCalendor(fromMonth, fromYear, fromDate, monthYearText1,clickOnRightArrow1, allDateList1,monthYearEle1);
		
		Thread.sleep(4000);
		//ml.getToDateClick();
		Thread.sleep(2000);
		String[] monthYearText2=ml.getMonthYear2().split(", ");
		System.out.println("to month year :"+monthYearText2[0]);
		WebElement clickOnRightArrow2=ml.getclickOnRightArrow2();
		List<WebElement> allDateList2=ml.getDateList2();
		
		String toMonth=prop.getProperty("toMonth");
		String toYear=prop.getProperty("toYear");
		String toDate=prop.getProperty("toDate");
		
		WebElement monthYearEle2= ml.getMonthYearEle2();
		
		String monthYear=ch.getCalendor(toMonth, toYear, toDate, monthYearText2, clickOnRightArrow2, allDateList2, monthYearEle2);
		System.out.println("  to calendar returned month year :"+leaveTypeDDList);
	}
	
	
	
	
}
