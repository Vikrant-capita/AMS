package pageObjects.myDetailsObjects.myExceptionObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyExceptionHistoryObject {
	
	public WebDriver driver;
	public Select s ;
	
	public MyExceptionHistoryObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnMyExceptions= By.xpath("//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt2']");
	
	By clickOnMyExcepHist=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_header']/span[2]/span/span/span");
	
	By empName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLEmployeeName1");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLEmployeeID1");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLRMName1");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLRMID1");
	
	By workingDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[1]/span");
	
	
	By statusList = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[6]/span");
	By historyWorkingDateList = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[1]/span");
	By deleteList = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/td[9]");
	By deleteMessageText = By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_Label5");
	
	
	
	
	
	
	public void getClickOnMyExceptions() {
		driver.findElement(clickOnMyExceptions).click();
	}
	public void getClickOnMyExcepHist() {
		driver.findElement(clickOnMyExcepHist).click();
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
	
	
	public List<String> getStatusList()
	{
		List<WebElement> historyStatusList= driver.findElements(statusList);
		List<String> histStatusList = new ArrayList<>();
		for(WebElement status : historyStatusList)
		{
			histStatusList.add(status.getText());
		}
		
		
		return histStatusList;
	}
	
	public List<String> getHistoryWorkingDate()
	{
		List<WebElement> histworkingdate = driver.findElements(historyWorkingDateList);
		List<String> histWorkingDateList = new ArrayList<>();
		for(WebElement workdate :histworkingdate )
		{
			histWorkingDateList.add(workdate.getText());
		}
		return histWorkingDateList;
	}
	public List<WebElement> getDeleteButton()
	{
		List<WebElement> deletebuttons = driver.findElements(deleteList);
		return deletebuttons;
	}
	public String getDeleteMessage()
	{
		return driver.findElement(deleteMessageText).getText();
	}
	
	
	
}
