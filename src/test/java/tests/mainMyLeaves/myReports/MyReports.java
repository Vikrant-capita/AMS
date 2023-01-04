package tests.mainMyLeaves.myReports;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.myLeavesObjects.myReportsObject.MyReportsObject;
import tests.LoginTest.LoginPage;
import utils.CalenderHandle;

public class MyReports {
	
	public WebDriver driver;
	public String[] monthAndYearText;
	public WebElement clickOnRightArrow;
	public List<WebElement> AllDateList;
	
	
	@Test
	public void validateMyReports() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		MyReportsObject mr=new MyReportsObject(driver);
		Thread.sleep(2000);
		mr.getClickOnMyReports();
		Thread.sleep(2000);
		mr.getreportName("My Attendance");
		
		//From calendar activity
		mr.getClickOnfromCal();
		WebElement fromMonthYearEle=mr.getFromMonthAndYear();
		monthAndYearText=mr.getFromMonthAndYear().getText().split(", ");
		clickOnRightArrow=mr.getClickOnRightArrow();
		AllDateList=mr.getAllDateList();
		CalenderHandle ch=new CalenderHandle();
		ch.getCalendor("December", "2022" , "11", monthAndYearText, clickOnRightArrow, AllDateList, fromMonthYearEle);
		
		//To calendar activity
		mr.getToClickOnCal();
		WebElement toMonthYearEle=mr.getToMonthAndYear();
		monthAndYearText =mr.getToMonthAndYear().getText().split(", ");
		clickOnRightArrow=mr.getToClickOnRightArrow();
		AllDateList=mr.getToALLDateList();
		ch.getCalendor("December", "2022", "12", monthAndYearText, clickOnRightArrow, AllDateList,toMonthYearEle);
		
		mr.getSubmitBTN();
	
		
		
	}

}
