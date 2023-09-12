package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import pageObjects.android.FormulairePage;
import utilities.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {

	
	public AndroidDriver driver;
	public AppiumDriverLocalService serviceBuilder;
	FormulairePage formulairePage;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {

		/*
		  //invocation de Appium server d'une facon automatique
		serviceBuilder =new AppiumServiceBuilder()
				.withAppiumJS(new File("C://Users//KARIM//AppData//Roaming//npm//node_modules//appium//build//lib//main.js" ))
				.withIPAddress("127.0.0.1")
				.usingPort(4723).build();
				
				
		 serviceBuilder.start();
		 
         */
		UiAutomator2Options automatorOptions = new UiAutomator2Options();
		automatorOptions.setDeviceName("KimsEmulator");
		// to run in real device android
		// automatorOptions.setDeviceName("Android Device");
		automatorOptions.setChromedriverExecutable(
				"K:\\Appium\\Appium_Eclipse_Project\\AppiumAndroidFrameworkDesign\\src\\test\\java\\drivers\\chromedriver.exe");
		automatorOptions.setApp(
				"K:\\Appium\\Appium_Eclipse_Project\\AppiumAndroidFrameworkDesign\\src\\test\\java\\resources\\General-Store.apk");

		// http://127.0.0.1:4723 => Appium server
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), automatorOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		formulairePage = new FormulairePage(driver);
	}

	@AfterClass
	public void teardownAppium() {

		driver.quit();
		//serviceBuilder.stop();
	}

}
