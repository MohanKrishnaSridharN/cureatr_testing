package com.cureatr.testpackage;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class gridtest {

	public static RemoteWebDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		//System.setProperty("webdriver.ie.driver","/Users/macmini/Cureatr/CureatrAutomationWorkSpace/CureatrApp/External Library Files/IEDriverServer_Win32_2.52.0/IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/External Library Files/IEDriverServer_Win32_2.52.0/IEDriverServer.exe");
		DesiredCapabilities cap = new DesiredCapabilities().internetExplorer();
		cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability("nativeEvents",false);
		
		cap.setPlatform(Platform.WINDOWS);
		cap.setBrowserName("internet explorer");
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
