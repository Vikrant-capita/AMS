package tests.Homepage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import tests.LoginTest.LoginPage;

public class ValidateHomepageLinks {
	public WebDriver driver;
	public SoftAssert sa;
	public HomePageObject hp;
	
	@Test
	public void homepageLink() throws InterruptedException, IOException, MalformedURLException{
		
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		hp=new HomePageObject(driver);
		//List<WebElement> links=hp.getMainMenuLink();
		//List<WebElement> links=menudriver.findElements(By.tagName("a"));
		sa=new SoftAssert();
		//brokenLink();
		activeAttendancelink();
		//validateRightPanelText();
	}	
	
	@Test(enabled=true)
	public void validateRightPanelText() throws InterruptedException, IOException {
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
		String userNameText=hp.getUserNameText1();
		//System.out.println("User Name is :"+ userNameText);
		Assert.assertEquals(userNameText, "Welcome Vikrant Bingi", "Default expiry time out sesion matched");
		System.out.println("User Name : '"+ userNameText +"' is matched");
		Thread.sleep(2000);
		String scheduleName=hp.getscheduleTime();
		String datetext=scheduleName.split("till ")[1];
		//System.out.println(scheduleName);
		Assert.assertEquals(scheduleName, "Schedule : Available till 30-Dec-2023", scheduleName);
		System.out.println("Scheduled name : '"+ scheduleName +"' is matched");
		
		
	}
	
	
	
	
		/*
		
		public void brokenLink() throws InterruptedException {
			List<WebElement> links=hp.getMainMenuLink();
		int k=0;
		for(WebElement link:links) {
			String key= Keys.chord(Keys.CONTROL,Keys.ENTER);
			link.sendKeys(key);
			k++;
			System.out.println(k+" : "+link.getText());
			
		}
		Set<String> windowhandle = driver.getWindowHandles();
		Iterator<String> it = windowhandle.iterator();
		while (it.hasNext()) 
		{
			Thread.sleep(4000);
			driver.switchTo().window(it.next());
			System.out.println("Tab Title : "+driver.getTitle());
			
		}
		
		//To get Response code
			String url=link.getAttribute("href");
		System.out.println(url);
		HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
		//URL obj=new URL(url);
		//HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int respCode=conn.getResponseCode();
		System.out.println(respCode);
		sa.assertTrue(respCode>400,"This is broken link : "+link.getText()+" with code "+respCode);
		
			sa.assertAll();	
		}

		*/
	public void activeAttendancelink() throws MalformedURLException, IOException {
		
		List<WebElement> activeAttendanceLink=hp.getMainMenuLink();
		System.out.println("size of links : "+activeAttendanceLink.size());
		for(WebElement link:activeAttendanceLink) {
			String url=link.getAttribute("href");
			String style="";
//			WebElement link=driver.findElement(By.id("ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus_LinkButton1_1"));
//			if (style==link.getAttribute("style").split(":")[1].split(";")[0]);
//			String url=link.getAttribute("href");
			
			System.out.println("link name : "+link.getText());

			if(url!=null)
		{
				System.out.println("Active URL is :"+url);
				System.out.println(isUrlValid(url));
			if(isUrlValid(url)) {
				System.out.println("inside try catch block");
			HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
			//URL obj=new URL(url);
			//HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode=conn.getResponseCode();
			System.out.println(respCode);
			sa.assertTrue(respCode>=400,"This is broken link : "+link.getText()+" with code "+respCode);
			//}
				sa.assertAll();
			}
			}}
	
	}
	public static boolean isUrlValid(String url) {
	      try {
	         URL obj = new URL(url);
	         obj.toURI();
	         return true;
	      } catch (MalformedURLException e) {
	    	 System.out.println("exception error msg :"+e.getMessage());
	         return false;
	      } catch (URISyntaxException e) {
	         return false;
	      }
	   
	}
		
	


	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	
}
