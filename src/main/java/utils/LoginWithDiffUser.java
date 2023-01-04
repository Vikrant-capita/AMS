package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import pageObjects.homePageObjects.HomePageObject;

public class LoginWithDiffUser {
	public WebDriver driver; 
	
	public void loginWithDiffUser(String INTManagerName) {
//		Properties prop=new Properties();
//		
//		INTManagerName=prop.getProperty("INTManagerID");
		
		HomePageObject hm=new HomePageObject(driver) ;
		hm.getClickOnDiffUser().click();;
		hm.getClickOnDiffUser().sendKeys(INTManagerName);
		System.out.println("int managaer nameinside method :"+INTManagerName);
		hm.getClickOnSubBtn();
				
	}
	
}
