package tests.Homepage.managerHomepageTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.managerHomePageObjects;
import tests.LoginTest.LoginPage;

public class MgrExceptionLeavesPendingActions {
	
	public WebDriver driver;
	public List<String> allPendingActionText = new ArrayList<>();
	public LoginPage lp;
	
	
	
	@BeforeTest
	public void validateManagerLogin() throws IOException, InterruptedException {
		lp=new LoginPage();
		driver=lp.validateManagerLogin();
		
	}
	
	@Test
	public void validateMgrExceptionPendingAction()
	{
		try {
			System.out.println("driver +++ "+driver);
			managerHomePageObjects mgrHPObj=new managerHomePageObjects(driver);
			List<WebElement>  allPendingActionList=mgrHPObj.getAllPendingActionList();
			int mgrAttendanceSize =mgrHPObj.getAllPendingActionList().size();
			System.out.println("Mgr pending action list size :"+mgrAttendanceSize);
			for(int i=0; i<allPendingActionList.size() ;i++) {
				 allPendingActionText.add(allPendingActionList.get(i).getText());
				
			}
		}
	
		catch(NoSuchElementException exception) {
			System.out.println("No Holiday/Exception/Leaves are found ");
		}
		System.out.println("Mgr pending action list name :"+allPendingActionText);
		//return allPendingActionText;
	}
	
	/*@Test
	public String validateMgrExceptionPendingAction() {
			
		try {
			managerHomePageObjects mgrHPObj=new managerHomePageObjects(driver);
			List<WebElement>  allPendingActionList=mgrHPObj.getAllPendingActionList();
			int mgrAttendanceSize =mgrHPObj.getAllPendingActionList().size();
			System.out.println("Mgr pending action list size :"+mgrAttendanceSize);
			for(int i=0; i<=allPendingActionList.size()-1 ;i++) {
				String allPendingActionText=allPendingActionList.get(i).getText();
				System.out.println("Mgr pending action list name :"+allPendingActionText);
				
			}
		}
	
		catch(NoSuchElementException exception) {
			System.out.println("No Holiday/Exception/Leaves are found ");
		}
		return allPendingActionText;
		
	}*/

}
