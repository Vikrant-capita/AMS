package testComponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass {

	static ExtentReports extent;
	public static ExtentReports config() {
		String path = System.getProperty("user.dir")+"\\ExtentReports\\Report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("AMS Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vikrant");
		
		return extent;
		
	}
}
