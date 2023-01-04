package tests.emailSent;

import org.apache.commons.mail.DefaultAuthenticator;

import org.apache.commons.mail.Email;
//import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;
import org.apache.commons.mail.EmailException;



//import org.apache.commons.mail.*;
// import javax.naming.*;
// import java.sql.*;
// import java.io.*;
// import java.lang.*;
// import java.text.*;
// import java.util.*;

@Test
public class EmailSent {

	
	//EmailAttachment attachment1 = new EmailAttachment();
	
	//EmailAttachment attachment=new EmailAttachment();
 
	
	Email email1 = new SimpleEmail();
	email1.setHostName("smtp.gmail.com");
	email.setSmtpPort("465");
	email.setSSLOnConnect(true);
	email.setAuthenticator(new DefaultAuthenticator("vikrant.bingi@capita.com", "Admin$2145"));
	email.setSSLOnConnect(true);
	email.setFrom("user@gmail.com");
	email.setSubject("TestMail");
	email.setMsg("This is a test mail ... :-)");
	email.addTo("bingivs@gmail");
	email.send();
	
*/
	/*
	
	email.setHostName("cias-server-smtp-uk");
	email.setSmtpPort(25);
	email.setAuthenticator(new DefaultAuthenticator("P50096390@capita.co.uk", "Admin$2145"));
	email.setSSLOnConnect(true);
	email.setFrom("user@gmail.com");
	email.setSubject("TestMail");
	email.setMsg("This is a test mail ... :-)");
	email.addTo("foo@bar.com");
	email.send();
		
	*/
	
/*
	  // Create the attachment
	  EmailAttachment attachment1 = new EmailAttachment();
	
	 // attachment.setPath(System.getProperty("user.dir")+"//test-output//emailable-report.html");
	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
	  attachment.setDescription("PO Manager Test Run Report");

	  // Create the email message
	  MultiPartEmail email = new MultiPartEmail();
	  email.setHostName("cias-server-smtp-uk");
	  email.setSmtpPort(25);
		//email.setAuthenticator(new DefaultAuthenticator("P50044121@capita.co.uk", "Email Password"));
	  try {
		email.addTo("pradnya.patil2@capita.com", "Pradnya Patil");
		//email.addTo("Amit.Verma@capita.com", "Amit Verma");
		//email.addTo("Amit.Verma@capita.com", "Sanjiv Kumar");
	} catch (EmailException e) {
		
		e.printStackTrace();
	}
	  try {
		email.setFrom("pradnya.patil2@capita.com", "Me");
	} catch (EmailException e1) {
		e.printStackTrace();
	}
	  email.setSubject("Automation Test Execution Report");
	  try {
		email.setMsg("Hello, Please find the attached test run report for your reference");
	} catch (EmailException e2) {
		e.printStackTrace();
	}

	  // add the attachment
	  try {
		email.attach(attachment);
	} catch (EmailException e3) {
		e.printStackTrace();
	}

	  // send the email
	  try {
		email.send();
		System.out.println("Email Sent Successfully");
	} catch (EmailException e4) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	
	
	

	
	
	
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

