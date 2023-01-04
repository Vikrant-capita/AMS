package pageObjects.myLeavesObjects.myReportsObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyReportsObject {
	
	public WebDriver driver;
	public Select s;
	
	public MyReportsObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By clickOnMyReports=By.cssSelector("a[id='TreeMenu1_MenuTreeViewt6']");
	
	By reportName=By.id("ContentPlaceHolderBody_MyReports1_DDLReportList");
	
	//elements for calendor and parameters to be sent except 'clickOnfromCal'
	By clickOnfromCal=By.id("ContentPlaceHolderBody_MyReports1_TXTFromLeaveDate");
	By monthAndYear= By.id("ContentPlaceHolderBody_MyReports1_TXTFromLeaveDate_CalendarExtender_title");
	By clickOnRightArrow=By.id("ContentPlaceHolderBody_MyReports1_TXTFromLeaveDate_CalendarExtender_nextArrow");
	By 	allDateList=By.xpath("//tbody[@id='ContentPlaceHolderBody_MyReports1_TXTFromLeaveDate_CalendarExtender_daysBody']/tr/td/div"); 
	
	//'To' calendar  date selection
	
	By toClickOnCal= By.id("ContentPlaceHolderBody_MyReports1_TXTToLeaveDate");
	By toMonthAndYear=By.id("ContentPlaceHolderBody_MyReports1_TXTToLeaveDate_CalendarExtender_title");
	By toClickOnRightArrow=By.id("ContentPlaceHolderBody_MyReports1_TXTToLeaveDate_CalendarExtender_nextArrow");
	By toALLDateList= By.xpath("//tbody[@id='ContentPlaceHolderBody_MyReports1_TXTToLeaveDate_CalendarExtender_daysBody']/tr/td/div");
	
	By submitbtn=By.id("ContentPlaceHolderBody_MyReports1_ImageButton1");
	
	
	public void getClickOnMyReports() {
		driver.findElement(clickOnMyReports).click();
	}
	
	
	public List<WebElement> getreportName(String rptName) {
		s = new Select(driver.findElement(reportName));
		List<WebElement> reportNameOptions=	s.getOptions();
		s.selectByVisibleText(rptName);
		return reportNameOptions;
	}
	
	public void getClickOnfromCal() {
		driver.findElement(clickOnfromCal).click();
	}
	
	public WebElement getFromMonthAndYear() {
		return driver.findElement(monthAndYear);
	}

	public WebElement getClickOnRightArrow() {
		return driver.findElement(clickOnRightArrow);
	}
	
	public List<WebElement> getAllDateList() {
		List<WebElement> alldatesList=driver.findElements(allDateList);
		return alldatesList;
	}
	
	public void getToClickOnCal() {
		driver.findElement(toClickOnCal).click();
	}
	
	public WebElement getToMonthAndYear() {
		return driver.findElement(toMonthAndYear);
	}
	
	public WebElement getToClickOnRightArrow() {
		return driver.findElement(toClickOnRightArrow);
	}
	
	public List<WebElement> getToALLDateList() {
		List<WebElement> alldatesList=driver.findElements(toALLDateList);
		return alldatesList;
	}
	
	public void getSubmitBTN() {
		driver.findElement(submitbtn).click();;
	}
	
}
