package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myDetailsObjects.myExceptionObject.MyExceptionHistoryObject;
import utils.excelDriven.excelDriven;

public class UserManagerDetailsValidation {
	//private static final Object MyExceptionHistoryObject = null;
	public WebDriver driver;
	
	public UserManagerDetailsValidation(WebDriver driver) {
		this.driver=driver;
	}
	//Object objName=MyExceptionHistoryObject;
			
public void usersManagerDetailsValidation( String empName, String empID,String managerName,String managerID ) throws IOException {
	
		
	//String empName= myExHist.getEmpName();
	HomePageObject hp=new HomePageObject(driver);
	String ab=hp.getUserNameText1().split("e ")[1];
	//username=hp.userNameText;
	System.out.println("usename: "+ab);
	System.out.println(empName);
	
	Assert.assertEquals(empName, ab);
	
	excelDriven excel=new excelDriven();
	ArrayList<String> data=excel.getData(empName, "Username");		//empName:Vikrant Bingi ... Username:
	
	String UserID=data.get(0);
	String Password=data.get(1);
	String Username=data.get(2);
	String EMPID=data.get(3);
	String ManagerName=data.get(4);
	String ManagerID=data.get(5);
	
	//String empID=myExHist.getEmpID();
	//String managerName=myExHist.getManagerName();
	//String managerID=myExHist.getManagerID();
	//String managerID=ml.get
	
	Assert.assertEquals(empID, EMPID);
	Assert.assertEquals(managerName, ManagerName);
	Assert.assertEquals(managerID, ManagerID);
		
	}
		
}
