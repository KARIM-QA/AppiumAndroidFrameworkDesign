package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extentReports;
	
	public static ExtentReports getReporterObject() {
		
		String reportpath = System.getProperty("user.dir") + "\\src\\test\\java\\ExtentReports\\index.html";

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportpath);
		extentSparkReporter.config().setReportName("Automation Reports By Kimmich");
		extentSparkReporter.config().setDocumentTitle("Test Suite Results");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("SDET Engineer", "Kimmich");
		
		return extentReports ;
		
	}
	
	

}
