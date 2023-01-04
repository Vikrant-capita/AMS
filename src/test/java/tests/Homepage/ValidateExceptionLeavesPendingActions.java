	package tests.Homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import lombok.var;
import pageObjects.homePageObjects.HomePageObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;
import utils.DateConversionFormat;

public class ValidateExceptionLeavesPendingActions extends BaseTest{
	public WebDriver driver;
	public HomePageObject hp;
	public Properties prop;
	public int lastCount;
	
//	public ValidateExceptionLeavesPendingActions(WebDriver driver) {
//		// TODO Auto-generated constructor stub
//		this.driver=driver;
//	}

	@Test(priority=0)
	public void validateMyException() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		hp = new HomePageObject(driver);
		List<WebElement> lists=	hp.getexplist();
		System.out.println("list size :"+lists.size());
		List<WebElement> alllist = hp.getalllist();
		System.out.println("alllist size :"+alllist.size());
		List<WebElement> ualist = hp.getualist();
		System.out.println("ua size :"+ualist.size());
		
		//for exception count text validation before and after approval
		String myexception = hp.getmyexptext();			//exception name and count
		
		String myUserExcpText=hp.getMyExcpText().substring(1, 2);  		//exception count 
		System.out.println("My User Exception Text :"+myUserExcpText);
		
		Assert.assertEquals(myexception, "My Exceptions ("+ualist.size()+")","validation");
		WebElement image=	hp.getsadimage();
			if(lists.size()!=0)
			{	
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "sad");
				System.out.println("Sad Image Matched");
			}
			else
			{
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "happy");
				System.out.println("happy Image Matched");
			}
		validateDatenText(lists, alllist, ualist);

	}
	 
	public void validateDatenText(List<WebElement> lists,List<WebElement> alllist,List<WebElement> ualist)
	{
		List<String> UAlist1 = new ArrayList<>();
		List<String> UAlist2 = new ArrayList<>();
		List<Integer>data1 = new ArrayList<>() ;
		List<Integer>data2 = new ArrayList<>() ;
		DateConversionFormat datecon = new DateConversionFormat();
		int n=1;
		for(int i=0;i<lists.size();i++)
		{
			
			if(n<4)
			{
				String Ualist = lists.get(i).getText().split("2022 ")[1].trim();
				UAlist1.add(Ualist);
				String text = lists.get(i).getText().split(" ")[0];
				List<Integer>d1 = datecon.dateFormatConversion2(text);
				data1.addAll(d1);
				n++;
			}
			else
				break;
			
		}
		int k=1;
		for(int j=alllist.size()-1;j>=0;j--) 
		{
			
			if(k<4)
			{
				String text1 = alllist.get(j).getText().split(", ")[1];
				//System.out.println("data 2 text : "+text1);
				List<Integer>d2 = datecon.dateFormatConversion1(text1);
				data2.addAll(d2);
				k++;
			}
			else
				break;
		}
		
		int l=1;
		for(int j=ualist.size()-1;j>=0;j--)
		{
			if(l<4)
			{
				UAlist2.add(ualist.get(j).getText());
				l++;
			}
			else
				break;
		}

		System.out.println("data 1 :"+data1);
		System.out.println("data 2 :"+data2);
		Assert.assertEquals(data1, data2);
		System.out.println("ua list 1 : "+UAlist1);
		System.out.println("ua list 2 : "+UAlist2);
		Assert.assertEquals(UAlist1, UAlist2);
		System.out.println("both text and date matched");
	}
	
	
	@Test(priority=1)
	public  void validatePendingLeave() throws IOException
	{
		validatePendingLeave1(driver);		
	}
	
	//Awaiting Approval CLs   Awaiting Approval Holidays
	public  List<String> validatePendingLeave1(WebDriver driver) throws IOException
	{
		prop=getProperties();
		hp = new HomePageObject(driver);
		
		String pendtext =hp.getpendingtext();		
		String apptext = hp.getapproachtext();
		Assert.assertEquals(pendtext, "Pending Leave/Holiday request by LM");
		Assert.assertEquals(apptext, "Approaching Leave / Holiday:");
		String imgtext = hp.getpendingimage();
		
		String nameCountText = "";		
			List<WebElement> nameList=driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_tbtPendingApproval']/tbody/tr/td[1]"));
			List<WebElement> countList=driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_tbtPendingApproval']/tbody/tr/td[2]/span"));
			
			List<String> allUserPendingActionText = new ArrayList<>();
			
			System.out.println("Name size :"+nameList.size());
			System.out.println("count size :"+countList.size());
					
			if(nameList.size()==0 && countList.size()==0) {
				String noPendingActionText=driver.findElement(By.id("ContentPlaceHolderBody_UserStatus1_dvMYLeaveNA")).getText();
				Assert.assertEquals(noPendingActionText, "No pending request", "No Pending leaves available");
				Assert.assertEquals(imgtext, "sad");
				List<String> noPendingReqText=new ArrayList<String> ();
				allUserPendingActionText.add("No Pending request count");
				//return allUserPendingActionText;
			}
			else {
				List<String> nameCountTextList=new ArrayList<String>();
				for(int i=0;i<=nameList.size()-1;i++) {
					String nameText=nameList.get(i).getText();
					String countText=countList.get(i).getText();
					nameCountText= nameText.concat(" "+countText);
					allUserPendingActionText.add(nameCountText);
					//nameCountTextList.add(nameCountText);
					System.out.println("Available holiday/Leave text and count :"+nameCountText);
					Assert.assertEquals(imgtext, "happy");
					//return allUserPendingActionText;
				}
		
			}	
			System.out.println("return value text :"+allUserPendingActionText);
			return allUserPendingActionText;
				 
	}
	
	
	//text is getting failed
	@Test(priority=2)
	public void  validateMyPendingAction()
	{
		String imgtext = hp.getpendingactionimage();
		Assert.assertEquals(imgtext, "happy");
		String text = driver.findElement(By.cssSelector(".newcusthead")).getText();
		System.out.println("pending action text :  "+text);
		String pendingacttext = hp.getpendingacttext();
		Assert.assertEquals(pendingacttext, "My Pending Action");

	}
	
	/*
	@Test(priority=3)
	public int validatePendingActionReturn() throws IOException {
		String s=validatePendingLeave1(driver);
		if(!s.equals("My Pending Action")){
			char lastChar=s.charAt(s.length()-1);
		System.out.println("Last character count :"+lastChar);
		System.out.println("return value :"+s);
		int lastCount=Integer.parseInt(String.valueOf(lastChar));
		System.out.println("Last count :"+lastCount);
		//return lastCount;
		}
		return lastCount;
	}
	
	*/
	
	
	
	
	@AfterTest(enabled=false)
	public void teardown()
	{
		driver.close();
	}
}
