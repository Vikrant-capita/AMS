package pageObjects.managerMyTeamApprovalObjects.pendingHolidayReqObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PendingHolidayReqObject {

	public WebDriver driver;
	Select s ;
	
	public PendingHolidayReqObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By clickOnHoliday=By.id("ContentPlaceHolderBody_UserStatus1_lbHoliday");
	By clickOnPendingHoliReq=By.cssSelector("#TreeMenu1_MenuTreeViewt24");
	By clickOnCapitaAMS=By.xpath("//a[contains(text(),'CAPITA AMS')]");
	
	By clickOnWorkLocationDD=By.id("ContentPlaceHolderBody_DDLWorkLocation");
	By holidayListDD=By.id("ContentPlaceHolderBody_DDLHolidayList");
	
	By holidayTableList=By.cssSelector("#ContentPlaceHolderBody_GridViewHoliday tr td:nth-child(3)");
	By holidayReqTable=By.cssSelector("#ContentPlaceHolderBody_GridViewHoliday");
	By clickoncheckBox=By.xpath("//span[contains(text(),'Vikrant Bingi')]//preceding::input[1]");
	By clickOnApprove=By.xpath("//span[contains(text(),'Vikrant Bingi')]//following::td/input[contains(@name,'ButApprove')]");
	By clickOnReject=By.xpath("//span[contains(text(),'Vikrant Bingi')]//following::td/input[contains(@name,'ButReject')]");
	By userNameList=By.xpath("//span[contains(text(),'Vikrant Bingi')]");		//Vikrant Bingi
	
	
	public WebElement getClickOnHoliday() {
		return driver.findElement(clickOnHoliday);
	}
	
	public void getClickOnPendingHoliReq() {
		driver.findElement(clickOnPendingHoliReq).click();
	}
	
	public void getClickOnCapitaAMS() {
		driver.findElement(clickOnCapitaAMS).click();
	}
	
	public void getClickOnWorkLocationDD() {
		Select s=new Select(driver.findElement(clickOnWorkLocationDD));
		
	}
	
	public void getHolidayListDD() {
		driver.findElement(holidayListDD);
	}
	
	
	public List<WebElement> getHolidayTableList() {
		return driver.findElements(holidayReqTable);
	}
	public WebElement getHolidayReqTable() {
		return driver.findElement(holidayReqTable);
	}
	
	public void getClickoncheckBox() {
		driver.findElement(clickoncheckBox).click();
	}
	public void getClickOnApprove() {
		driver.findElement(clickOnApprove).click();
	}
	public void getClickOnReject() {
		driver.findElement(clickOnReject).click();
	}
	
	public List<WebElement> getUserNameList() {
		return driver.findElements(userNameList);
	}
	
}
