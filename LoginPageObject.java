package pageObjects.loginObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject {
	
	
	public WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By user=By.name("loginfmt");
	By userNextButton=By.xpath("//input[@id='idSIButton9']");
	By pass=By.xpath("//input[@name='passwd']");
	By passNextButton=By.xpath("//input[@type='submit']");
	By signInButton=By.xpath("//*[@id='idBtn_Back']");
	
	
	By signOutBtn=By.cssSelector("#btnSignOutMaster");
	By signoutTittle=By.cssSelector(".message:nth-child(1)");
	
	//after logout click on "Website" to relogin
	By clickOnWebsite=By.xpath("//span[@class='emphasis']");
	By clickOnUseAnotherAccount=By.xpath("//div[contains(text(),'Use another account')]");
	
	//INT enviroment login from user to manager
	By clickOnAnotherUser=By.id("txtUserName");
	By clickOnIcon=By.id("ImageButton1");
	
	//Forgot Pass
	By clickOnForgotPassLink=By.id("idA_PWD_ForgotPassword");
	By enterEmail=By.id("ContentPlaceholderMainContent_TextBoxUserIdentifier");
    By captchaImage=By.xpath("//img[@id='RepMapVisualChallenge']");
    By captchTextBox=By.id("RepMapChallengeSolution");
	By clickOnNextBtn=By.cssSelector("#ContentPlaceholderMainContent_ButtonNext");
    
	public void getuser(String username) {
		driver.findElement(user).sendKeys(username);
	}
	
	public void getclickbtn() {
		driver.findElement(userNextButton).click();;
	}
	
	public void getpass(String password) {
		driver.findElement(pass).sendKeys(password);
	}
	
	public void getpassnextbtn() {
		driver.findElement(passNextButton).click();
	}
	
	public void getsignInButton() {
		driver.findElement(signInButton).click();
	}
	
	public void getsignOutBtn() {
		driver.findElement(signOutBtn).click();
	}
	
	public String getsignOutTittle() {
		String signOutText=driver.findElement(signoutTittle).getText();
		return signOutText;
	}
	
	public void getClickOnWebsite() {
		driver.findElement(clickOnWebsite).click();
	}
	public void getClickOnUseAnotherAccount() {
		driver.findElement(clickOnUseAnotherAccount).click();
	}
	
	//======================Int Enviroment============================
	public void getClickOnAnotherUser() throws InterruptedException {
		//driver.findElement(clickOnAnotherUser).click();
		//Thread.sleep(1000);
		driver.findElement(clickOnAnotherUser).sendKeys("P50044121");
	}
	public void getClickOnIcon() {
		driver.findElement(clickOnIcon).click();
	}
	
	//=============forgot password=============================
	public void getClickOnForgotPassLink() {
		driver.findElement(clickOnForgotPassLink).click();
		}
	
	public void getEnterEmail(String userName) {
		driver.findElement(enterEmail).sendKeys(userName);
	}
	
	public WebElement getCaptchImage() {
		return driver.findElement(captchaImage);
	}
	
	public void getCaptchTextBox(String captchText) {
		driver.findElement(captchTextBox).sendKeys(captchText);
	}
	
	public void getClickOnNextBtn() {
		driver.findElement(clickOnNextBtn).click();
	}
}


