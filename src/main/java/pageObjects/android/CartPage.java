package pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver androidDriver;

	public CartPage(AndroidDriver driver) {

		// call constructor in AndroidActions
		super(driver);

		this.androidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	/********************************************
	 * Locators
	 *******************************************************/

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> products_prices_displayed;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement DisplayedTotalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms_conditions;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;

	/********************************************
	 * Actions Methods
	 *************************************************/

	public List<WebElement> getProduct_prices_displayed() {

		return products_prices_displayed;
	}

	public Double getCalculatedSumProduct() {

		int count_products_prices = products_prices_displayed.size();
		System.out.println("count_products_prices is :" + count_products_prices);
		double totalSumPrices = 0;

		for (int i = 0; i < count_products_prices; i++) {

			String product_price = products_prices_displayed.get(i).getText();

			Double price = getFormattedPrice(product_price);

			totalSumPrices = totalSumPrices + price;

			//System.out.println("Sum Calculated prices is : $" + totalSumPrices);
		}
		return totalSumPrices;
	}

	public Double getDisplayedSumProductInEmulator() {

		String DisplayedTotalSum = DisplayedTotalAmount.getText();

		return getFormattedPrice(DisplayedTotalSum);

	}

	public void acceptTermsCondtions() {

		LongPress_Action(terms_conditions);
		acceptButton.click();
	}

	public void submitOrder() throws InterruptedException {
		checkbox.click();
		proceedButton.click();
		Thread.sleep(5000);
	}

}
