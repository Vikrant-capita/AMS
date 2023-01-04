package tests.giftOfLeaves;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.giftOfLeaves.GiftOfLeavesObject;
import tests.LoginTest.LoginPage;

public class DonateGiftOfLeave {

	public	WebDriver driver;

	public 	DonateGiftOfLeave(WebDriver driver) {
		//super();
		this.driver=driver;		
	}
	
	
	public void validateGiftOfLeaves() throws InterruptedException, IOException {
		
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		GiftOfLeavesObject giftLeave=new GiftOfLeavesObject(driver);
		giftLeave.getClickOnAMS();
	}
	
	
	
}
