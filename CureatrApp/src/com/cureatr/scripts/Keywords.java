package com.cureatr.scripts;
//updating xpath to s


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {
	private  WebDriver driver;
	private  WebDriver driver1;
	//private RemoteWebDriver driver1;
	private WebDriver driver2;
	public String Result1="";
	public String Result2="";
	public String Result3="";
	public String Result4="";
	private String TestBrowser;
	private String FolderName;
	private String SubFolderName;
	private String BrowserName;
	//test
	public void setdriver1(String TestBrowser, String FolderName, String SubFolderName) {
		this.TestBrowser = TestBrowser;
		this.FolderName=FolderName;
		this.SubFolderName=SubFolderName;
	}
	public String getdriver() {
		return TestBrowser;
	}
	public Keywords() {
		super();
	}
	
	//*****Launch Web Browser**********
	//METHOD WILL LAUNCH CHROME/IE/FF BROWSER 
		public String LaunchWebBrowser(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
			try{
				if(TestBrowser.equals("IE")){
					//Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
					//Process p =Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
					//p.waitFor();
					//Thread.sleep(10000);
					/*Web driver Settings
					System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/External Library Files/IEDriverServer_Win32_2.52.0/IEDriverServer.exe");
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
					caps.setCapability("ignoreZoomSetting", true);
					caps.setCapability("nativeEvents",false);
					driver1 = new InternetExplorerDriver(caps);
					*/
					//System.setProperty("webdriver.ie.driver","/Users/macmini/Cureatr/CureatrAutomationWorkSpace/CureatrApp/External Library Files/IEDriverServer_Win32_2.52.0/IEDriverServer.exe");
					DesiredCapabilities cap = new DesiredCapabilities().internetExplorer();
					cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
					cap.setCapability("ignoreZoomSetting", true);
					cap.setCapability("nativeEvents",false);
					cap.setPlatform(Platform.WINDOWS);
					cap.setBrowserName("internet explorer");
					cap.setVersion("10.0");
					
					driver1 = new RemoteWebDriver(new URL("http://192.168.73.125:5557/wd/hub"),cap);
					
					//driver1.manage().deleteAllCookies();
					driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver1.manage().window().maximize();
					return Constants.PASS;
				}else if(TestBrowser.equals("FF")){
					//Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
					driver1 = new FirefoxDriver();
					System.out.println("Successfully Opened FF");
					//driver1.manage().deleteAllCookies();
					driver1.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					driver1.manage().window().maximize();
					return Constants.PASS;
				}else if(TestBrowser.equals("Chrome")){
					//Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
					//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/External Library Files/chromedriver_mac32/chromedriver");
					driver1 =new ChromeDriver();
					driver1.manage().deleteAllCookies();
					driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					return Constants.PASS;
				}
				}catch(Exception e){
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
					}
			ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			return Constants.FAIL;
		}
		
	//*****Launch 3nd Web Browser**********
	//METHOD WILL LAUNCH 3nd CHROME/IE/FF BROWSER 
			public String LaunchWebBrowser3(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
				try{
					if(TestBrowser.equals("FF")){
						Process p =Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
						p.waitFor();
						System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/External Library Files/IEDriverServer_Win32_2.52.0/IEDriverServer.exe");
						DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
						caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
						driver2 = new InternetExplorerDriver(caps);
						
						driver2.manage().deleteAllCookies();
						driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						driver2.manage().window().maximize();
						return Constants.PASS;
					}else if(TestBrowser.equals("Chrome")){
						driver2 = new FirefoxDriver();
						System.out.println("Successfully Opened FF");
						driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						return Constants.PASS;
					}else if(TestBrowser.equals("IE")){
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/External Library Files/chromedriver_win32/chromedriver.exe");
						driver2 =new ChromeDriver();
						driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						return Constants.PASS;
					}
					}catch(Exception e){
						e.printStackTrace();
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						return Constants.FAIL;
						}
				ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
				return Constants.FAIL;
			}
			//*****Launch 3nd Web Browser**********
			//METHOD WILL LAUNCH 3nd CHROME/IE/FF BROWSER 
			public String LaunchWebBrowser2(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
			try{
					if(TestBrowser.equals("FF") || TestBrowser.equals("Chrome")){
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/External Library Files/chromedriver_mac32/chromedriver");
					driver2 =new ChromeDriver();
					driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						return Constants.PASS;
					} else if(TestBrowser.equals("IE")){
						//driver2 = new FirefoxDriver();
						DesiredCapabilities cap = new DesiredCapabilities().chrome();
						cap.setPlatform(Platform.WINDOWS);
						cap.setBrowserName("chrome");
						cap.setVersion("10.0");
						driver2 = new RemoteWebDriver(new URL("http://192.168.73.125:5557/wd/hub"),cap);
						
						System.out.println("Successfully Opened Chrome");
						driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						return Constants.PASS;
					}
				}catch(Exception e){
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
					}
			ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			return Constants.FAIL;
		}
	//*****CloseWebApp Method*********
	//METHOD WILL CLOSE WEB APPLICATION
		public String CloseWebApp(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
		//APP_LOGS.debug("SUCCESSFULLY CLOSED WEB APPLICATION");
		try{
			if(browser.equals("browser1")){
				driver=driver1;
			}else if(browser.equals("browser2")){
				driver=driver2;
			}
			/*
			if(TestBrowser.equals("IE")){
				Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			}
			driver.manage().deleteAllCookies();
			*/
			driver.close();
			driver.quit();
		 		return Constants.PASS;	
			}catch (Exception e){
				ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
				e.printStackTrace();
				return Constants.FAIL;
			}
		}
	//*****OpenWebApp Method*********
	//METHOD WILL OPEN CUREATR WEB URL
			public String OpenWebApp(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
			//APP_LOGS.debug("SUCCESSFULLY OPENED CUREATR WEB APPLICATION");
			try{
				if(browser.equals("browser1")){
					driver=driver1;
				}else if(browser.equals("browser2")){
					driver=driver2;
				}
					driver.get(DriverScript.CONFIG.getProperty(target));
					PageRefresh(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					return Constants.PASS;	
				}catch (Exception e){
					e.printStackTrace();
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					return Constants.FAIL;
				}
			}
	//*****OpenWebApp Method*********
	//METHOD WILL OPEN CUREATR WEB URL
					public String OpenWebApp2(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("SUCCESSFULLY OPENED CUREATR WEB APPLICATION");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						driver1.get(DriverScript.CONFIG.getProperty(target));
							return Constants.PASS;
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
			
	//*****VerifyTitle Method**********
	//METHOD WILL GET WEB APP TITLE & VALIDATE WITH EXPECTED TITLE
	public String verifyTitle(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
			//APP_LOGS.debug("Get Title OF Opened Web Application");
			try{
				if(browser.equals("browser1")){
					driver=driver1;
				}else if(browser.equals("browser2")){
					driver=driver2;
				}
				String ActTitle=driver.getTitle();
				if(data.equals(ActTitle)){
					return Constants.PASS;
				}else{
					System.out.println(driver1);
					System.out.println("***************Calling Screen shot capture method***************");
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					return Constants.FAIL;	
				}
				}catch (Exception e){
					System.out.println("***************Calling Screen shot capture method @ Exception***************");
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
				}
				
			}

	//*****verifyText Method**********
	//METHOD WILL GET THE TARGETED TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
		public String verifyText(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Get Title OF Opened Web Application");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					String ActText=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getText();
					System.out.println("******************************************ActText=="+ActText);
					System.out.println("******************************************ExpText=="+data);
					
					if(data.equals(ActText)){
						return Constants.PASS;
					}else{
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						return Constants.FAIL;	
					}
					}catch (Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
					
				}
	//*****verifyTextContains Method**********
	//METHOD WILL GET THE TARGETED TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
		public String verifyTextContains(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Get Title OF Opened Web Application");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					String ActText=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getText();
					System.out.println("******************************************ActText=="+ActText);
					System.out.println("******************************************ExpText=="+data);
					
					if(ActText.contains(data)){
						return Constants.PASS;
					}else{
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						return Constants.FAIL;	
					}
					}catch (Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
					
					}
		//*****verifyReadRecipt Method**********
		//METHOD WILL GET THE TARGETED TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
			public String verifyReadRecipt(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Get Title OF Opened Web Application");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						String ActText=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getText();
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm a");
						Calendar cal1 = Calendar.getInstance();
						String PresentTime= dateFormat1.format(cal1.getTime());
						PresentTime=PresentTime.replaceFirst("^0+(?!$)", "");
						data=data+" "+PresentTime;
						data=data.toLowerCase();
						ActText=ActText.toLowerCase();
						System.out.println("******************************************ActText=="+ActText);
						System.out.println("******************************************ExpText=="+data);
						
						if(data.equals(ActText)){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;	
						}
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
						
					}
				//*****verifyMsgBodyPart Method**********
		//METHOD WILL GET THE TARGETED TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
			public String verifyMsgBodyPart(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Get Title OF Opened Web Application");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						String Status="";
						for(int i=1; i<=60; i++){
							String ActText=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getText();
							System.out.println("******************************************ActText=="+ActText);
							System.out.println("******************************************ExpText=="+data);
							if(data.equals(ActText)){
								Status="PASS";
								break;
							}else{
								Status="FAIL";
								Thread.sleep(1000);	
							}
						}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;	
						}
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
						
					}
		
		//*****verifyWaterMark Method**********
		//METHOD WILL GET THE TARGETED WATERMARK TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
			public String verifyWaterMark(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Get Title OF Opened Web Application");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						String ActText=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getAttribute("placeholder");
						System.out.println("******************************************ActText=="+ActText);
						System.out.println("******************************************ExpText=="+data);
						
						if(data.equals(ActText)){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;	
						}
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
						
					}
		
		//*****verifyTextcss Method**********
		//METHOD WILL GET THE TARGETED TEXT FROM WEB APP & VALIDATE WITH EXPECTED TEXT
			public String verifyTextcss(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Get Title OF Opened Web Application");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						System.out.println("****************************Expected Text******************"+data);
						String ActText=driver.findElement(By.cssSelector(DriverScript.CONFIG.getProperty(target))).getText();
						System.out.println("****************************Act Text******************"+ActText);
						if(data.equals(ActText)){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;	
						}
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
						
					}
	//*****SearchInstitution Method**********
	//METHOD WILL SEARCH INSTITUTION FROM WEB APP & VALIDATE RESULTS WHICH ARE MATCHING TO SEARCH CRITERIA OR NOT
		public String SearchInstitution(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
		//APP_LOGS.debug("Select Desired Institution");
		String Status="";
		String ActOrg="";
		try{
			if(browser.equals("browser1")){
				driver=driver1;
			}else if(browser.equals("browser2")){
				driver=driver2;
			}
			String SearchMsg=data;
			driver.findElement(By.id("institution-input")).sendKeys(SearchMsg);
			java.util.List list=driver.findElements(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li"));
			for(int k=1;k<=list.size();k++){
			ActOrg=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li["+k+"]/div/div")).getText();
			if(ActOrg.contains(SearchMsg)){
							Status="PASS";
							
						}else{
							Status="FAIL";
						}
					}
					if(Status.equals("PASS")){
						return Constants.PASS;
					}else{
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						return Constants.FAIL;
					}
				}catch (Exception e){
					e.printStackTrace();
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					return Constants.FAIL;
				}
			}
	
		//*****DeleteQuickMsg Method**********
		//METHOD WILL SEARCH MATCHING QUICK MESSAGES AND DELETES THEM
			public String DeleteQuickMsg(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
			//APP_LOGS.debug("Select Desired Institution");
			String Status="";
			String ActOrg="&";
			try{
				if(browser.equals("browser1")){
					driver=driver1;
				}else if(browser.equals("browser2")){
					driver=driver2;
				}
				Thread.sleep(3000);
				String SearchMsg=data;
				WebDriverWait wait = new WebDriverWait(driver, 20);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[2]/li")));
				java.util.List list=driver.findElements(By.xpath("//ul[2]/li"));
				for(int k=1;k<=list.size();k++){
				WebDriverWait wait3 = new WebDriverWait(driver, 20);
				//WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")));
				wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")));
				String ActOrg1=driver.findElement(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")).getText();
				ActOrg=ActOrg+ActOrg1+"&";
				}
				String[] QuickMsgs=ActOrg.split("&");
				for(int qk=QuickMsgs.length-1; qk>0; qk--){
					if(QuickMsgs[qk].equals(data)){
						driver.findElement(By.xpath("//ul[2]/li["+qk+"]/div[2]/a")).click();
						driver.findElement(By.xpath("//a[contains(text(),'Yes, remove')]")).click();
						Thread.sleep(3000);
						Status="PASS";
					}
				}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
					}catch (Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
			}
			//*****DeletAllQuickMsgs Method**********
			//METHOD WILL SEARCH ALL QUICK MESSAGES AND DELETES THEM
				public String DeletAllQuickMsgs(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Select Desired Institution");
				String Status="";
				String ActOrg="&";
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					Thread.sleep(3000);
					String SearchMsg=data;
					WebDriverWait wait = new WebDriverWait(driver, 20);
					WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[2]/li")));
					java.util.List list=driver.findElements(By.xpath("//ul[2]/li"));
					for(int k=1;k<=list.size();k++){
					WebDriverWait wait3 = new WebDriverWait(driver, 20);
					WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")));
					wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")));
					String ActOrg1=driver.findElement(By.xpath("//ul[2]/li["+k+"]/div[2]/div[2]/span")).getText();
					ActOrg=ActOrg+ActOrg1+"&";
					}
					String[] QuickMsgs=ActOrg.split("&");
					for(int qk=QuickMsgs.length-1; qk>0; qk--){
							driver.findElement(By.xpath("//ul[2]/li["+qk+"]/div[2]/a")).click();
							driver.findElement(By.xpath("//a[contains(text(),'Yes, remove')]")).click();
							Thread.sleep(3000);
							Status="PASS";
					}
							if(Status.equals("PASS")){
								return Constants.PASS;
							}else{
								ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
								return Constants.FAIL;
							}
						}catch (Exception e){
							e.printStackTrace();
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
				}
			//*****selectInstitution Method**********
		//METHOD WILL SELECT INSTITUTION FROM DROPDOWN LIST & VALIDATES BEFORE SELECTING THE INSTITUTION WITH DROP LIST INSTITUTIONS
		public String selectInstitution(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
		//APP_LOGS.debug("Select Desired Institution");
		String ActOrg="";
		String ExpOrg="";
		try{
			if(browser.equals("browser1")){
				driver=driver1;
			}else if(browser.equals("browser2")){
				driver=driver2;
			}
	java.util.List list=driver.findElements(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li"));
			ExpOrg=data;
				for(int k=1;k<=list.size();k++){
				ActOrg=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li["+k+"]/div/div")).getText();
					if(ExpOrg.equals(ActOrg)){
					driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li["+k+"]/div/div")).click();
					break;
					}
				}
				if(ExpOrg.equals(ActOrg)){
					return Constants.PASS;
				}else{
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					return Constants.FAIL;
				}
			}catch (Exception e){
				ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
				e.printStackTrace();
				return Constants.FAIL;
			}
			}
		//*****DeleteOneQuickMsg Method**********
		//METHOD WILL SELECT INSTITUTION FROM DROPDOWN LIST & VALIDATES BEFORE SELECTING THE INSTITUTION WITH DROP LIST INSTITUTIONS
				public String DeleteOneQuickMsg(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Select Desired Institution");
				String ActOrg="";
				String ExpOrg="";
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					java.util.List list=driver.findElements(By.xpath("html/body/div[6]/div/div[1]/div/div[2]/ul[2]/li"));
					ExpOrg=data;
						for(int k=1;k<=list.size();k++){
						ActOrg=driver.findElement(By.xpath("html/body/div[6]/div/div[1]/div/div[2]/ul[2]/li[["+k+"]/div[2]/a")).getText();
							if(ExpOrg.equals(ActOrg)){
							driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li["+k+"]/div/div")).click();
							break;
							}
						}
						if(ExpOrg.equals(ActOrg)){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
					}catch (Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
					}
		//*****verifyInstitutions Method**********
		//METHOD WILL VLAIDATE INSTITUTION FROM DROPDOWN LIST & VALIDATES THE INSTITUTION WITH EXPECTED LIST INSTITUTIONS
				public String verifyInstitutions(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Select Desired Institution");
				String ActOrg="";
				String ExpOrg="";
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					java.util.List list=driver.findElements(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li"));
					for(int k=1;k<=list.size();k++){
						String ActOrg1=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[2]/ul/li["+k+"]/div/div")).getText();
						ActOrg=ActOrg+ActOrg1+"&";
						}
						System.out.println("*****************ActOrg"+ActOrg);
						System.out.println("*****************ExpOrg"+data);
						if(ActOrg.equals(data)){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
					}catch (Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}
				
		
		//*****TYPE Method**********
		//METHOD WILL TYPE TEXT IN ALL TEXT BOX 
		public String Type(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
			//APP_LOGS.debug("Input The Value In Test Box");
			try{
				if(browser.equals("browser1")){
					driver=driver1;
				}else if(browser.equals("browser2")){
					driver=driver2;
				}
				
				driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).sendKeys(data);
				return Constants.PASS;
			}catch (Exception e){
				ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
				e.printStackTrace();
				return Constants.FAIL;
			}
		}

		//*****FileUpload Method**********
		//METHOD WILL UPLOAD FILE
		public String FileUpload(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
			//APP_LOGS.debug("Input The Value In Test Box");
			try{
				if(browser.equals("browser1")){
					driver=driver1;
				}else if(browser.equals("browser2")){
					driver=driver2;
				}
				String UploadFilePath="";
			    if(TestBrowser.equals("FF")){
			    	  BrowserName = "FFTestResults_";
			    	  UploadFilePath="/Users/macmini/Cureatr/CureatrAutomationWorkSpace/CureatrApp/External Library Files/upload/"+data;
				  }else if(TestBrowser.equals("Chrome")){
					  BrowserName = "ChromeTestResults_";
					  UploadFilePath="/Users/macmini/Cureatr/CureatrAutomationWorkSpace/CureatrApp/External Library Files/upload/"+data;
				}else if (TestBrowser.equals("IE")){
					  BrowserName = "IETestResults_";
					  UploadFilePath="C:\\SeleniumSoftWares\\upload\\"+data;
					}
				System.out.println("**********File Path="+UploadFilePath);
				WebElement fileInput = driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target)));
				fileInput.sendKeys(UploadFilePath);
				return Constants.PASS;
			}catch (Exception e){
				ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
				e.printStackTrace();
				return Constants.FAIL;
			}
		}

		//*****WaitForSendBtn Method**********	
		//METHOD WILL WAIT FOR SEND BUTTON TO BE ENABLE TO CLICK ON SEND BUTTONS/LINKS
		public String WaitForSendBtn(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					Thread.sleep(3000);
					for(int i=1; i<=120; i++){
						String SendBtnState=driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[7]/div/div[1]/div[2]/div[2]/div[1]")).getAttribute("class");
						System.out.println("*******************SendBtnState="+SendBtnState);
						if(SendBtnState.equals("thread-message-send-area clearfix send-button thread-message-button js-send-button")){
							System.out.println("***************************Break For Loop");
							break;							
						}else{
							System.out.println("*****************Executing Sleep");
							Thread.sleep(1000);
						}
					}
					return Constants.PASS;
				}catch(Exception e){
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
				}
			}
		//*****WaitForReplyBtn Method**********	
		//METHOD WILL WAIT FOR SEND BUTTON TO BE ENABLE TO CLICK ON SEND BUTTONS/LINKS
		public String WaitForReplyBtn(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					Thread.sleep(3000);
					for(int i=1; i<=120; i++){
						String SendBtnState=driver.findElement(By.xpath("//*[@id='main-content']/div/div[2]/div/div[2]/div/div[1]/div[2]/div[3]/div[1]")).getAttribute("class");
						System.out.println("*******************SendBtnState="+SendBtnState);
						if(SendBtnState.equals("thread-message-send-area clearfix send-button thread-message-button js-send-button")){
							System.out.println("***************************Break For Loop");
							break;							
						}else{
							System.out.println("*****************Executing Sleep");
							Thread.sleep(1000);
						}
					}
					return Constants.PASS;
				}catch(Exception e){
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
				}
			}
				
		//*****Click Method**********	
		//METHOD WILL CLICK ON BUTTONS/LINKS
		public String Click(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DriverScript.CONFIG.getProperty(target))));
					driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
					return Constants.PASS;
				}catch(Exception e){
					ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
					e.printStackTrace();
					return Constants.FAIL;
				}
			}
		//*****ClickHidden Method**********	
		//METHOD WILL CLICK ON BUTTONS/LINKS WHICH ARE IN HIDDEN STATUS
				public String ClickHidden(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Click on Button");
						try{
							if(browser.equals("browser1")){
								driver=driver1;
							}else if(browser.equals("browser2")){
								driver=driver2;
							}
							WebDriverWait wait = new WebDriverWait(driver, 30);
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DriverScript.CONFIG.getProperty(target))));
							WebElement element=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target)));
							JavascriptExecutor js = (JavascriptExecutor)driver;
							js.executeScript("arguments[0].click();", element);
							return Constants.PASS;
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
				
		//*****Clickcss Method**********	
		//METHOD WILL CLICK ON BUTTONS/LINKS
			public String Clickcss(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Click on Button");
						try{
							if(browser.equals("browser1")){
								driver=driver1;
							}else if(browser.equals("browser2")){
								driver=driver2;
							}
							driver.findElement(By.cssSelector(DriverScript.CONFIG.getProperty(target))).click();
							return Constants.PASS;
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
			//*****TYPE Method**********
			//METHOD WILL TYPE TEXT IN ALL TEXT BOX 
					public String TypeToList(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Input The Value In Test Box");
						try{
							if(browser.equals("browser1")){
								driver=driver1;
							}else if(browser.equals("browser2")){
								driver=driver2;
							}
								String ToName1="";
								String Status="";
								for(int i=1; i<=20; i++){
								driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
								driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).sendKeys(data);
								driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
								Thread.sleep(3000);
								java.util.List list2=driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li"));
								System.out.println("*************************list2="+list2);
								for(int k=list2.size(); k<=list2.size(); k--){
									ToName1=driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li["+k+"]/div[1]")).getText();
									System.out.println("***********************ToName1="+ToName1);
									if(ToName1.contains(data)){
										Status="PASS";
										System.out.println("***********************Breack");
										i=30;
										break;
									}else{
										driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/div/div[2]/div[2]")).click();
										driver.findElement(By.xpath("//div[@id='navigation-column']/div/div/div/div/span")).click();
										Status="FAIL";
										System.out.println("***************************Clear TO Filed iteration="+i);
										Thread.sleep(1000);
										break;
										}
									}	
								}
								if(Status.equals("PASS")){
									return Constants.PASS;
								}else{
									ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
									return Constants.FAIL;
								}
						}catch (Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
		//*****TYPE Method**********
		//METHOD WILL TYPE TEXT IN ALL TEXT BOX 
				public String InputToList(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Input The Value In Test Box");
					String ToName1="";
					String Status="";
					java.util.List list2;
					java.util.List list;
					for(int i=1; i<=10; i++){
					try{
					driver.findElement(By.xpath("//*[@id='new-thread-to']")).click();
					driver.findElement(By.xpath("//*[@id='new-thread-to']")).sendKeys(data);
					driver.findElement(By.xpath("//*[@id='new-thread-to']")).click();
					Thread.sleep(3000);
					list2=driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li"));
					}catch (Exception e){
						break;
					}
					for(int k=list2.size(); k<=list2.size(); k--){
						try{
							ToName1=driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li["+k+"]/div[1]")).getText();
						}catch (Exception e){
							k=10000;
							e.printStackTrace();
							break;
						}
						if(ToName1.contains(data)){
							try{
								driver.findElement(By.xpath("//*[@id='new-thread-to']")).click();
								list=driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li/div/span/div/span/div"));
							}catch (Exception e){
								k=10000;
								e.printStackTrace();
								break;
							}
							for(int j=1; j<=list.size(); j++){
								try{
								String ToName= driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li/div/span/div/span/div["+j+"]/div/div[2]/div[1]")).getText();
								System.out.println(ToName);
								if(ToName.contains(data)){
									WebElement element = driver.findElement(By.xpath("//span/div["+j+"]/div/div[2]/div"));
									JavascriptExecutor js = (JavascriptExecutor)driver;
									js.executeScript("arguments[0].click();", element);
										String ToName2=driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li[1]/div[1]")).getText();
										if(ToName2.contains(data)){
											Status="PASS";
											k=100000;
											i=30;
											break;
										}
									}else{
									Thread.sleep(100);
								}
							}catch (Exception f){
								f.printStackTrace();
								k=10000;
								break;
							}
							}	
						}else{
							driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/div/div[2]/div[2]")).click();
							driver.findElement(By.xpath("//div[@id='navigation-column']/div/div/div/div/span")).click();
							Status="FAIL";
							Thread.sleep(1000);
							break;
							}
						}	
					}
					if(Status.equals("PASS")){
						return Constants.PASS;
					}else{
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						return Constants.FAIL;
					}
				}
		//*****SelectToList Method**********	
		//METHOD WILL CLICK ON BUTTONS/LINKS WHICH ARE IN HIDDEN STATUS
				public String SelectToList(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
						driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
						driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
						//Thread.sleep(3000);
						String Status="";
						java.util.List list=driver.findElements(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li/div/span/div/span/div"));
						for(int j=1; j<=list.size(); j++){
							driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
							String ToName= driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div/form/fieldset/div[1]/div/div[1]/div/div/ul/li/div/span/div/span/div["+j+"]/div/div[2]/div[1]")).getText();
							System.out.println(ToName);
							//driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
							if(ToName.contains(data)){
								driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
								WebElement element = driver.findElement(By.xpath("//span/div["+j+"]/div/div[2]/div"));
								JavascriptExecutor js = (JavascriptExecutor)driver;
								js.executeScript("arguments[0].click();", element);
								Status="PASS";
								break;
							}else{
								Thread.sleep(1);
							}
						}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							return Constants.FAIL;
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}
		//*****AddRecipient Method**********	
		//METHOD WILL SELECTS DESIRED RECIPIENT FROM SEARCH LIST
				public String AddRecipient(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						Thread.sleep(5000);
						java.util.List list=driver.findElements(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[4]/div/div/div[1]/div[2]/span/div/span/div"));
						String Status="";
						for(int j=1; j<=list.size(); j++){
							String ToName= driver.findElement(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[4]/div/div/div[1]/div[2]/span/div/span/div["+j+"]/div/div[2]/div[1]")).getText();
							System.out.println(ToName);
							if(ToName.contains(data)){
								WebElement element = driver.findElement(By.xpath("//span/div["+j+"]/div/div[2]/div"));
								//WebElement element = driver.findElement(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[4]/div/div/div[1]/div[2]/span/div/span/div["+j+"]/div/div[1]/img"));
								//WebElement element = driver.findElement(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[4]/div/div/div[1]/div[2]/span/div/span/div["+j+"]/div/div[2]/div[1]"));
								JavascriptExecutor js = (JavascriptExecutor)driver;
								js.executeScript("arguments[0].click();", element);
								Status="PASS";
								break;
							}else{
								Thread.sleep(100);
							}
						}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}
		//*****RemoveRecipient Method**********	
		//METHOD WILL SELECTS DESIRED RECIPIENT FROM SEARCH LIST
				public String RemoveRecipient(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						Thread.sleep(5000);
						java.util.List list=driver.findElements(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[2]/div"));
						String Status="";
						for(int j=1; j<=list.size(); j++){
							String ToName= driver.findElement(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[2]/div["+j+"]/div[2]/div[1]")).getText();
							System.out.println(ToName);
							if(ToName.contains(data)){
								WebElement element = driver.findElement(By.xpath("//*[@id='threadSettingsModal']/div[2]/div[2]/div["+j+"]/div[2]/div[5]/a"));
								JavascriptExecutor js = (JavascriptExecutor)driver;
								js.executeScript("arguments[0].click();", element);
								Status="PASS";
								break;
							}else{
								Thread.sleep(100);
							}
						}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							return Constants.FAIL;
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}
		//*****SelectConversation Method**********	
		//METHOD WILL SELECTS CONVERSATIONS WHICH ARE ARCHIEVED
				public String SelectConversation(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						Thread.sleep(5000);
						java.util.List list=driver.findElements(By.xpath("//*[@id='thread-collection']/li"));
						String Status="";
						for(int j=1; j<=list.size(); j++){
							java.util.List list2=driver.findElements(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[2]/div[1]"));
							String ArchivedStatu=driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[2]/div[1]")).getText();
							if(ArchivedStatu.equals(data)){
									WebElement element = driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[2]"));
									JavascriptExecutor js = (JavascriptExecutor)driver;
									js.executeScript("arguments[0].click();", element);
									Status="PASS";
									break;
								}else{
									Thread.sleep(100);
								}	
							}
						if(Status.equals("PASS")){
							return Constants.PASS;
						}else{
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							return Constants.FAIL;
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}
		
		//*****UnMuteThread Method**********	
		//METHOD WILL SELECTS CONVERSATIONS WHICH ARE NOT MUTTED 
				public String UnMuteThread(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
					//APP_LOGS.debug("Click on Button");
					try{
						if(browser.equals("browser1")){
							driver=driver1;
						}else if(browser.equals("browser2")){
							driver=driver2;
						}
						Thread.sleep(5000);
						String Status="";
						java.util.List list=driver.findElements(By.xpath("//*[@id='thread-collection']/li"));
						for(int j=1; j<=list.size(); j++){
							String ToName= driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[3]")).getAttribute("class");
							System.out.println(ToName);
							if(ToName.contains("mute-icon")==true){
								Thread.sleep(10);
							}else{
								WebElement element = driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[3]"));
								JavascriptExecutor js = (JavascriptExecutor)driver;
								js.executeScript("arguments[0].click();", element);
								Status="PASS";
								break;
							}
						}
							if(Status.equals("PASS")){
								return Constants.PASS;
							}else{
								ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
								return Constants.FAIL;
							}
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
			//*****MuteThread Method**********	
			//METHOD WILL SELECTS CONVERSATIONS WHICH ARE MUTTED 
						public String MuteThread(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
							try{
								if(browser.equals("browser1")){
									driver=driver1;
								}else if(browser.equals("browser2")){
									driver=driver2;
								}
								Thread.sleep(5000);
								String Status="";
								java.util.List list=driver.findElements(By.xpath("//*[@id='thread-collection']/li"));
								for(int j=1; j<=list.size(); j++){
									String ToName= driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[3]")).getAttribute("class");
									System.out.println(ToName);
									if(ToName.contains("mute-icon")==false){
										Thread.sleep(10);
									}else{
										WebElement element = driver.findElement(By.xpath("//*[@id='thread-collection']/li["+j+"]/div[2]/div[2]/div[3]"));
										JavascriptExecutor js = (JavascriptExecutor)driver;
										js.executeScript("arguments[0].click();", element);
										Status="PASS";
										break;
									}
								}
									if(Status.equals("PASS")){
										return Constants.PASS;
									}else{
										ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
										return Constants.FAIL;
									}
								}catch(Exception e){
									ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
									e.printStackTrace();
									return Constants.FAIL;
								}
							}		
		//*****Close Method**********	
		//METHOD WILL Close Dialog
				public String Close(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Click on Button");
						try{
							if(browser.equals("browser1")){
								driver=driver1;
							}else if(browser.equals("browser2")){
								driver=driver2;
							}
							//Thread.sleep(10000);
							driver.findElement(By.className(DriverScript.CONFIG.getProperty(target))).click();
							return Constants.PASS;
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
				//*****Select Method**********	
		//METHOD WILL CLICK ON BUTTONS/LINKS BY ID
		public String Select(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
				//APP_LOGS.debug("Click on Button");
				try{
					if(browser.equals("browser1")){
						driver=driver1;
					}else if(browser.equals("browser2")){
						driver=driver2;
					}
					driver.findElement(By.id(DriverScript.CONFIG.getProperty(target))).click();
					return Constants.PASS;
				}catch(Exception e){
					e.printStackTrace();
					return Constants.PASS;
				}
			}
		
		//*****Sleep Method**********	
		//METHOD WILL PAUSE TEST EXECUTION BY SPECIFIED TIME
				public String Sleep(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Click on Button");
						try{
							Thread.sleep(Integer.parseInt(data));
							return Constants.PASS;
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
		//*****Wait Method**********	
		//METHOD WILL WAIT TILL SPECIFIED OBJECT FOUND IN APP BY SPECIFIED TIME
						public String Wait(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
								//APP_LOGS.debug("Click on Button");
								try{
									Thread.sleep(3000);
									WebDriverWait wait = new WebDriverWait(driver, 20);
									WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty(target))));
									return Constants.PASS;
								}catch(Exception e){
									ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
									e.printStackTrace();
									return Constants.FAIL;
								}
							}
		//*****PageLoad Method**********	
		//METHOD WILL WAIT UP TO XX SECONDS FOR PAGE LOADING
						public String PageLoad(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
								//APP_LOGS.debug("Click on Button");
								try{
									driver.navigate().refresh();
									//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
									//WebDriverWait wait = new WebDriverWait(driver, 20);
									//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty(target))));
									return Constants.PASS;
								}catch(Exception e){
									ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
									e.printStackTrace();
									return Constants.FAIL;
								}
							}
			//*****PageRefresh Method**********	
			//METHOD WILL REFRESH THE WEB PAGE
						public String PageRefresh(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName,  String currentTSID, String currentDSID) throws InterruptedException, IOException{
									//APP_LOGS.debug("Click on Button");
							try{
								WebDriverWait wait = new WebDriverWait(driver, 5);
								WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty("WelcomeMsg"))));
								return Constants.PASS;
							}catch(Exception e){
								driver.navigate().refresh();
								try{
									WebDriverWait wait = new WebDriverWait(driver, 5);
									WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty("WelcomeMsg"))));
									return Constants.PASS;
								}catch(Exception f){
									driver.navigate().refresh();
									try{
										WebDriverWait wait = new WebDriverWait(driver, 5);
										WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty("WelcomeMsg"))));
										return Constants.PASS;
									}catch(Exception g){
										driver.navigate().refresh();
										try{
											WebDriverWait wait = new WebDriverWait(driver, 5);
											WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty("WelcomeMsg"))));
											return Constants.PASS;
										}catch(Exception h){
											driver.navigate().refresh();
											try{
												WebDriverWait wait = new WebDriverWait(driver, 5);
												WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DriverScript.CONFIG.getProperty("WelcomeMsg"))));
												return Constants.PASS;
											}catch(Exception i){
												driver.navigate().refresh();
												ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
												i.printStackTrace();
												return Constants.FAIL;
											}
										}
									}
								}
							}	
						}
						
			//*****ScreenShot Method**********	
			//METHOD WILL CAPTURE SCREEN SHOT WHEN TEST STEP FAIL
						public String ScreenShot(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
						File file=((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
						String filepath = (System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+BrowserName+SubFolderName+"/"+"ScreenShots"+"/");
						System.out.println("*************************FilePath="+filepath);
						String filetype = ".jpg";
						//String filename = currentTestCaseName;
						String filenamepath = filepath+currentTestName+"-"+currentTSID+"-"+currentDSID+filetype;
						FileUtils.copyFile(file, new File(filenamepath));
						return Constants.PASS;
					}catch(Exception e){
							return Constants.FAIL;
						}
					}
						
			//*****CheckCheckBox Method**********	
			//METHOD WILL CHCECK THE CHECK BOX IF IT IS NOT SELECTED BEFORE ELSE CHECK REMAINS THE SAME
					public String CheckCheckBox(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
						String classname=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getAttribute("class");
						if(classname.contains("checked")){
							return Constants.PASS;
						}else{
							driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
							return Constants.PASS;
						}
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}	

			//*****UnCheckCheckBox Method**********	
			//METHOD WILL UN-CHCECK THE CHECK BOX IF IT IS NOT UN-CHECKED BEFORE ELSE UN-CHECK REMAINS THE SAME
					public String UnCheckCheckBox(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
						String classname=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getAttribute("class");
						System.out.println("**********************ClassName="+classname);
						if(classname.contains("checked")){
							driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).click();
							return Constants.PASS;
						}else{
							return Constants.PASS;
						}
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}
		//*****LinkState Method**********	
		//METHOD WILL VALIDATE IF LINK/BUTTON IS ENABLED OR DISABLED
				public String LinkState(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
						//APP_LOGS.debug("Click on Button");
				try{
					if(TestBrowser.equals("FF")){
					BrowserName = "FFTestResults_";
					}else if(TestBrowser.equals("Chrome")){
						BrowserName = "ChromeTestResults_";
					}else if (TestBrowser.equals("IE")){
						BrowserName = "IETestResults_";
					}
					String classname=driver.findElement(By.xpath(DriverScript.CONFIG.getProperty(target))).getAttribute("class");
					if(classname.contains("disabled")){
						if(data.contains("disabled")){
							return Constants.PASS;
						}else{
							return Constants.FAIL;
						}
					}else{
						if(data.contains("enabled")){
							return Constants.PASS;
						}else{
							return Constants.FAIL;
						}
					}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}	

	//*****Maximize Method**********	
	//METHOD WILL Maximize WEB WINDOW
					public String Maximize(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
							driver.manage().window().maximize();
							return Constants.PASS;
						}catch(Exception e){
							ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							e.printStackTrace();
							return Constants.FAIL;
						}
					}	
	//*****verifySignIn Method**********	
	//METHOD WILL VERIFY SING-IN SECUSS OR NOT
					public String verifySignIn(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
						if(Correct_Data.equals("Y")){
							boolean InboxLink = driver.findElement(By.xpath("//*[@id='inbox-link']")).isEnabled();
							boolean ContactsLink = driver.findElement(By.xpath("//*[@id='contacts-link']")).isEnabled();
							boolean PatientsLink = driver.findElement(By.xpath("//*[@id='patients-link']")).isEnabled();
							boolean Compose = driver.findElement(By.xpath("//div[@id='navigation-column']/div/div/div/div/span")).isEnabled();
							if(InboxLink==true && ContactsLink==true && PatientsLink==true && Compose==true){
								return Constants.PASS;
							}else{
								return Constants.FAIL;
							}
						}else{
							Thread.sleep(3000);
							boolean CureatrIncText=driver.findElement(By.xpath("//*[@id='frame']/div/div[1]/div/div[4]")).isDisplayed();
							boolean SelectedOrgName=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/h1")).isDisplayed();
							boolean GetSupportLink=driver.findElement(By.xpath("//*[@id='frame']/div/div[1]/div/div[3]/a")).isEnabled();
							String ActErrorMsg=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[2]")).getText();
							System.out.println("*************ExpectedErrorMsg="+ExpectedErrorMsg);
							System.out.println("*************ActErrorMsg="+ActErrorMsg);
							if(CureatrIncText==true&&SelectedOrgName==true&&GetSupportLink==true&&ActErrorMsg.equals(ExpectedErrorMsg)){
								return Constants.PASS;
							}else{
								return Constants.FAIL;
							}
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}						
		//*****verifySignOut Method**********	
		//METHOD WILL VERIFY SING-Out SECUSS OR NOT
				public String verifySignOut(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
							//APP_LOGS.debug("Click on Button");
					try{
						if(TestBrowser.equals("FF")){
							BrowserName = "FFTestResults_";
						}else if(TestBrowser.equals("Chrome")){
							BrowserName = "ChromeTestResults_";
						}else if (TestBrowser.equals("IE")){
							BrowserName = "IETestResults_";
						}
						if(Correct_Data.equals("Y") || Correct_Data.equals("N") || Correct_Data.equals("")){
							Thread.sleep(3000);
							boolean CureatrIncText=driver.findElement(By.xpath("//*[@id='frame']/div/div[1]/div/div[4]")).isDisplayed();
							boolean SelectedOrgName=driver.findElement(By.xpath("//*[@id='frame']/div/div[2]/div[2]/div/div/div/div[1]/h1")).isDisplayed();
							boolean GetSupportLink=driver.findElement(By.xpath("//*[@id='frame']/div/div[1]/div/div[3]/a")).isEnabled();
							if(CureatrIncText==true&&SelectedOrgName==true&&GetSupportLink==true){
								return Constants.PASS;
							}else{
								ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
								return Constants.FAIL;
							}
						}else{
							boolean InboxLink = driver.findElement(By.xpath("//*[@id='inbox-link']")).isEnabled();
							boolean ContactsLink = driver.findElement(By.xpath("//*[@id='contacts-link']")).isEnabled();
							boolean PatientsLink = driver.findElement(By.xpath("//*[@id='patients-link']")).isEnabled();
							boolean Compose = driver.findElement(By.xpath("//div[@id='navigation-column']/div/div/div/div/span")).isEnabled();
							if(InboxLink==true && ContactsLink==true && PatientsLink==true && Compose==true){
								return Constants.PASS;
							}else{
								return Constants.FAIL;
							}
						}
					}catch(Exception e){
						ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						e.printStackTrace();
						return Constants.FAIL;
					}
				}						
      //*****CreateUser Method********** 
	  //METHOD WILL CreateUser BASED ON BROWSER
			  public String CreateUser(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
		    //APP_LOGS.debug("Click on Button");
				  String Status = null;
			      try{
			      System.out.println("**************data Value at CreateUser="+data);
			      if(TestBrowser.equals("FF")&&(data.split("\\&")[0]).equals("FF")){
			       BrowserName = "FFTestResults_";
			       Status=CreateUserAPIRequest(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			       System.out.println("********Status="+Status);
			      }else if(TestBrowser.equals("Chrome")&&(data.split("\\&")[0]).equals("Chrome")){
			       BrowserName = "ChromeTestResults_";
			       Status=CreateUserAPIRequest(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			       System.out.println("********Status="+Status);
			      }else if (TestBrowser.equals("IE")&&(data.split("\\&")[0]).equals("IE")){
			       BrowserName = "IETestResults_";
			       Status=CreateUserAPIRequest(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			       System.out.println("********Status="+Status);
			      }
			      System.out.println("********Status@1541="+Status);
			       if(Status.equals("PASS")){
			    	   return Constants.PASS;
				   }else{
					   return Constants.FAIL;
				   }
			       }catch(Exception e){
			       ScreenShot(target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
			       e.printStackTrace();
			       return Constants.FAIL;
			       }
			      }
		 //*****CreateUserAPIRequest Method********** 
		 //METHOD WILL CREATE TRHOUGH USER API REQUEST
		  public String CreateUserAPIRequest(String target, String data, String Correct_Data, String browser, String ExpectedErrorMsg, String currentTestName, String currentTSID, String currentDSID) throws InterruptedException, IOException{
			    //APP_LOGS.debug("Click on Button");
			     try{
			      if(TestBrowser.equals("FF")){
			    	  BrowserName = "FFTestResults_";
				  }else if(TestBrowser.equals("Chrome")){
					  BrowserName = "ChromeTestResults_";
				}else if (TestBrowser.equals("IE")){
					  BrowserName = "IETestResults_";
					}
			      Random ran = new Random();
			      int EmailExten = ran.nextInt(9999) + 10000;
			      
			      String IEinstitutionOrg=data.split("\\&")[1];
			       System.out.println("IEinstitutionOrg="+IEinstitutionOrg);
			       
			       String Email=TestBrowser+"User"+EmailExten+"@cureatr.com";
			       System.out.println("IEuserA="+Email);
			       
			       String Password=data.split("&")[3];
			       System.out.println("IEpasswordA="+Password);
			       
			       String FirstName=data.split("&")[4];
			       System.out.println("IEFirstNameA="+FirstName);
			       
			       String LastName=data.split("&")[5];
			       System.out.println("IELastNameA="+LastName);
			       
			       String Type=data.split("&")[6];
			       System.out.println("IETYPEA="+Type);
			       
			       String Specialty=data.split("&")[7];
			       System.out.println("IEspecialtyA="+Specialty);
			       
			       String Title=data.split("&")[8];
			       System.out.println("IETITILE="+Title);
			       
			       
			    String urlParameters  = "institution="+data.split("\\&")[1]+"&email="+data.split("\\&")[2]+"&password="+data.split("\\&")[3]+"&first_name="+data.split("\\&")[4]+"&last_name="+data.split("\\&")[5]+"&type="+data.split("\\&")[6]+"&specialty="+data.split("\\&")[7]+"&title="+data.split("\\&")[8];
				byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
				int    postDataLength = postData.length;
				String request        = "https://cureatr-vm.dev:5001/mtuity/create_user";
				URL    url            = new URL( request );
				
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				
				conn.setDoOutput( true );
				conn.setInstanceFollowRedirects( false );
				conn.setRequestMethod( "POST" );
				conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
				conn.setRequestProperty( "charset", "utf-8");
				conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				conn.setUseCaches( false );
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.write( postData );
				
				int responseCode = conn.getResponseCode();
				String response1=conn.getResponseMessage();
				String response2=conn.getRequestMethod();
				  
				System.out.println(response2);
				System.out.println(responseCode);
				System.out.println(response1);
				  
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String line=null;
				while ((line=in.readLine())!=null){
					String [] tokens=line.split(":");
					System.out.println(tokens[1]);
					if(tokens[1].contains("error")){
						return Constants.FAIL;
					}else{
						return Constants.PASS;
					}
				}
				return Constants.PASS;
			     }catch(Exception e){
				    e.printStackTrace();
				    return Constants.FAIL;
				     }
				}
}