package tests.userMannual;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.userManualObjects.AMSFAQsObject;
import tests.LoginTest.LoginPage;

public class AMSFAQs {

	public WebDriver driver;
	
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test
	public void validateAMSFAQs() throws InterruptedException, IOException {
		Thread.sleep(2000);
		System.out.println("page tittle before click :"+ driver.getTitle());
		
		
		AMSFAQsObject AMSFAQs=new AMSFAQsObject(driver);
		AMSFAQs.getClickOnAMSFAQ();
		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		System.out.println("page url  after click :"+ url);
		Assert.assertEquals(url, "https://ams-in.capita.co.in/AMS_FAQs.pdf");
		
		URL pdfUrl = new URL(url);
		
		URLConnection urlConnection = pdfUrl.openConnection();
		
		InputStream ip = urlConnection.getInputStream();
		
		BufferedInputStream bf = new BufferedInputStream(ip);
		
		//
		PDDocument pdDocument = PDDocument.load(bf);
		
		int pageCount = pdDocument.getNumberOfPages();
		System.out.println("pages count: " + pageCount);
		//Assert.assertEquals(pageCount, 43);
		
		/*
		//
		System.out.println("------meta data of pdf-------");
		System.out.println(pdDocument.getVersion());
		System.out.println(pdDocument.getCurrentAccessPermission().canPrint());
		System.out.println(pdDocument.getCurrentAccessPermission().isReadOnly());
		System.out.println(pdDocument.getCurrentAccessPermission().isOwnerPermission());
		
		System.out.println(pdDocument.getDocumentInformation().getSubject());
		System.out.println(pdDocument.getDocumentInformation().getTitle());
		System.out.println(pdDocument.getDocumentInformation().getCreator());
		System.out.println(pdDocument.getDocumentInformation().getCreationDate());
		
		System.out.println(pdDocument.isEncrypted());
		System.out.println(pdDocument.getDocumentId());

*/
		//read full pdf text:
		PDFTextStripper pdfStripper = new PDFTextStripper();
//		String pdfFullText = pdfStripper.getText(pdDocument);
//		System.out.println(pdfFullText);
//		Assert.assertTrue(pdfFullText.contains("Recognized worldwide"));
		
		pdfStripper.setStartPage(4);
		String pdfPageText = pdfStripper.getText(pdDocument);
		System.out.println(pdfPageText);
		
		pdDocument.close();
		
	}
	
	
}

//====================================After click page tittle not coming================