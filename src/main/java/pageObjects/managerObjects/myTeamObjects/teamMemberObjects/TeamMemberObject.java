package pageObjects.managerObjects.myTeamObjects.teamMemberObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamMemberObject {

	WebDriver driver;
	
	public TeamMemberObject(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="#TreeMenu1_MenuTreeViewt10")
	WebElement clickOnMyTeam;
	
	@FindBy(id="ContentPlaceHolderBody_GridViewMyTeam")
	WebElement teamTableExist;
	
	@FindBy(xpath="//table[@id='ContentPlaceHolderBody_GridViewMyTeam']//tr/td[2]")
	List<WebElement> empDetailList;
	
	public void getClickOnTeamMember() {
		clickOnMyTeam.click();
	}
	
	public boolean getTeamTableExist() {
		if(teamTableExist.isDisplayed())
		return true;
		else
		return false;
	}
	public List<WebElement> getEmpDetailList() {
		return empDetailList;
	}
	
}
