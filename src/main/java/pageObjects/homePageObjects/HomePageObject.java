package pageObjects.homePageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePageObject {

	public WebDriver driver;
	Select s ;
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
	}
	//Left side of Homepage
	By capitaAMS= By.xpath("//div[@id='logo']//a");
	By mydetails= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt0']");
	By myLeaves= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt4']");
	By userManual= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt9']");
	By giftOfLeave= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt12']");
	
	//Righ-Top of Homepage
	
	By sessionExpiryTime= By.xpath("//table/tbody/tr/td[1]/span[@id='lblSessionTime']");  
	By loggedUserName =By.xpath("//table/tbody/tr/td[2]/span[@id='search_text']");			
	//By scheduleTime=By.xpath("//span[@id='ContentPlaceHolderTitle_Lbltopmsg1']");
	
	
	//Right-Top Corner
	By sessionExpiry=By.xpath("//table[@class='tablewidth']/tbody/tr/td/span[@id='lblSessionTime']");
	By userNameText1= By.xpath("//table[@class='tablewidth']/tbody/tr/td[2]/span[@id='search_text']");
	//By userNameText=By.xpath("//table[@class='tablewidth']/tbody/tr/td[2]/span[@id='search_text']");
	By scheduleTime=By.xpath("//span[@id='ContentPlaceHolderTitle_Lbltopmsg1']");
	
	//login with diff user
	By clickOnDiffUser=By.id("txtUserName");
	By clickOnSubBtn=By.id("ImageButton1");
	
	//left side links
	By mainMenuLink =By.xpath("//div[@id='TreeMenu1_MenuTreeView']//td//a[@class='TreeMenu1_MenuTreeView_0 groovybutton TreeMenu1_MenuTreeView_1']");
	By activeAttendance=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]/a[@style='color:Red;font-weight:bold;']");
			
	//ACtive Attendance tab
	By activeAttendanceOpt= By.xpath("//a[@style='color:Red;font-weight:bold;']");
	By leaveTypeDD = By.cssSelector("#ContentPlaceHolderBody_UserStatus1_DDLType");
	By categoryDD = By.cssSelector("#ContentPlaceHolderBody_UserStatus1_ddlCategories"); 
	By reasonDD = By.cssSelector("#ContentPlaceHolderBody_UserStatus1_DDLReason");
	By remark=By.id("ContentPlaceHolderBody_UserStatus1_TxtRemarks");
	By submitBtn=By.cssSelector("#ContentPlaceHolderBody_UserStatus1_btnAddToCart");
	By submitText=By.id("ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_LBL_MSG");							 
	By workingDateList=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]");
	
	//table/tbody/tr/td[4]/a[contains(text(),'UA')]//preceding::span[contains(@id,'ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus_LBLNewStatus_')]                    
	
	//table/tbody/tr/td[4]/a[contains(@style,'Red')] [contains(@title,'ABSENT')] //preceding::span[contains(@id,'ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus_LBLNewStatus_')]                    
	
	//Historical Attendance
	By histAttenanceOpt=By.xpath("//*[contains(text(),'Historical Attendance')]");
	By year=By.xpath("//select[@id='ContentPlaceHolderBody_UserStatus1_TStatus_ArchivedStatus_DDLYear']");
	By month=By.xpath("//select[@id='ContentPlaceHolderBody_UserStatus1_TStatus_ArchivedStatus_DDLMonth']");
	
	By button=By.cssSelector("#ContentPlaceHolderBody_UserStatus1_TStatus_ArchivedStatus_IMGBtnSearch");
	By attendanceHistory=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_ArchivedStatus_GridViewArchieve']/tbody/tr/td[1]/span");
	
	//My Exception
	By explist = By.xpath("//span[@class='ContentPlaceHolderBody_UserStatus1_tvMyExceptions_0 ContentPlaceHolderBody_UserStatus1_tvMyExceptions_1']");
	
	//By ualist = By.xpath("//table/tbody/tr/td[4]/a[contains(text(),'UA')]");
	By ualist=By.xpath("//a[contains(text(),'UA') and @style='color:Red;font-weight:bold;']");
	
	By alllist = By.xpath("//a[@title='ABSENT#']/parent::td/parent::tr//td[1]/span");
	//By alllist = By.xpath("//a[@class='aspNetDisabled']/parent::td/parent::tr//td[1]/span");
	By myexptext = By.xpath("(//div[@class='stats-link']/a)[1]");
	By sadimage = By.id("ContentPlaceHolderBody_UserStatus1_imgMyException");
	By myExcpText=By.xpath("(//div[@class='stats-link']/a)[1]/span");
	
	By redUAWorkingList=By.xpath("//a[contains(text(),'UA') and @style='color:Red;font-weight:bold;']/preceding::td[3]/span");
	

	//Pending Leave
	//By pendingtext = By.xpath("(//div[@class='stats-link']/a)[2]");
	By pendingtext=By.xpath("//a[contains(text(),'Pending Leave/Holiday request by LM')]");
	By approachtext = By.xpath("//div[@class='mrt-5 stats-link']/a");
	By imagetxt = By.id("ContentPlaceHolderBody_UserStatus1_imgLeaveHoliday");
	By awaitingApprovalText=By.id("ContentPlaceHolderBody_UserStatus1_trCLAwait");
	
	By awaitingApprovalTextNameList=By.id("//table[@id='ContentPlaceHolderBody_UserStatus1_tbtPendingApproval']/tbody/tr/td[1]");
	By awaitingApprovalTextCountList=By.id("//table[@id='ContentPlaceHolderBody_UserStatus1_tbtPendingApproval']/tbody/tr/td[2]");

	
	
	//My Pending Action
	By pendingacttext = By.xpath("(//div[@class='stats-link']/a)[3]");
	By actionimagetxt = By.id("ContentPlaceHolderBody_UserStatus1_imgPendingAction");
	
	
	
	
	
	public String getCapitaAMS() {
		String capitaAMSText=driver.findElement(capitaAMS).getText();
		return capitaAMSText;
	}
	
	public String getmyDetails() {
		String mydetailsText=driver.findElement(mydetails).getText();
		return mydetailsText;
	}
	
	public String getmyLeaves() {
		String myLeavesText=driver.findElement(myLeaves).getText();
		return myLeavesText;
	}
	
	public String getUserManual() {
		String userManualText=driver.findElement(userManual).getText();
		return userManualText;
	}
	
	public String getGiftOfLeave() {
		String giftOfText=driver.findElement(giftOfLeave).getText();
		return giftOfText;
	}
	
	
	public List<WebElement> getMainMenuLink() {
		List<WebElement> menudriver=driver.findElements(mainMenuLink);
		return menudriver;
	}
	
	public List<WebElement> getActiveAttendance() {
		List<WebElement> activeAttendancedriver=driver.findElements(activeAttendance);
		return activeAttendancedriver;
	}
		public String getSessionExpiry() {
		String sessionExpiryText=driver.findElement(sessionExpiry).getText();
		return sessionExpiryText;
	}
	
	public String getUserNameText1() {
		String userNameText=driver.findElement(userNameText1).getText();
		return userNameText;
	}
	
	public String getscheduleTime() {
		String scheduleText=driver.findElement(scheduleTime).getText();
		return scheduleText;
	}
	
	public WebElement  getClickOnDiffUser() {
		return driver.findElement(clickOnDiffUser);
	}
	
	public void getClickOnSubBtn() {
		driver.findElement(clickOnSubBtn).click();;
	}
	
	//================Active Attendance=====================
	
	public WebElement getleaveType(String option) {
		WebElement leavetype = driver.findElement(leaveTypeDD);
		s = new Select(leavetype);
		s.selectByVisibleText(option);
		return null;
	}
	public void getAtiveAttendanceOpt()
	{
		driver.findElement(activeAttendanceOpt).click();
	}
	
	public void getCategory(String opt)
	{
		s = new Select(driver.findElement(categoryDD));
		s.selectByVisibleText(opt);
	}
	public void getReason(String opt)
	{
		s = new Select(driver.findElement(reasonDD));
		s.selectByVisibleText(opt);
	}
	
	public void getRemark(String rmrk) {
		driver.findElement(remark).sendKeys(rmrk);
	}
	
	public void getSubmitBtn() {
		driver.findElement(submitBtn).click();
	}
	
	public String getSubmitText() {
		return driver.findElement(submitText).getText();
	}
	
	public List<WebElement> getWorkingDateList() {
		return driver.findElements(workingDateList);
	}
	
	//============= History Attendance=================================
	public void getHistoryAttendance() {
		driver.findElement(histAttenanceOpt).click();
	}
	
	public void getYear(String yr)
	{
		s = new Select(driver.findElement(year));
		s.selectByVisibleText(yr);
	}
	
	public void getMonth(String mnth)
	{
		s = new Select(driver.findElement(month));
		s.selectByVisibleText(mnth);
	}
	
	public void getButton() {
		driver.findElement(button).click();
	}
	
	public List<WebElement> getAttendanceHistory() {
		List<WebElement> attHistoryText=driver.findElements(attendanceHistory);
		return attHistoryText;
	}
	
	
	
	//My Exception methods
		public List<WebElement> getexplist()
		{
			return driver.findElements(explist);
			 
		}
		public List<WebElement> getualist()
		{
			return driver.findElements(ualist);
			 
		}
		public List<WebElement> getalllist()
		{
			return driver.findElements(alllist);
			 
		}
		public String getmyexptext()
		{
			return driver.findElement(myexptext).getText();
		}
		
		public WebElement getsadimage()
		{
			return driver.findElement(sadimage);
		}
		
		public String getMyExcpText() {
			return driver.findElement(myExcpText).getText();
		}
		
		
		public List<WebElement> getRedUAWorkingList() {
			return driver.findElements(redUAWorkingList);
		}

		//====================Pending Leave methods========================
		public String getpendingtext()
		{
			return driver.findElement(pendingtext).getText();
		}
		
		public String getapproachtext()
		{
			return driver.findElement(approachtext).getText();
		}
		
		public String getpendingimage()
		{
			String image=driver.findElement(imagetxt).getAttribute("class");
			String text  = image.split(" ")[0];
			return text;
		}
		
		public WebElement getAwaitingApprovalText() {
			return driver.findElement(awaitingApprovalText);
			
		}
		
		public List<WebElement> getAwaitingApprovalTextNameList() {
			return driver.findElements(awaitingApprovalTextNameList);
		}
		public List<WebElement> getAwaitingApprovalTextCountList() {
			return driver.findElements(awaitingApprovalTextCountList);
		}
		
		//---------------------------MyPending Action method--------------------
		public String getpendingacttext()
		{
			System.out.println("text : "+driver.findElement(pendingacttext).getText());
			return driver.findElement(pendingacttext).getText();
		}
		
		public String getpendingactionimage()
		{
			String image=driver.findElement(actionimagetxt).getAttribute("class");
			String text  = image.split(" ")[0];
			return text;
		}
	
}
