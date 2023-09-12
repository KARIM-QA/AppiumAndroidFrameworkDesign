package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{
	
	//si on travaille aussi avec IOS application , changer AndroidDriver par AppiumDriver
	AndroidDriver androidDriver ; 
	
	public AndroidActions (AndroidDriver driver) {
		
		this.androidDriver =driver ;
	}
	
	 public void ScrollToText(String text) {
		 
		 androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));	
	 }
	
	  public void LongPress_Action(WebElement element) {
	    	 
	    	 ((JavascriptExecutor) androidDriver).executeScript("mobile: longClickGesture", ImmutableMap.of(
	    			    "elementId", ((RemoteWebElement) element).getId(),"duration",3000 ));
	       }
	    
	     public void ScrollToEnd_Action() {
	    	 
	    	 boolean canScrollMore ;
	 		do {
	 			 canScrollMore = (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
	 				    "left", 100, "top", 100, "width", 200, "height", 200,
	 				    "direction", "down",
	 				    "percent", 3.0
	 				));

	 		  }while(canScrollMore);
	     }
	     
	     public void Swipe_Action(WebElement element,String direction) {
	    	 ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
	    			   // "left", 100, "top", 100, "width", 200, "height", 200,

	    				"elementId", ((RemoteWebElement) element).getId(),"duration",3000 ,
	    			    "direction", direction,
	    			    "percent", 0.75 //this percentage is between 0 and 1
	    			));
	     }
	     

}
