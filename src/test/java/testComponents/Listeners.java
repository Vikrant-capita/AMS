package testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import testComponents.ExtentReportClass;
import testComponents.BaseTestClass;

public class Listeners extends BaseTestClass implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReportClass.config();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); 
	public void onTestStart(ITestResult result) {

		// TODO Auto-generated method stub
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test is passed : "+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test failed :  "+result.getName());
		extentTest.get().fail(result.getThrowable());
		WebDriver driver=null;
		String methodname = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			System.out.println("driver : "+driver);
		} catch (Exception e)
		{
			System.out.println("try error  : "+e.getMessage());
		}
		try {
			//we attached screenshot to report extent
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(methodname,driver), result.getMethod().getMethodName());
			System.out.println("ScreenShot Captured");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error : "+e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	
	
	
}
