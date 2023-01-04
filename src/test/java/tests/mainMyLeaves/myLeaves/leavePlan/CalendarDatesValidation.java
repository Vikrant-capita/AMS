package tests.mainMyLeaves.myLeaves.leavePlan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//import junit.framework.Assert;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import resources.BaseTest;





public class CalendarDatesValidation extends BaseTest{
	int CurrentYear;
	 int CurrentMonth;
	 int CurrentDay;
	 public leavePlanObject ml;
	 public WebDriver driver;
	 public String toYearProp;
	public  String toMonthProp;
	String leaveTypeProp;
	
	public CalendarDatesValidation(WebDriver driver) {
		this.driver=driver;
	}
	
	   	
	@SuppressWarnings("deprecation")
	public void calendarErrorValidation(String fromMonth1, String fromYear1, String fromDate1, String toMonth1, String toYear1, String toDate1,String scheduleTimeText) throws InterruptedException, IOException {
		
		int fromMonth=monthStringFormatConvert(fromMonth1);
		int fromYear=Integer.parseInt(fromYear1);
		int fromDate=Integer.parseInt(fromDate1);
		int toMonth=monthStringFormatConvert(toMonth1);
		int toYear=Integer.parseInt(toYear1);
		int toDate=Integer.parseInt(toDate1);
		
		System.out.println("Hashmap return value :"+fromMonth);
		System.out.println("Hashmap return value :"+toMonth);
		
		SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth=new SimpleDateFormat("MM");
		SimpleDateFormat sdfDate=new SimpleDateFormat("dd");
		Date d=new Date();
		CurrentYear=Integer.parseInt(sdfYear.format(d));
		CurrentMonth=Integer.parseInt(sdfMonth.format(d));
	    CurrentDay=Integer.parseInt(sdfDate.format(d));
				
//		Calendar cal = Calendar.getInstance();
//        CurrentYear = cal.get(Calendar.YEAR);
//        CurrentMonth = cal.get(Calendar.MONTH);
//        CurrentDay = cal.get(Calendar.DATE);
        
        String expectedDate=toMonth1.concat(" "+toYear1);
             
        ml=new leavePlanObject(driver);
       
       LeavePlan lp=new LeavePlan();
//        String scheduleTimeText=lp.scheduleTimeText;
        //String scheduleTimeText=lp.scheduleTimeText;
        System.out.println("schedule time text outside if in cale date val: "+ scheduleTimeText);
        System.out.println("inside calendar error validation");
	     
        
        String[] schTimeArrayList=scheduleTimeText.split("till ")[1].split("-");
		int schMonth=monthStringFormatConvert1(schTimeArrayList[1]);
		System.out.println("Schedule month :"+schMonth);
		System.out.println("Schedule month :"+schMonth);
		int schDate = Integer.parseInt(scheduleTimeText.split("till ")[1].split("-")[0]);
		int schYear = Integer.parseInt(scheduleTimeText.split("till ")[1].split("-")[2]);
        
        
        //1. calendar date is greater than current date and less than scheduled date
		//actual data= current fromyear=2022 from month=November  fromDate=29
		if(CurrentYear<=fromYear &&  CurrentYear<=toYear  && schYear>=fromYear  && schYear>=toYear) {
			if(CurrentMonth<=fromMonth && CurrentMonth<=toMonth && schMonth>=fromMonth && schMonth>=toMonth) {
				if(CurrentDay<fromDate && CurrentDay<toDate   && schDate>fromDate && schDate>toDate) {
					condition1();
					System.out.println("Condition 1 :user entered future single date");
				}
				
			}
		}
		
				
		//2. 'From Date' and 'To date' is greater than Schedule and current date 

		System.out.println("Schedule date :"+schDate);
		System.out.println("Schedule year :"+schYear);
		if(CurrentYear<fromYear &&  CurrentYear<toYear  && schYear<fromYear  && schYear<toYear){
			condition2();
			System.out.println("Year match in condition 2");
		}
		else if(CurrentYear==fromYear &&  CurrentYear==toYear  && schYear==fromYear  && schYear==toYear ) {
			if( CurrentMonth<=fromMonth && CurrentMonth<=toMonth && schMonth<=fromMonth && schMonth<=toMonth )	{
				if(CurrentDay<=fromDate && CurrentDay<=toDate   && schDate<=fromDate && schDate<=toDate) {
					condition2();
					System.out.println("date match in condition 2");
				}
			}
		}
		
		
		//3.From Date and To Date are less than today's date(Past Date)
		if(CurrentYear>=fromYear &&  CurrentYear>=toYear  ){
			System.out.println("condition 3: year matched");
			System.out.println("current month :"+CurrentMonth  +"  "+fromMonth +"   "+toMonth);
			System.out.println("current Date :"+CurrentDay  +"  "+fromDate +"   "+toDate);
			if(CurrentMonth>=fromMonth && CurrentMonth>=toMonth ) {
				System.out.println("condition 3: month matched");
				if(CurrentDay>=fromDate && CurrentDay>=toDate ) {
					condition3();
					System.out.println("Condition 3 : Please select from date greater than todays date.");
				}
			}
		}
		
		//4. 'from date' is lesser than current and  'To date' is greater than current date
		if(CurrentYear>=fromYear &&  CurrentYear<=toYear  ){
			System.out.println("condition 4: year matched");
			System.out.println("current month :"+CurrentMonth  +"  "+fromMonth +"   "+toMonth);
			System.out.println("current Date :"+CurrentDay  +"  "+fromDate +"   "+toDate);
			if(CurrentMonth>=fromMonth && CurrentMonth<=toMonth ) {
				System.out.println("condition 3: month matched");
				if(CurrentDay>=fromDate && CurrentDay<toDate ) {
					condition4();
					System.out.println("Condition 4 : Please select from date greater than todays date.");
				}
			}
		}
		
		/*
		//5. 'from date' is less and 'to date' is greater than scheduled date

				System.out.println("Schedule date :"+schDate);
				System.out.println("Schedule year :"+schYear);
				if(CurrentYear<fromYear &&  CurrentYear<toYear  && schYear>fromYear  && schYear<toYear){
					condition5();
					System.out.println("Year match in condition 5");
				}
				else if(CurrentYear==fromYear &&  CurrentYear==toYear  && schYear==fromYear  && schYear==toYear ) {
					if( CurrentMonth<=fromMonth && CurrentMonth<=toMonth && schMonth>=fromMonth && schMonth<=toMonth )	{
						if(CurrentDay<=fromDate && CurrentDay<=toDate   && schDate>=fromDate && schDate<=toDate) {
							condition5();
							System.out.println("date match in condition 5");
						}
					}
				}*/
				
		//6. 'from date' is less than schedule date and 'to date' is greater than scheduled date

				System.out.println("Schedule date :"+schDate);
				System.out.println("Schedule year :"+schYear);
				if(schYear>=fromYear  && schYear<=toYear){
					if(schMonth>=fromMonth && schMonth<=toMonth) {
						if(schDate>fromDate && schDate<=toDate) {
							condition6();
							System.out.println("date match in condition 6");
						}
						
					}
				}
				
				
		//7. Maximum 15 days for WFH can be applied
				//String fromMonth1, String fromYear1, String fromDate1, String toMonth1, String toYear1, String toDate1
				String join=fromDate1.concat("-"+fromMonth1).concat("-"+fromYear1);
				String leave=toDate1.concat("-"+toMonth1).concat("-"+toYear1);
				
				System.out.println("Join Date : "+join  +" leave date : "+leave);
				if(leaveTypeProp.equals("Work From Home(WFH)")&& fromMonth==toMonth) {
					System.out.println("abcde");
				}





		
//		//6. 'To Date and 'From Date' is greater than todays date and scheduled date
//				if(schYear<=fromYear && CurrentYear<=fromYear  && schYear<=toYear && CurrentYear<=toYear &&  CurrentMonth<fromMonth && schMonth<fromMonth && CurrentDay<toDate && schMonth<fromDate) {
//					//CurrentMonth<fromMonth && CurrentMonth<toMonth  && CurrentDay<=fromDate && CurrentDay<=toDate
//					//String submitmsg=ml.getSubmitMessage();
//					//Thread.sleep(2000);
//					System.out.println("Condition 6 : 'From' date and 'To' date are greater than Scheduled date and current date :");
//					
////					lp.validateCalendor();
////					lp.validateStatusOfSubmittedRequest();
//					
//	}
	
	}
	
	
	
	


	//================perform action like select leave type, category, remark==================================
	public void getLeaveTypeAndCat() throws InterruptedException, IOException {
		
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Properties prop=getProperties();
		leaveTypeProp=prop.getProperty("leaveType");
		String categoryProp=prop.getProperty("category");
		String remarkProp=prop.getProperty("leaveRemark");
		toMonthProp=prop.getProperty("toMonth");
		toYearProp=prop.getProperty("toYear");
		
		ml=new leavePlanObject(driver);
		
		System.out.println(leaveTypeProp);
		System.out.println("to month prop :"+toMonthProp);
		
		ml.getLeaveType(leaveTypeProp);
		Thread.sleep(6000);
		ml.getCategory(categoryProp);
		System.out.println("Category selected");
		ml.getRemark(remarkProp);
		ml.getClickOnSubmitBtn();
		String monthDisplayed=ml.getMonthDisplay();
		
     	String expectedDate=toMonthProp.concat(" "+toYearProp);
	}
	
	
	public void condition1() throws InterruptedException {
		System.out.println("Condition 1 : 'From' and 'To' date are greater than 'current date' and less than 'scheduled date'");
		Thread.sleep(2000);
		String submitmsg=ml.getSubmitMessage();
		System.out.println("submitted msg :"+submitmsg);
		
		Assert.assertEquals(submitmsg, "Saved");
	}
	
	
	public void condition2() throws InterruptedException {
		System.out.println("Condition 2: No Schedule updated. Ensure schedule is updated before applying leaves.");
		Thread.sleep(2000);
		String submitmsg=ml.getSubmitMessage();
		Assert.assertEquals(submitmsg, "No Schedule updated. Ensure schedule is updated before applying leaves.");
	}

	public void condition3() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Condition 3 : Please select from date greater than todays date.");
		String submitmsg=ml.getSubmitMessage();
		Assert.assertEquals(submitmsg,"Please select from date greater than todays date.");	
	}
	
	public void condition4() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Condition 4 : Please select from date greater than todays date.");
		String submitmsg=ml.getSubmitMessage();
		Assert.assertEquals(submitmsg,"Please select from date greater than todays date.");	
	}
	public void condition5() throws InterruptedException {
		System.out.println("Condition 5: No Schedule updated. Ensure schedule is updated before applying leaves.");
		Thread.sleep(2000);
		String submitmsg=ml.getSubmitMessage();
		Assert.assertEquals(submitmsg, "No Schedule updated. Ensure schedule is updated before applying leaves.");
	}
	
	public void condition6() throws InterruptedException {
		System.out.println("Condition 6: No Schedule updated. Ensure schedule is updated before applying leaves.");
		Thread.sleep(2000);
		String submitmsg=ml.getSubmitMessage();
		Assert.assertEquals(submitmsg, "No Schedule updated. Ensure schedule is updated before applying leaves.");
	
	}
	
	//===== convert String month to int==============================
		public int monthStringFormatConvert(String m) {
			
			HashMap<String,Integer> hm = new HashMap<String,Integer>();
			hm.put("January", 1);
			hm.put("February", 2);
			hm.put("March", 3);
			hm.put("April", 4);
			hm.put("May", 5);
			hm.put("June", 6);
			hm.put("July", 7);
			hm.put("August", 8);
			hm.put("September", 9);
			hm.put("October", 10);
			hm.put("November", 11);
			hm.put("December", 12);
			
			int monthInt = hm.get(m);
			System.out.println("hash map cal month in int. :"+monthInt);
			
			for(int i=0; i<=hm.size()-1 ;i++) {
				if(!hm.containsKey(m)) {
					System.out.println("Invalid month");
				}
			}
			return monthInt;
	}
		
public int monthStringFormatConvert1(String m) {
			
			HashMap<String,Integer> hm = new HashMap<String,Integer>();
			hm.put("Jan", 1);
			hm.put("Feb", 2);
			hm.put("March", 3);
			hm.put("April", 4);
			hm.put("May", 5);
			hm.put("June", 6);
			hm.put("July", 7);
			hm.put("Aug", 8);
			hm.put("Sept", 9);
			hm.put("Oct", 10);
			hm.put("Nov", 11);
			hm.put("Dec", 12);
			
			int monthInt = hm.get(m);
			System.out.println("hash map cal month in int. :"+monthInt);
			
			for(int i=0; i<=hm.size()-1 ;i++) {
				if(!hm.containsKey(m)) {
					System.out.println("Invalid month");
				}
			}
			return monthInt;
	}
		
		
		
	
	
	
}
