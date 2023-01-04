package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JavaDatesAndCalendar {
	public static List<String> getDaysBetweenDates(String str_date, String end_date) throws ParseException
	{
		List<Date> dates = new ArrayList<Date>();
		List<String> datesString = new ArrayList<String>();
		DateFormat formatter ; 
		System.out.println(" inside method 'getDaysBetweenDates' from date :"+str_date+"\t to date :"+end_date);
		formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date  startDate = (Date)formatter.parse(str_date); 
		Date  endDate = (Date)formatter.parse(end_date);
		long interval = 24*1000 * 60 * 60; // 1 hour in millis
		long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		long curTime = startDate.getTime();
		while (curTime <= endTime) {
		    dates.add(new Date(curTime));
		    curTime += interval;
		}
		for(int i=0;i<dates.size();i++){
		    Date lDate =(Date)dates.get(i);
		    String ds = formatter.format(lDate);    
		    System.out.println(" Date is ..." + ds);
		    datesString.add(ds);
		}
		System.out.println(dates.size());
		return datesString;
	}
}
