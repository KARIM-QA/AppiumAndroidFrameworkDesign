package testcases;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.android.CartPage;
import pageObjects.android.ProductCataloguePage;
import testUtils.AndroidBaseTest;
import utilities.AndroidActions;
import utilities.AppiumUtils;

public class Hybrid_Validate_Total_Amount_Steps extends AndroidBaseTest {

	
	

	@Test(dataProvider="getData",groups={"smoke"})
	public void Validate_Total_AmountTest(HashMap<String,String> inputData) throws InterruptedException {
		
		/**************************First page Formulaire to fill************************************************************/
		
		 AndroidActions androidActions = new AndroidActions(androidDriver);
		 AppiumUtils appiumUtils = new AppiumUtils();
		 
		 //on peut initier  l'objet formulairePage dans AndroidBaseTest car c'est la première page à executer (optimisation)
		 //FormulairePage formulairePage = new FormulairePage(driver) ;
		 formulairePage.SetCountryName(inputData.get("country"));
		 formulairePage.SetNameField(inputData.get("name"));
		 Thread.sleep(1000);
		 formulairePage.SetGender(inputData.get("gender"));
		 
		
		 ProductCataloguePage  productCataloguePage = formulairePage.goToProductCataloguePage();
		 
		 /**************************Second page Product Catalogue************************************************************/
		 
	    //scroll  to a specific product 
		 androidActions.ScrollToText("Converse All Star");
		 
		//Après le scroll , une liste des produits s'affiche , on voulait ajouter dans panier les produits affichés dans la meme view que Converse All Star
		 //on a utilisé get(0) dans les deux lignes parce que le bouton add to cart change après un click (add to cart =>Added To cart )
		 
		 productCataloguePage.addItemToCartByIndex(0);//first add to cart 
		 
		 productCataloguePage.addItemToCartByIndex(0); // second add to cart
		 
		CartPage cartPage= productCataloguePage.goToCartPage();
	
		 /**************************Third page Cart page******************************************************************/
	
	    //explicit wait until Cart page is loaded totally
	     appiumUtils.waitElementToBeDisplayed(androidDriver,androidDriver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart") ;
	     
	
	     double calculatedTotalSum =cartPage.getCalculatedSumProduct();
	     System.out.println("calculatedTotalSum is :$" + calculatedTotalSum);
	
	     double displayedTotalSum =cartPage.getDisplayedSumProductInEmulator();
	     System.out.println("displayedTotalSum is :$" + displayedTotalSum);
	     
		Assert.assertEquals(calculatedTotalSum, displayedTotalSum);
		
		 cartPage.acceptTermsCondtions() ;
		
		 cartPage.submitOrder();
		
		
		/******************************************Hybrid Automation*******************************************************************/
		// list collection of contexts
		Set <String> contexts= androidDriver.getContextHandles();
		
	   // display contexts using loop
		for(String contextName :contexts) {
			
			System.out.println("Context name is :" + contextName);
		}
		
		//switch vers web application, be careful context name like  Webview can be different depending on the developpers specifications
		androidDriver.context("WEBVIEW_com.androidsample.generalstore");
		 // redirection vers google.com
		androidDriver.findElement(By.name("q")).sendKeys("Kimmich Foundation");
		androidDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		 //retour au contexte native 
		androidDriver.context("NATIVE_APP");
		androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
		 
		
	}
	
	  @DataProvider
      public Object[][] getData() throws IOException {
		  
		  List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\eCommerce.json");
		  return new Object [][] {
			  {data.get(0)}, {data.get(1)}, {data.get(2)},
			 
		          } ;
      	
      }

}

      
