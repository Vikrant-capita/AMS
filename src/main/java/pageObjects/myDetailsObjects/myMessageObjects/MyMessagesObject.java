package pageObjects.myDetailsObjects.myMessageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MyMessagesObject {
	
	public WebDriver driver;
	public Select s ;
	
	public MyMessagesObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myMessage=By.cssSelector("MyMessagesObject");
	
	By messages=By.xpath("//table[@id='ContentPlaceHolderBody_Grid_MyAlert']/tbody/tr/td[2]");

	
	public void getMyMessagaes() {
		driver.findElement(myMessage).click();
	}

	public String getMessages() {
		String messagesText=driver.findElement(messages).getText();
		return messagesText;
	}



}
