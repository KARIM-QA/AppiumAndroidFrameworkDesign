package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

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

	
	public AndroidDriver androidDriver;
	public AppiumDriverLocalService serviceBuilder;
	protected FormulairePage formulairePage;

	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException {
		
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties") ;
		properties.load(fis);
		
		String ipAdress =properties.getProperty("ipAdress");
		//properties file accepte que string , on doit convertir integer port =>String
		String port =properties.getProperty("port") ;
		
		//serviceBuilder = startAppiumServer(ipAdress,Integer.parseInt(port));

		UiAutomator2Options automatorOptions = new UiAutomator2Options();
		automatorOptions.setDeviceName(properties.getProperty("AndroidDeviceName"));
		// to run in real device android
		// automatorOptions.setDeviceName("Android Device");
		automatorOptions.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\java\\drivers\\chromedriver.exe");
				
		automatorOptions.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
				

		// http://127.0.0.1:4723 => Appium server
		//on peut remplacer http://127.0.0.1:4723 par serviceBuilder.getUrl()  driver = new AndroidDriver(serviceBuilder.getUrl()), automatorOptions);
		androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), automatorOptions);
		androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		formulairePage = new FormulairePage(androidDriver);
	}

	@AfterClass(alwaysRun=true)
	public void teardownAppium() {

		androidDriver.quit();
		//serviceBuilder.stop();
	}

}
