/**
 * 
 */
package com.appium.android.appium_android_framework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author choudhuryIqbal @version1.0.0 @
 */
public class TestBase {
	DesiredCapabilities cap = new DesiredCapabilities();
	AndroidDriver<AndroidElement> ad;
	String destDir;
	DateFormat dateFormat;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "UOIRAEVG6LFY6SLF");
		cap.setCapability(MobileCapabilityType.VERSION, "6.0");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		// set up remotewebdriver
		ad = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	public void hideKeyBoard() {
		ad.hideKeyboard();
	}
	@Test
	public void select() throws InterruptedException {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		ad.findElementByXPath("//android.widget.TextView[@text='Controls']").click();
		ad.findElementByXPath("//android.widget.TextView[@text='2. Dark Theme']").click();
		ad.findElementById("io.appium.android.apis:id/edit").sendKeys("Test");
		ad.hideKeyboard();
		ad.findElementById("android:id/text1").click();
		ad.findElementByXPath("//android.widget.TextView[@text='Mars']").click();
		//ad.findElementByXPath("//android.widget.CheckedTextView[@text='Mars']").click();//android.widget.CheckedTextView
		Thread.sleep(3000);
		//ad.findElementByXPath("//android.widget.CheckedTextView[@text='Mars']").click();
		ad.findElementsById("android:id/text1").get(3).click();
	}
	public void typeInText() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		ad.findElementByXPath("//android.widget.TextView[@text='Controls']").click();
		ad.findElementByXPath("//android.widget.TextView[@text='2. Dark Theme']").click();
		ad.findElementById("io.appium.android.apis:id/edit").sendKeys("Test");

		//2. Dark Theme
	}
	public void tapSwitchOff() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Switches\"));");
		ad.findElementByXPath("//android.widget.TextView[@text='Switches']").click();
		 String switchStatus1 = ad.findElementById("io.appium.android.apis:id/monitored_switch").getText();
		 if(switchStatus1.trim().equalsIgnoreCase("Monitored Switch OFF")) {
			 AndroidElement el1 = ad.findElementById("io.appium.android.apis:id/monitored_switch");
			 el1.tap(1,1);
		 }

	}
	/*public void AtuTestRecorder() {
		AtuTestRecorder recorder=new ATUTestRecorder(path),format.dateFormat,false);
		recorder.start;
		recorder.stop;
		
	}*/
	public void  orientation() {
		ad.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		System.out.println(ad.getOrientation());
		ad.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		
	}
	public void seekBarTest() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
//ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Spinner\"));");
		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Seek Bar\"));");
		ad.findElementByXPath("//android.widget.TextView[@text='Seek Bar']").click();
		WebElement seekBar=ad.findElementById("io.appium.android.apis:id/seek");
		int startX=seekBar.getLocation().getX();
		int yAxis=seekBar.getLocation().getY();
		int endX=seekBar.getSize().getWidth();
		int moveToXDirectionAt=(int) (endX*0.6);
		TouchAction act=new TouchAction(ad);
		act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
	}
	
	public void takeScreensShot() {
		//set folder name to store screenshot
		destDir ="screenshots";
		//Capture screenshots
		File scrFile=ad.getScreenshotAs(OutputType.FILE);
		//set date format to set is as screenshot file name
		dateFormat=new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
		//create folder uder project with nae
		new File(destDir).mkdirs();
		//set file name using current date time
		String destFile=dateFormat.format(new Date())+".png";
		try {
			FileUtils.copyFile(scrFile, new File(destDir+"/"+destFile));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	//@Test
	public void dateSet() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		ad.findElement(By.xpath("//android.widget.TextView[@text='Date Widgets']")).click();
		// 1. Dialog
		ad.findElement(By.xpath("//android.widget.TextView[@text='1. Dialog']")).click();
		ad.findElementById("io.appium.android.apis:id/pickDate").click();
		ad.findElement(By.xpath("//android.view.View[@text='15']")).click();
		ad.findElementById("android:id/prev").click();
		ad.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		ad.findElementById("io.appium.android.apis:id/pickTime").click();

		ad.findElement(By.xpath("//android.widget.Button[@text='Cancel']")).click();

	}

	/**
	 * Drag and drop
	 * 
	 */

	//@Test
	public void spinnerTest() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Spinner\"));");
		ad.findElement(By.xpath("//android.widget.TextView[@text='Spinner']")).click();
		ad.findElementById("io.appium.android.apis:id/spinner2").click();
		ad.findElement(By.xpath("//android.widget.CheckedTextView[@text='Pluto']")).click();

	}

	//@Test
	public void Test001() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		// Drag and Drop
		ad.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
		TouchAction action = new TouchAction(ad);

		action.longPress(ad.findElement(By.id("io.appium.android.apis:id/drag_dot_1")))
				.moveTo(ad.findElement(By.id("io.appium.android.apis:id/drag_dot_3"))).release().perform();

		// ad.findElementById("io.appium.android.apis:id/drag_dot_1")

		// System.out.println("Running empyt test");

	}

	//@Test
	public void Test002() {
		Dimension size = ad.manage().window().getSize();

		// drag and drop
		ad.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		// ad.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"WebView\"));");
		// ad.findElementByAndroidUIAutomator("new UiScrollable (new
		// UiSelector()).scrollIntoView(text(\"Tabs\"));");
		ad.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"));");
		ad.findElement(By.xpath("//android.widget.TextView[@text='Tabs']")).click();

		ad.findElement(By.xpath("//android.widget.TextView[@text='5. Scrollable']")).click();
		int startx = (int) (size.width * 0.70);
		int endx = (int) (size.width * 0.30);
		Point point = ad.findElement(By.xpath("//android.widget.TextView[@text='Tab 1']")).getLocation();
		int xlookingfor;
		System.out.println(xlookingfor = size.getHeight());
		int firstone;
		System.out.println(firstone = point.getX());
		int starty = point.getY() + 2;

		ad.swipe(startx, starty, endx, starty, 2000);
		// boolean run=true;

		for (int i = 0; i < 10; i++) {
			if ((ad.findElements(By.xpath("//android.widget.TextView[@text='Tab 18']")).size() == 0)) {

				ad.swipe(startx, starty, endx, starty, 2000);
			} else {
				ad.findElement(By.xpath("//android.widget.TextView[@text='Tab 18']")).click();
			}
		}

	}

	//@Test
	public void AlertDialog() {
		ad.findElement(By.xpath("//android.widget.TextView[@text='App']")).click();
		ad.findElement(By.xpath("//android.widget.TextView[@text='Alert Dialogs']")).click();
		ad.findElementsById("io.appium.android.apis:id/two_buttons").get(0).click();
		ad.findElementById("android:id/button1").click();

	}

	public void multiTouchAction() {
		TouchAction actions1 = new TouchAction(ad);
		TouchAction actions2 = new TouchAction(ad);
		TouchAction actions3 = new TouchAction(ad);
		MultiTouchAction maction = new MultiTouchAction(ad);
		maction.add(actions1).add(actions2).add(actions3).perform();

	}

	@AfterMethod
	public void closeAfterTest() {
		ad.quit();

	}

}
