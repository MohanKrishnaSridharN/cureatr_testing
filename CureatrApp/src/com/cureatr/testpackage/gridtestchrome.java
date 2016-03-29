package com.cureatr.testpackage;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class gridtestchrome {

	public static RemoteWebDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap = new DesiredCapabilities().chrome();
		//cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
		//cap.setCapability("ignoreZoomSetting", true);
		//cap.setCapability("nativeEvents",false);
		
		cap.setPlatform(Platform.WINDOWS);
		cap.setBrowserName("chrome");
		cap.setVersion("10.0");
		
		driver = new RemoteWebDriver(new URL("http://192.168.73.125:5557/wd/hub"),cap);
		//WebDriver driver = new InternetExplorerDriver(cap);
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium Hq");
		driver.findElement(By.name("btnG")).click();
		//driver.navigate().to("http://www.google.com");
		//driver.findElementByName("q").sendKeys("selenium hq");
		//driver.findElementByName("btnG").click();
		Thread.sleep(10000);
		driver.close();
	}

}
