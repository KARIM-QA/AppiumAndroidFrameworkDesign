package testUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import utilities.AppiumUtils;

public class Listeners extends AppiumUtils implements ITestListener {
	AndroidDriver androidDriver ;
	ExtentTest extentTest;
	ExtentReports extentReports = ExtentReporterNG.getReporterObject();

	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReports.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test successfully Passed :) ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.fail(result.getThrowable());
		
		//appel du driver de la classe AndroidBaseTest.java
		try {
			androidDriver =(AndroidDriver)result.getTestClass().getRealClass().getField("androidDriver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		
		try {
			extentTest.addScreenCaptureFromPath(getScreenshotsPath(androidDriver, result.getMethod().getMethodName()), result.getMethod().getMethodName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
