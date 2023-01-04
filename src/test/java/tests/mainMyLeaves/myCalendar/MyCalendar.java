package tests.mainMyLeaves.myCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.myLeavesObjects.myCalendarObjects.MyCalendarObject;
import tests.LoginTest.LoginPage;

public class MyCalendar {

	public WebDriver driver;
	public String calendarDate;
	
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validatelogin();
	}
	
	@Test
	public void validateCalendar() throws InterruptedException, IOException {
		
		MyCalendarObject mc=new MyCalendarObject(driver);
		Thread.sleep(2000);
		List<WebElement> workingDateList=mc.getWorkingDateList();
		List<WebElement> attendanceStatusList=mc.getAttendanceStatus();
		Thread.sleep(2000);
		System.out.println("before calendar click");
		
		Thread.sleep(1000);
		//System.out.println("After calendar click");
		
		
		List<String> homePageMonthList = new ArrayList<>();
		List<String> homePageDateList = new ArrayList<>();
		for(WebElement workingDate:workingDateList) {
			System.out.println("home page working dates prints :"+ workingDate);
			String date=workingDate.getText().split(", ")[1].split(" ")[0];			 //date only print "20"
			String monthAndYear=workingDate.getText().split(", ")[1].split(" ")[1];  //Month and year will print
			homePageMonthList.add(monthAndYear);
			homePageDateList.add(date);
			System.out.println("Homepage Month splitted :"+monthAndYear);   //Nov
			System.out.println("Homepage Date :"+date );
		}
		System.out.println("After calendar click");
		List<String> attendanceStatusListText = new ArrayList<>();
		for(WebElement attendanceStatus:attendanceStatusList) {
			System.out.println(attendanceStatus.getText());
			attendanceStatusListText.add(attendanceStatus.getText());
		}
		
		System.out.println("All working dates size :"+workingDateList.size());
		System.out.println("All attendance status size :"+attendanceStatusList.size());
//click on my calender
		mc.getClickOnMyCalendar();
//get month calender
		
//================ vick=>need to change "String monthCalender = mc.getMonthCalender();"==========================
		//String monthCalender = mc.getMonthCalender().split("em")[0];
		String monthCalender = mc.getMonthCalender();
	
//collect date and status from Mycalender
		List<WebElement> dateList=mc.getCalendarDateList();
		List<String> calendarDateList = new ArrayList<>();
		for(WebElement datelist:dateList) {
			String dateText=datelist.getText();
			calendarDateList.add(dateText);
		}
		System.out.println("Calendar array date list data :"+calendarDateList);
		
		List<WebElement>statusTextList=mc.getCalendarStatusList();
		List<String> calendarSatusList = new ArrayList<>();
		for(WebElement statuslist:statusTextList) {
			String statusText=statuslist.getText();
			calendarSatusList.add(statusText);
		}
		System.out.println("Calendar array status list data :"+calendarSatusList);

		System.out.println("HomePageMonthList : "+homePageMonthList.get(0));
		System.out.println("month calender : "+monthCalender);
		System.out.println("HomepageDateList : "+homePageDateList.size()); //size 17 **after sort print 01 to 17
		System.out.println("calendarDateList : "+calendarDateList.size()); //size 30  **print 1 to 17
		System.out.println("attendanceStatusListText : "+attendanceStatusListText.size()); //size 17 
		System.out.println("calendarSatusList : "+calendarSatusList.size()); //size 29
		
		
		//===========Vick=>need to change while(!monthCalender.contains(homePageMonthList.get(0))) ==================================
		//while(!homePageMonthList.get(0).contentEquals(monthCalender))  
		while(!monthCalender.contains(homePageMonthList.get(0)))
		{
			mc.getPrevMonthclick();
		}
		Collections.sort(homePageDateList);
		Collections.reverse(attendanceStatusListText);
		
		for(int i=0;i<homePageDateList.size();i++)
		{
			for(int j=i;j<calendarDateList.size();j++)
			{
				if(homePageDateList.get(i).contains(calendarDateList.get(j)))
				{
					System.out.println("both hp date and calender date matched : "+homePageDateList.get(i)+",calender date : "+calendarDateList.get(j));
					for(int k=i;k<attendanceStatusListText.size();k--)
					{
						System.out.println("attendance status : "+attendanceStatusListText.get(k));
						String text = attendanceStatusListText.get(k);
						if(text.equals("UA"))
						{
							text = "ABSENT";
						}
						for(int l=i;l<calendarSatusList.size();l++)
						{
							System.out.println("Calender status : "+calendarSatusList.get(l));
							if(calendarSatusList.get(l).contains(text))
							{
								System.out.println("attendance status list and calender status list matched");
								Assert.assertTrue(true);
							}
							else {
								System.out.println("attendance status list and calender status list not matched");
								Assert.assertTrue(false);
							}
							
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

