package pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.AndroidActions;

public class ProductCataloguePage extends AndroidActions {
	AndroidDriver androidDriver;

	public ProductCataloguePage(AndroidDriver driver) {

		// call constructor in AndroidActions
		super(driver);

		this.androidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	/******************************************** Locators *************************************************/
	
	// add to cart element
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	//  cart button
		@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
		private WebElement cartButton;
	
	/********************************************Actions Methods*************************************************/
	
	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException {
		cartButton.click();
		Thread.sleep(3000);
		return new CartPage(androidDriver);
	}

}
