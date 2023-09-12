package pageObjects.android;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.AndroidActions;

public class FormulairePage extends AndroidActions{
	
	AndroidDriver androidDriver ;
	
	public FormulairePage(AndroidDriver driver) {
		//call constructor in AndroidActions
		super(driver);
		
		this.androidDriver=driver ;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/********************************************Locators*************************************************/
	
	// spinner countries element
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement spinnerCountries ; 
	
	// enter name
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField ; 
	
	//set female gender 
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleGender ; 
	
	//set male gender 
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleGender ; 
	
	//click shop button
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton ; 
	
	/********************************************Actions Methods*************************************************/
	
	
	public void SetCountryName(String countryName) {
		
		spinnerCountries.click();
		ScrollToText(countryName);
		androidDriver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	
	public void SetNameField(String name ) {
		nameField.sendKeys(name);
		// masquer le clavier
		androidDriver.hideKeyboard();
	}
	
	public void SetGender(String gender) {
		if(gender.matches("male")) {
			maleGender.click();
		} else if(gender.matches("female") ) {
			femaleGender.click();
		}
	}
    //best practice to go to the next page ProductCataloguePage
	public ProductCataloguePage goToProductCataloguePage() {
		shopButton.click();
		return new ProductCataloguePage(androidDriver);
	}
	
	
}
