	package tests.Homepage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.homePageObjects.HomePageObject;
import tests.LoginTest.LoginPage;
import utils.DateConversionFormat;

public class ValidateHomepage {

	public WebDriver driver;
	public HomePageObject hp;
	public SoftAssert sa;
	public String userNameText;
	
	@Test
	public void homepageElements() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		HomePageObject hp=new HomePageObject(driver);
		String capitaAMS = hp.getCapitaAMS();
		sa=new SoftAssert();
		sa.assertEquals(capitaAMS, "CAPITA AMS");
		System.out.println("CapitaAMS is matched");
		
		String myDetails = hp.getmyDetails();
		sa.assertEquals(myDetails, "My Details");
		System.out.println("myDetails is matched");
		
		String myLeaves=hp.getmyLeaves();
		sa.assertEquals(myLeaves, "My Leaves");
		System.out.println("myLeaves is matched");
		
		String userManual=hp.getUserManual();
		sa.assertEquals(myLeaves, "User Manual");
		System.out.println("User Manual is matched");
		
		String GiftOfLeave=hp.getGiftOfLeave();
		sa.assertEquals(GiftOfLeave, "Gift Of Leave");
		System.out.println("GiftOfLeave is matched");
	}
	
	@Test(enabled=true)
	public String validateRightPanelText() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		hp=new HomePageObject(driver);
		sa=new SoftAssert();
		Thread.sleep(2000);
		String expiryTimeText= hp.getSessionExpiry();
		//System.out.println("Expiry Time is :"+ expiryTimeText);
		Assert.assertEquals(expiryTimeText, "20", "Default expiry time out sesion matched");
		System.out.println("Default expiry time out sesion : '"+ expiryTimeText +"' is matched");
		//Assert.assertEquals(expiryTimeText, 20, "Default expiry time out sesion matched");
		Thread.sleep(2000);
		userNameText=hp.getUserNameText1().split("e ")[1];
		
		System.out.println("User Name is :"+ userNameText);
		Assert.assertEquals(userNameText, "Vikrant Bingi", "Default expiry time out sesion matched");
		//Welcome Vikrant Bingi
		
		System.out.println("User Name : '"+ userNameText +"' is matched");
		Thread.sleep(2000);
		String scheduleName=hp.getscheduleTime();
		//System.out.println(scheduleName);
		Assert.assertEquals(scheduleName, "Schedule : Available till 30-Dec-2023", scheduleName);
		System.out.println("Scheduled name : '"+ scheduleName +"' is matched");
		
		String dateText=scheduleName.split("till ")[1];
		DateConversionFormat format=new DateConversionFormat();
		format.dateFormatConversion(dateText);
		return userNameText;
	}
	
	
	
	@AfterTest(enabled=true)
	public void tearDown() {
		driver.close();
	}

	
}
