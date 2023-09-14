
/*
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@Test
public class ExtentReportDemo {

	ExtentReports extentReports;

	@BeforeTest
	public void config() {

		String reportpath = System.getProperty("user.dir") + "\\src\\test\\java\\ExtentReports\\index.html";

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportpath);
		extentSparkReporter.config().setReportName("Automation Reports By Kimmich");
		extentSparkReporter.config().setDocumentTitle("Test Suite Results");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("SDET Engineer", "Kimmich");

	}

	public void ExtentReportDemoTest() {

		ExtentTest extentTest =extentReports.createTest("ExtentReportDemoTest");

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.close();
		extentTest.addScreenCaptureFromBase64String("screenshot");

		extentReports.flush();
	}

}

*/
