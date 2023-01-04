package pageObjects.managerObjects.myTeamObjects.teamExceptionsListObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class MyTeamExceptionListObject {
	
public WebDriver driver;
	
	public MyTeamExceptionListObject(WebDriver driver)
	{
		this.driver=driver;
	}
	

	By clickOnTeamExpList = By.xpath("//a[text()='Team Exceptions List']");
	By teamMemberName = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLEmployeeName']/tbody/tr/td/label");
	By count = By.id("ContentPlaceHolderBody_lblAbsent");
	By workingDateList = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[1]/span");
	By employeeNameList = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[2]/span");
	By typeOptions= By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[4]/select");
	By categoryOptions = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[5]/select");
	By reasonOptions = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[6]/select");
	By remark = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[8]/input");
	By approve = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[9]/input");
	
	public void getClickOnteamExpList()
	{
		driver.findElement(clickOnTeamExpList).click();
	}
	
	public void getTeamMemberName(String memberName)
	{
		List<WebElement> lists = driver.findElements(teamMemberName);
		for(WebElement list : lists)
		{
			String text =list.getText();
			if(text.contains(memberName))
			{
				System.out.println("member :"+text);
				list.click();
				break;
			}
		}
		
	}
	
	public int getCount()
	{
				
		return	 Integer.parseInt(driver.findElement(count).getText());
	}
	
	public List<String> getWorkingDateList()
	{
		List<String> allworkingDateList = new ArrayList<>();
		List<WebElement> dateList =driver.findElements(workingDateList);
		for(WebElement date:dateList)
		{
			//System.out.println("working dates:"+date.getText());
			allworkingDateList.add(date.getText());
		}
		return allworkingDateList;
	
	}
	
	public List<String> getEmployeeList()
	{
		List<String> allEmpList = null;
		List<WebElement> empList =driver.findElements(employeeNameList);
		for(WebElement emp:empList)
		{
			allEmpList.add(emp.getText());
		}
		return allEmpList;
	
	}
	
	public void getTypeList(String selectType, int i)
	{
		List<WebElement> types = driver.findElements(typeOptions);
		for(int j=i;j<types.size();j++)
		{
			Select Types1 = new Select(types.get(j));
			Types1.selectByVisibleText(selectType);	
			break;
		}
			
	}
	
	public void getCategoryList(String selectCat, int i)
	{
		List<WebElement> cat = driver.findElements(categoryOptions);
		for(int j=i;j<cat.size();j++)
		{
			Select Types1 = new Select(cat.get(j));
			Types1.selectByVisibleText(selectCat);	
			break;
		}
			
	}
	
	public void getReasonList(String selectReason, int i)
	{
		List<WebElement> reason = driver.findElements(reasonOptions);
		for(int j=i;j<reason.size();j++)
		{
			Select Types1 = new Select(reason.get(j));
			Types1.selectByVisibleText(selectReason);	
			break;
		}
			
	}
	
	public void getRemark(String addremark,int i)
	{
		List<WebElement> remarks=driver.findElements(remark);
		for(int j=i;j<remarks.size();j++)
		{
			remarks.get(j).sendKeys(addremark);
			break;
		}
	}
	
	public List<WebElement> getapprove()
	{
		List<WebElement> approves=driver.findElements(approve);
		for(int j=0;j<approves.size();j++)
		{
			approves.get(j).click();
			break;
		}
		return approves;
	}
	
}
