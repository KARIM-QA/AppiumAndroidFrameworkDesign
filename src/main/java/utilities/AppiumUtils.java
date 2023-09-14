package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	public AppiumDriverLocalService serviceBuilder;

	// invocation de Appium server d'une facon automatique
	public AppiumDriverLocalService startAppiumServer(String ipAdress, int port) {

		serviceBuilder = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\KARIM\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAdress).usingPort(port).build();

		serviceBuilder.start();
		return serviceBuilder;

	}
	
    /************************************************************************************************************************/
	public Double getFormattedPrice(String amountWithDollar) {
		Double price = Double.parseDouble(amountWithDollar.substring(1));
		return price;
	}
	
	/************************************************************************************************************************/
	public void waitElementToBeDisplayed(AndroidDriver androidDriver, WebElement element, String attribute,
			String value) {
		WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains((element), attribute, value));
	}
	
	/************************************************************************************************************************/
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// parse Json file content => Json String
		String JsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// Convert Json String =>HashMap
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
	
	
	public String getScreenshotsPath(AndroidDriver androidDriver ,String testcaseName ) throws IOException {
		
		File source =androidDriver.getScreenshotAs(OutputType.FILE);
		
		String destinationFile=  System.getProperty("user.dir") + "\\src\\test\\java\\ExtentReports"+testcaseName +".png"  ;
		
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile ;
		
		
	}

}
