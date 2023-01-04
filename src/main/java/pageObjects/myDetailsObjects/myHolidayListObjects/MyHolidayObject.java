package pageObjects.myDetailsObjects.myHolidayListObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyHolidayObject {

	public WebDriver driver;
	public Select s ;
	
	public MyHolidayObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By myHolidayList= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt1']");
	
	//Emp and Manager details
	By empName=By.cssSelector("#ContentPlaceHolderBody_LBLEmployeeName");
	By empCode=By.cssSelector("#ContentPlaceHolderBody_LBLEmployeeID");
	By lineManager=By.cssSelector("#ContentPlaceHolderBody_LBLRMName");
	By lmCode=By.cssSelector("#ContentPlaceHolderBody_LBLRMID");
	By year=By.xpath("//*[@id='ContentPlaceHolderBody_DDLHYear']"); 
	
	//Holiday Name
	By yellowHoliday=By.cssSelector("#ContentPlaceHolderBody_lblttt");
	By holidayList=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td");		//array index out of bound
	
	By holidayListWOYellow=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label");
	
	By holidayListWithYello=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td//span/label");
	
	By allHolidayListCheckBox=By.xpath("//input[contains(@name,'ctl00$ContentPlaceHolderBody$CHKLHolidayList$')]");
	By allHolidatNameList=By.cssSelector("label[for*='ContentPlaceHolderBody_CHKLHolidayList_']");
	
	By submitBtn=By.id("ContentPlaceHolderBody_IMGBTNSubmit");
	By submitMsg=By.id("ContentPlaceHolderBody_LBLMSG");
	
	//holiday table validation
	By tableExist=By.cssSelector("#ContentPlaceHolderBody_GridViewHoliday");
	By submittedHolidayNameList=By.xpath("//table[@id='ContentPlaceHolderBody_GridViewHoliday']/tbody/tr/td[2]");
	By submittedHolidayDates=By.xpath("//table[@id='ContentPlaceHolderBody_GridViewHoliday']/tbody/tr/td[3]");
	//By cancelBtn=By.xpath("//table[@id='ContentPlaceHolderBody_GridViewHoliday']/tbody/tr/td[7]");
	By cancelBtn=By.id("ContentPlaceHolderBody_GridViewHoliday_ButDelete_0");
	
	By cancelSubmitMsg=By.id("ContentPlaceHolderBody_LBLMSG");
	
	By clickOnCapitaAMS=By.xpath("//a[contains(text(),'CAPITA AMS')]");
	
	
	//=================================================
	public void getClickOnMyHolidayList() {
		driver.findElement(myHolidayList).click();
	}
	
	public String getEmpName() {
		String employeeName=driver.findElement(empName).getText();
		return employeeName;
	}
	
	public String getEmpID() {
		 return driver.findElement(empCode).getText();
	}
	
	public String getManagerName() {
		String LM=driver.findElement(lineManager).getText();
		return LM;
	}
	
	public String getManagerID() {
		String LMCode=driver.findElement(lmCode).getText();
		return LMCode;
	}
	
	public void getYear(String yr)
	{
		s = new Select(driver.findElement(year));
		s.selectByVisibleText(yr);
	
	}
	
	public String getYellowHoliday() {
		String yelloHoliday=driver.findElement(yellowHoliday).getText();
		return yelloHoliday;
	}
	
	public List<WebElement> getHolidayList() {
		List<WebElement> holidayList1=driver.findElements(holidayList);
		return holidayList1;
	}
	
	public List<WebElement> getholidayListWOYellow() {
		List<WebElement> holidayListWOYellow1=driver.findElements(holidayListWOYellow);
		return holidayListWOYellow1;
	}
	
	public List<WebElement> getholidayListWithYellow() {
		List<WebElement> holidayListWithYello1=driver.findElements(holidayListWithYello);
		return holidayListWithYello1;
	}
	
	public List<WebElement> getAllHolidayCheckBoxList() {
		return driver.findElements(allHolidayListCheckBox);
	}
	public List<WebElement> getAllHolidayNameList() {
		return driver.findElements(allHolidatNameList);
	}
	
	public void getSubmitBtn() {
		driver.findElement(submitBtn).click();
	}
	public String getSubmitMsg() {
		return driver.findElement(submitMsg).getText();
	}
	
	public WebElement getTableExist() {
		return driver.findElement(tableExist);
	}
	public List<String> getSubmittedHolidayNameList() {
		List<WebElement> lists=driver.findElements(submittedHolidayNameList);
		List<String> submitedList = new ArrayList<>();
		for(WebElement list:lists)
		{
			submitedList.add(list.getText().split(" \\[")[0]);
		}
		
		return submitedList;
	}
	
	public List<String> getSubmittedHolidayDates() {
		List<String> submitedDateList = new ArrayList<>();
		for(WebElement list:driver.findElements(submittedHolidayDates)) {
			submitedDateList.add(list.getText());
		}
		return submitedDateList;
	}
	
	public void  getCancelBtn() {
		driver.findElement(cancelBtn).click();;
	}
	
	public String getCancelSubmitMsg() {
		return 	driver.findElement(cancelSubmitMsg).getText();
	}
	
	public void getClickOnCapitaAMS() {
		driver.findElement(clickOnCapitaAMS).click();
	}
	
}
