package pageObjects.myDetailsObjects.myExceptionObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyExceptionsListObject {
	
	public WebDriver driver;
	public Select s ;
	
	public MyExceptionsListObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnMyExceptionListOpt=By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1");
	//text= My Exceptions list
	By clickOnMyExceptionHistoryOpt=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_header']/span[2]/span/span/span");
	//text=  My Exceptions History

	//HomePage elements
	By clickOnMyExceptions= By.xpath("//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt2']");
	By hpUAStatus=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]");
	By hpWorkingDate=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]");
	By clickOnCAPITAAMS=By.xpath("//div[@id='header']/div/h1/a");
	
	//My Exception List
	By empName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMName");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMID");
	
	//ICON count 
	By absentCount=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_lblAbsent");
	By presentCount=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_lblPresent");
	By shortAttendanceCount= By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_lblShourtTime");
	
	//Exceptions List  1. Absent
    By workingDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[1]");
	
	By workingDate=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_LBLWorkingDate_0");
	By type=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLType_0");
	By typeList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[2]/select");
	
	By category=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0");
	By categoryList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[3]/select");
	
	By reason=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLReasonType_0");
	By reasonList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[4]/select");
	
	By duration=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_LBLDuration_0");
	
	By remark=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_TXTRemarks_0");
	By remarkList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[6]/input");
	
	By markBtn=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_ButUpdate_0");
	By markBtnList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[7]/input");
		
	public String getClickOnMyExceptionListOpt() {
		return driver.findElement(clickOnMyExceptionListOpt).getText();
	}
	
	public String getClickOnMyExceptionHistoryOpt() {
		return driver.findElement(clickOnMyExceptionHistoryOpt).getText();
	}
	
	
	public void getMyExceptionList() {
		driver.findElement(clickOnMyExceptions).click();
	}
	
	/*
	public List<WebElement> getHPDatesUA() {
		List<WebElement> hpUADAtes=driver.findElements(UAStatus);
		return hpUADAtes;
	}*/
	
	
	public List<WebElement> getHPUAStatus() {
		return  driver.findElements(hpUAStatus);
	}
	
	public List<WebElement> getHpWorkingDate() {
		return driver.findElements(hpWorkingDate);
	}
	
	public void getClickOnCAPITAAMS() {
		driver.findElement(clickOnCAPITAAMS).click();
	}
	
	public String getEmpName() {
		return driver.findElement(empName).getText();
	}

	public String getEmpID() {
		return driver.findElement(empID).getText();
	}
	public String getManagerName() {
		return driver.findElement(managerName).getText();
	}
	public String getManagerID() {
		return driver.findElement(managerID).getText();
	}
	
	public String getAbsentCount() {
		return driver.findElement(absentCount).getText();
	}
	public String getPresentCount() {
		return driver.findElement(presentCount).getText();
	}
	public String getShortAttendanceCount() {
		return driver.findElement(shortAttendanceCount).getText();
	}
	
	public List<WebElement> getWorkingDateList() {
		return driver.findElements(workingDateList);
	}
	
	public WebElement getWorkingDate() {
		 return driver.findElement(workingDate);
	}
	public void getType(String typ) {
		s=new Select(driver.findElement(type));
		//List<WebElement> typeOption=s.getOptions();
		s.selectByVisibleText(typ);
		//return typeOption;
	}
	
	public void getTypeList(String typ) {
		List<WebElement> typelist = driver.findElements(typeList);
		
		for(WebElement ty : typelist)
		{
				s=new Select(ty);
				s.selectByVisibleText(typ);
		}
	}
	public void getCategory(String cat) {
		s=new Select(driver.findElement(category));
		//List<WebElement> categoryOptions=s.getOptions();
		s.selectByVisibleText(cat);
		//return typeOption;
	}	
	
	public void getCategorylist(String cat) {
	List<WebElement> categorylist = driver.findElements(categoryList);
	
	for(WebElement category : categorylist)
	{
			s=new Select(category);
			s.selectByVisibleText(cat);
	}
	
}
	
	
	public void getReason(String  reason) {
		s=new Select(driver.findElement(category));
		s.selectByVisibleText(reason);
	}
	
	
	public void getReasonlist(String  Reason) {
		
		List<WebElement> reasonlistt = driver.findElements(reasonList);
				
				for(WebElement reason1 : reasonlistt)
				{
						s=new Select(reason1);
						s.selectByVisibleText(Reason);
				}
				
			}
					
			
	public void getRemark(String rmrk) {
		driver.findElement(remark).sendKeys(rmrk);
	}
	
	public void getRemarklist(String rmrk) {
		List<WebElement> remarklist = driver.findElements(remarkList);
				
				for(WebElement remark1 : remarklist)
				{
						remark1.sendKeys(rmrk);
				}
	}
	
	public void getMarkBtn() {
		driver.findElement(markBtn).click();
	}
	
	public void getAllMarkBtn() {
		List<WebElement> markbuttons = driver.findElements(markBtnList);
				for(WebElement mark : markbuttons)
				{
						mark.click();
				}
				
			}
	
}
