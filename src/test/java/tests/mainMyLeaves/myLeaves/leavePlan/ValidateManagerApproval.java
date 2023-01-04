package tests.mainMyLeaves.myLeaves.leavePlan;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import tests.LoginTest.LoginPage;

public class ValidateManagerApproval {

	public WebDriver driver;
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validateManagerLogin();
		driver = lp.driver;
		
	}
	
	
	
	
	
	
}
