package tests.myDetails;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import pageObjects.myDetailsObjects.myMessageObjects.MyMessagesObject;
import tests.LoginTest.LoginPage;
import utils.DateConversionFormat;

public class MyMessages {

	public WebDriver driver;
	public SoftAssert sa;
	
	
	@Test
	public void ValidateMyMessages() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		MyMessagesObject msg=new MyMessagesObject(driver);
		msg.getMyMessagaes();
		String msgText=msg.getMessages();
		//Assert.assertEquals(msgText, "");
	}
	
	
	
	
}
