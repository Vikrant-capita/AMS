package pageObjects.giftOfLeaves;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GiftOfLeavesObject {

	public	WebDriver driver;

	public 	GiftOfLeavesObject(WebDriver driver) {
		//super();
		this.driver=driver;
		
	}
	
	//click on gift of leaves xpath
	
	By clickOnAMS=By.xpath("//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt1']");
	
	//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt1']


	public void getClickOnAMS() {
		driver.findElement(clickOnAMS).click();
	}


}
