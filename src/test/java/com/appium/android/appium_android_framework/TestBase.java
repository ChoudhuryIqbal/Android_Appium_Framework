/**
 * 
 */
package com.appium.android.appium_android_framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	
	@Test
	public void Test001() {
		System.out.println("Running empyt test");
		
	}
	@AfterMethod
	public void closeAfterTest() {
		ad.quit();
		
	}

}
