/**
 * 
 */
package com.appium.android.appium_android_framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author choudhuryIqbal
 *@version1.0.0
 *@
 */
public class TestBase {
	DesiredCapabilities cap=new DesiredCapabilities();
	AndroidDriver<AndroidElement> ad;
	
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,"UOIRAEVG6LFY6SLF");
		cap.setCapability(MobileCapabilityType.VERSION,"6.0");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
		cap.setCapability("appPackage","io.appium.android.apis");
		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		
		//set up remotewebdriver
		ad= new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		ad.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	/**
	 * Drag and drop
	 * 
	 */
	@Test
	public void Test001() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
	//Drag and Drop
		ad.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
		TouchAction action=new TouchAction(ad);
		
		action.longPress(ad.findElement(By.id("io.appium.android.apis:id/drag_dot_1"))).moveTo(ad.findElement(By.id("io.appium.android.apis:id/drag_dot_3"))).release().perform();
		
	//	ad.findElementById("io.appium.android.apis:id/drag_dot_1")
	
		//System.out.println("Running empyt test");
		
	}
	
	@Test
	public void Test002() {
		Dimension size=ad.manage().window().getSize();
		
		//drag and drop
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		//		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		//ad.findElementByAndroidUIAutomator("new UiScrollable (new UiSelector()).scrollIntoView(text(\"Tabs\"));");
		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"));");
		ad.findElement(By.xpath("//android.widget.TextView[@text='Tabs']")).click();

		ad.findElement(By.xpath("//android.widget.TextView[@text='5. Scrollable']")).click();
		int startx=(int) (size.width*0.70);
		int endx=(int) (size.width*0.30);
		Point point = ad.findElement(By.xpath("//android.widget.TextView[@text='Tab 1']")).getLocation();
	int xlookingfor;
	System.out.println( xlookingfor=size.getHeight());
	int firstone;
	System.out.println( firstone=point.getX());
		int starty=point.getY()+2;
		
		ad.swipe(startx, starty, endx,starty, 2000);
		//boolean run=true;
		
		
		for(int i=0;i<10;i++) {
			if((ad.findElements(By.xpath("//android.widget.TextView[@text='Tab 18']")).size()==0)) {
				
				
				 ad.swipe(startx, starty, endx,starty, 2000);
				}
			else {
				ad.findElement(By.xpath("//android.widget.TextView[@text='Tab 18']")).click();
			}
		}
		 
		
	}
	@AfterMethod
	public void closeAfterTest() {
		ad.quit();
		
	}

}
