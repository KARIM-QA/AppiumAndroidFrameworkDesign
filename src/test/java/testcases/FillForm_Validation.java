package testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import testUtils.AndroidBaseTest;

public class FillForm_Validation extends AndroidBaseTest {
	
	
	/*
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void preSetup()
	{
		//C:\Users\KARIM>adb shell dumpsys window  | find  "mCurrentFocus"
		//  mCurrentFocus=Window{7db6d5 u0 com.androidsample.generalstore/com.androidsample.generalstore.MainActivity}

		
		//Passer directement dans l’interface qui contient le checkbox wifi
				Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
				driver.startActivity(activity);
	}
*/
	@Test
	public void FillForm_PositiveTest() throws InterruptedException {
		
		// click on dropdown
		androidDriver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		// scroller vers un pays specifique
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"));	
		 //Select Country
		androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
         
		// set name
		androidDriver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Cindy Crowford");
		// masquer le clavier
		androidDriver.hideKeyboard();
		androidDriver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
	//shopping
		androidDriver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Thread.sleep(2000);
		
		//Verification Toast messages => dans notre cas un seul Toast message
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Cindy Crowford");
		
		Assert.assertTrue(androidDriver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);

	}
	
	/********************************************************************************************************/
	
	@Test 
	public void FillForm_NegativeTest() throws InterruptedException {
		
		// click on dropdown
		androidDriver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		// scroller vers un pays specifique
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"));	
		 //Select Country
		androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
         
		// set name
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Cindy Crowford");
		// masquer le clavier
		androidDriver.hideKeyboard();
		androidDriver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
	//shopping
		androidDriver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Thread.sleep(2000);
		
		//Verification Toast messages => dans notre cas un seul Toast message
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Cindy Crowford");
		
		String toastMessage=androidDriver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name1");

	}
	
	


}
