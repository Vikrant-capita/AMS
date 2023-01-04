package tests.managerTest.myTeam.teamMember;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import pageObjects.managerObjects.myTeamObjects.teamMemberObjects.TeamMemberObject;
import tests.LoginTest.LoginPage;
import utils.excelDriven.MgrTeamMemberExcel;

public class TeamMemberTest {

	public LoginPage lp;
	public WebDriver driver;
	
	public HomePageObject hp;
	public String leaveTypeName="";
	public List<String> arrListData;
	
	@BeforeTest
	public void initializer() throws InterruptedException, IOException	{
		lp=new LoginPage();
		driver=lp.INTManagerLogin();
		                                                                                                                                 
	}	
	
	@Test
	public void validateTeamMembaer() throws IOException, InterruptedException {
		TeamMemberObject teamMemObj=new TeamMemberObject(driver);
		teamMemObj.getClickOnTeamMember();
		Thread.sleep(1000);
		System.out.println("boolean : "+teamMemObj.getTeamTableExist());
		if(teamMemObj.getTeamTableExist()) {
			System.out.println("table emp list size :"+teamMemObj.getEmpDetailList().size());
			for(int i=0;i<teamMemObj.getEmpDetailList().size(); i++) {
				MgrTeamMemberExcel teamMem=new MgrTeamMemberExcel();
				String empDetailName=teamMemObj.getEmpDetailList().get(i).getText();
								
				arrListData=teamMem.getData(teamMemObj.getEmpDetailList().get(i).getText(), "Employee Detail","Team Member");
				System.out.println("array data size:"+arrListData.size());
				System.out.println("array data :"+arrListData);
				
				List<WebElement> rowTableData=driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_GridViewMyTeam']//tr["+(i+2)+"]/td"));
				System.out.println("row table data size:"+rowTableData.size());
				for(int j=0; j<rowTableData.size();j++) {
					//System.out.println("emp details :"+teamMemObj.getEmpDetailList().get(j).getText());
					
					String cellData=rowTableData.get(j).getText();
					System.out.println("cell data :"+cellData);
					System.out.println("arr list data :"+arrListData.get(j));
					if(j!=5) {
						Assert.assertEquals(cellData, arrListData.get(j));
					}			
				}
			}
		}	
	}	
}

//=====================Excel date format is different that UI date format=======================
