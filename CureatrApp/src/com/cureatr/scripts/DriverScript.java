package com.cureatr.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Objects;
import java.util.Properties;
//import java.util.logging.LogManager;

//import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

//import mx4j.log.Log;

import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestNGException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

//DriverScript CLASS DRIVES END TO END TEST AUTOMATION SCRIPTS EXECUTION
public class DriverScript {
	
	//INITIALIZING WebDriver 
	public WebDriver driver;
	public Keywords keywords;
	public Method method[];
	public String TestBrowser="";
	
	//INITIALIZING APPLICATION LOGS
	public Logger APP_LOGS;
	
	// INITIALIZING POI EXCEL OBJECTS
	public Xls_Reader SuiteXLS;
	public int currentSuiteID;
	public String currentTestSuite;
	public Xls_Reader ResultsXLS;
	
	//DECLARING TEST SUITE, TEST CASE ID, TEST CASE NAME, TEST STEP ID, KEYWORKNAME, EXPECTED ERROR MESSAGE
	public Xls_Reader currentTestSuiteXLS;
	public int currentTestCaseID;
	public String currentTestCaseName;
	public int currentTestStepID;
	public String currentKeywordName;
	public String ExpectedErrorMsg;
		
	// CURRENT TEST DATA SET ID
	public int currentTestDataSetID;
	
	//DECLARING KEYWORD EXECUTION RESULT SET
	public String Keyword_execution_result_main;
	public String Keyword_execution_result;
	public ArrayList<String> resultSet;
		
	//DECLARING CONFIGURATION PROPERTIES
	public static Properties CONFIG;
	public Properties OR;
	public String data;
	public String currentTestName;
	public String currentTSID;
	public String currentDSID;
	public String priority;
	public String target;
	public String ProceedOnFail;
	public String colName;
	public String Correct_Data="";
	public String PASSWORD;
	public String browser;
	public String ExecuteNextStep;
	
	//DECLARING FOLDERNAMES
	public String FolderName="";
	public String SubFolderName="";
	public long startTime;
	public String StartTimeHH;
    public long endTime;
	
	//DECLARING RESULTS SETS(CAN BE STORED TITLE, ERROR MESSAGES, STATIC & DYNAMIC TEXT)
	public String Result1="";
	public String Result2="";
	public String Result3="";
	public String Result4="";
	
	//DELARING TO STORE RESULTS PASS/FAIL/SKIP COUNT
	public int PASSCOUNT=0;
	public int FAILCOUNT=0;
	public int SKIPCOUNT=0;
	
	//DELARING TO STORE P1/P2/P3/P4 COUNT
	public int P1=0;
	public int P2=0;
	public int P3=0;
	public int P4=0;
	
	//DECLARING TO STORE EMAIL ATTACHEMENTS PATH
	public String IEAttachmentPaths="&";
	public String ChromeAttachmentPaths="&";
	public String FFAttachmentPaths="&";
	public String ResultsPath="";
	public String AttachmentPaths="&";
	//public String ReportEmailID;
	
//**********************************************************************
//METHOD NAME: DriverScript
//DESCRIPTION: METHOD WILL STORE ALL KEYWORDS/METHODS/FUNCTIONS IN ARRAY LIST. THIS WILL BE USED TO CALL/REUSE MOTHODS IN KEYWORDS
//**********************************************************************
	public DriverScript(){
			keywords = new Keywords();
			method= keywords.getClass().getMethods();
		}
	Workbook workbook; 
	WritableWorkbook copy;
	
	@Test //METHOD WILL CREATE NEW FOLDERS TO STORE FIREFOX, CHROME AND IE BROWSERS RESULTS SHEETS
	public void excels() throws BiffException, IOException, WriteException {
		if(TestBrowser.equals("FF")){
			SubFolderName = "FFTestResults_"+SubFolderName;
		}else if(TestBrowser.equals("Chrome")){
			SubFolderName = "ChromeTestResults_"+SubFolderName;
		}else if (TestBrowser.equals("IE")){
			SubFolderName = "IETestResults_"+SubFolderName;
		}
		//IF THE DIRECTORY DOES NOT EXISTS, ELSE CREATE IT(CREATE DIRECTORY WITH CURRENT DATE)
		File FolderPath = new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName);
		if (!FolderPath.exists()) {
			boolean result = false;
		    try{
		    	FolderPath.mkdir();
		        result = true;
		    } 
		    catch(SecurityException e){
		    	e.printStackTrace();
		    }		    
		}
		//IF THE SUB DIRECTORY DOES NOT EXISTS, ELSE CREATE IT(CREATE SUB DIRECTORY WITH CURRENT DATE & TIME)
		File SubFolderPath = new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName);
		if (!SubFolderPath.exists()) {
			boolean result = false;
		    try{
		    	SubFolderPath.mkdir();
		        result = true;
		    } 
		    catch(SecurityException e){
		        e.printStackTrace();
		    }		    
		}
		//IF THE SCREENSHOT DIRECTORY DOES NOT EXISTS, ELSE CREATE IT
		File ScreenShotPath = new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"ScreenShots");
		if (!SubFolderPath.exists()) {
			System.out.println("creating directory: " + ScreenShotPath);
		    boolean result = false;
		    try{
		    	ScreenShotPath.mkdir();
		        result = true;
		    } 
		    catch(SecurityException e){
		        e.printStackTrace();
		    }		    
		}
		//COPYING SUITE XLS FILE FROM INPUT FOLDER TO OUTPUT FOLDER
		workbook = Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/InputFiles/Web/Suite_Web.xls"));
		if(TestBrowser.equals("IE")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteIE.xls"), workbook);
		}else if(TestBrowser.equals("Chrome")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteChrome.xls"), workbook);
		}else if(TestBrowser.equals("FF")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteFF.xls"), workbook);
		}
		copy.write();
		copy.close();
		workbook.close();
		
		//COPYING RESULTS XLS FILE FROM INPUT FOLDER TO OUTPUT FOLDER
		workbook = Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/InputFiles/Web/Results.xls"));
		if(TestBrowser.equals("IE")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls"), workbook);
			ResultsPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls";
		}else if(TestBrowser.equals("Chrome")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls"), workbook);
			ResultsPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls";
		}else if(TestBrowser.equals("FF")){
			copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls"), workbook);
			ResultsPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results.xls";
		}
		copy.write();
		copy.close();
		workbook.close();
	}
	//THIS METHOD WILL BE EXECUTED FIRST/BEFORE start() METHOD EXECUTION
	@BeforeTest
	@Parameters("browser")
	/*
	protected void setUp() throws SecurityException, IOException
	{
	    FileInputStream fis = new FileInputStream("D:/CureatrAutomationWorkSpace/CureatrApp/src/log4j.properties");
	    //LogManager.ge
	    LogManager.getLogManager().readConfiguration(fis);
	}*/
	public void launchBrowserCase(String browser) throws IOException {
		// TO GET EXECUTION DATE AND START TIME
		startTime = System.currentTimeMillis();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		Calendar cal1 = Calendar.getInstance();
		StartTimeHH = dateFormat1.format(cal1.getTime());
						
		//INITIALIZING TEST RESULTS FOLDER WITH SYS DATE
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		FolderName = dateFormat.format(cal.getTime());
				
		//INITIALIZING SUBFOLDER NAME WITH SYS DATE, TIME IN HH:MM
		SimpleDateFormat subfolderdateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Calendar subfoldercal = Calendar.getInstance();
		SubFolderName = subfolderdateFormat.format(subfoldercal.getTime());

		//INITIALIZING TEST BROWSER NAME
		if(browser.equals("FF")){
			TestBrowser="FF";
		}else if(browser.equalsIgnoreCase("IE")){
			TestBrowser="IE";
		}else if(browser.equalsIgnoreCase("Chrome")){
			TestBrowser="Chrome";
		}
		keywords.setdriver1(TestBrowser, FolderName, SubFolderName);
	}
	//METHOD WILL 
	public WebDriver getDriver(){
        System.out.println("Driver "+driver);
        return driver;
    }
	
	@Test
	public void start() throws EncryptedDocumentException, InvalidFormatException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TestNGException, BiffException, WriteException, InterruptedException{
		
		//INITIALIZING THE APP_LOGS
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		APP_LOGS.debug(":: Hello Mohan Nimmala"); 
		APP_LOGS.debug("::  Hello");
		APP_LOGS.debug(":: Intialze Suite Xlsx");
				
		FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"/src/com/cureatr/config/config.properties");
		CONFIG= new Properties();
		CONFIG.load(fs);
		
		
		//CONDITION TO READ SUITE XLS FILE FROM OUTPUT DIRECTORY
		if(TestBrowser.equals("IE")){
			SuiteXLS = new Xls_Reader(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteIE.xls");
		}else if(TestBrowser.equals("Chrome")){
			SuiteXLS = new Xls_Reader(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteChrome.xls");
		}else if(TestBrowser.equals("FF")){
			SuiteXLS = new Xls_Reader(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"SuiteFF.xls");
		}
		//FOR LOOP TO GET/READ TEST SCRIPT NAME/ID FROM SUITE.XLS SHEET
		for(currentSuiteID=2;currentSuiteID<=SuiteXLS.getRowCount(Constants.TEST_SUITE_SHEET);currentSuiteID++){
			APP_LOGS.debug(SuiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentSuiteID)+" -- "+SuiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_RUNMODE, currentSuiteID));
			currentTestSuite=SuiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentSuiteID);
			//VALIDATION TO CHECK XLS SHEET EXISTING WITH TEST SCRIPT OR NOT
			if(SuiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_RUNMODE, currentSuiteID).equals(Constants.TEST_RUNMODE)){
				//COPYING CURRENT TEST SCRIPT SHEETS.XLS FILE FROM INPUT FOLDER TO OUTPUT FOLDER
				APP_LOGS.debug("COPYING TEST SCRIPT SHEETS.XLS FILE FROM INPUT FOLDER TO OUTPUT FOLDER"+currentTestSuite);
				workbook = Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/InputFiles/Web/"+currentTestSuite+".xls"));
				if(TestBrowser.equals("IE")){
					copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls"), workbook);
					String IEAttachmentPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls";
					AttachmentPaths=AttachmentPaths+IEAttachmentPath+"&";
				}else if(TestBrowser.equals("Chrome")){
					copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls"), workbook);
					String ChromeAttachmentPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls";
					AttachmentPaths=AttachmentPaths+ChromeAttachmentPath+"&";
				}else if(TestBrowser.equals("FF")){
					copy = Workbook.createWorkbook(new File(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls"), workbook);
					String FFAttachmentPath=System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls";
					AttachmentPaths=AttachmentPaths+FFAttachmentPath+"&";
				}
				copy.write();
				copy.close();
				workbook.close();
				
				//READ CURRENTTESTSUITE XLS FILE FROM OUTPUT DIRECTORY
				currentTestSuiteXLS= new Xls_Reader(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+currentTestSuite+".xls");
				
				//READ RESULTS XLS FILE FROM OUTPUT DIRECTORY
				ResultsXLS= new Xls_Reader(System.getProperty("user.dir")+"/src/com/cureatr/excel/OutputFiles/"+FolderName+"/"+SubFolderName+"/"+"Results"+".xls");
				
				//FOR LOOP TO GET/READ ALL TEST CASES FROM CURRENTTESTSUITE.XLS SHEET
				for(currentTestCaseID=2;currentTestCaseID<=currentTestSuiteXLS.getRowCount(Constants.TEST_CASE_SHEET);currentTestCaseID++){
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, currentTestCaseID)+" -- "+currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE, currentTestCaseID));
					//CURRENT TEST CASE NAME USED TO READ THE TEST STEPS FROM TEST STEPS SHEET
					currentTestCaseName=currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, currentTestCaseID);
					//CURRENTTESTNAME IS USED TO PREFIX THE ERROR SCREENSHOT WITH TEST NAME 
					currentTestName=currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, currentTestStepID);
					//CONDITION TO CHECK CUREENT TEST CASE RUN MODE YES/NO
					if(currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE, currentTestCaseID).equals(Constants.TEST_RUNMODE)){
						APP_LOGS.debug("**********Executing Test Cases**********"+currentTestCaseName);
						resultSet= new ArrayList<String>();
						//WILL READ CURRENT TEST NAME PRIORITY(P1/P2/P3/P4) TO UPDATE FAILED TEST CASE PRIORITY RESUTLS SHEET
						priority=currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_PRIORITY, currentTestCaseID);
						if(currentTestSuiteXLS.isSheetExist(currentTestCaseName)){
							//RUN AS MANY TIMES AS NUMBER OF TEST DATA SETS WITH RUNMODE Y
							for(currentTestDataSetID=2;currentTestDataSetID<=currentTestSuiteXLS.getRowCount(currentTestCaseName);currentTestDataSetID++){
								resultSet= new ArrayList<String>();
								APP_LOGS.debug("**********ITERATION NUMBER**********"+(currentTestDataSetID-1));
								//CHECKING RUNMODE FOR THE CURRENT DATA SET
								if(currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.TEST_STEP_RUNMODE, currentTestDataSetID).equals(Constants.TEST_RUNMODE)){
									Correct_Data=currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.TEST_Correct_Data, currentTestDataSetID);
									PASSWORD=currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.TEST_PASSWORD, currentTestDataSetID);
									ExpectedErrorMsg=currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.TEST_EXP_ERROR_MSG, currentTestDataSetID);
									// ITERATING THOUGH ALL KEYWORDS
									executeKeywords();//Multiple Steps OF Test Data
								}else{
									//CALLING METHOD TO PRINT PASS/PASS/SKIP STATUS
									createXLSReport();
								}
							}
						}else{
							currentTestDataSetID=0;
							executeKeywords();//No Test Data With The Test
							//createXLSReport();
							}
						}
					}
				}
			}
			SendEmail();
		}
	public void executeKeywords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// ITERATING THOUGH ALL KEYWORDS
		ExecuteNextStep="Y";
		for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount("TestSteps");currentTestStepID++){
			//priority=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_PRIORITY, currentTestStepID);
			data=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_DATA, currentTestStepID);
			target=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_TARGET, currentTestStepID);
			ProceedOnFail=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.PROCEED_ON_FAIL, currentTestStepID);
			System.out.println("******************ProceedOnFail="+ProceedOnFail);
			currentKeywordName=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_STEP_KEYWORD, currentTestStepID);
			browser=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_BROWSER, currentTestStepID);
			currentTestName=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_CASE_ID, currentTestStepID);
			currentTSID=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_SUITE_ID, currentTestStepID);
			currentDSID=currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.TEST_DATA_SET_ID, currentTestDataSetID);
			if(data.startsWith("col")){
				if(TestBrowser.equals("IE")){
					data=currentTestSuiteXLS.getCellData(currentTestCaseName, data.split("\\|")[1], currentTestDataSetID);
				}else if(TestBrowser.equals("FF")){
					data=currentTestSuiteXLS.getCellData(currentTestCaseName, data.split("\\|")[2], currentTestDataSetID);
				}else if(TestBrowser.equals("Chrome")){
					data=currentTestSuiteXLS.getCellData(currentTestCaseName, data.split("\\|")[3], currentTestDataSetID);
				}
			}else if(data.startsWith("IE")||data.startsWith("Chrome")||data.startsWith("FF")){
			    String[] APIDataSet=data.split("\\|");
			    data=APIDataSet[0]+"&";
			    for(int i=1; i<APIDataSet.length; i++){
			     String data1=currentTestSuiteXLS.getCellData(currentTestCaseName, APIDataSet[i], currentTestDataSetID);
			     data=data+data1+"&";
			     }
			     System.out.println(data);
			    }
			// CHECKING TCID
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_STEP_TCID, currentTestStepID))){
				APP_LOGS.debug("executeKeywords:**********Current Keyword Name**********"+currentKeywordName);
				for(int i=0;i<(method.length);i++){
					if(method[i].getName().equals(currentKeywordName)&&ExecuteNextStep.equals("Y")){
						Keyword_execution_result_main=(String) method[i].invoke(keywords, target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
						String[] TesteresultSet=Keyword_execution_result_main.split("&");
						Keyword_execution_result=TesteresultSet[0];
						if(TesteresultSet.length>1){
								Result1=TesteresultSet[1];
								Result2=TesteresultSet[2];
								Result3=TesteresultSet[3];
								Result4=TesteresultSet[4];
						}
						APP_LOGS.debug(Keyword_execution_result);
						resultSet.add(Keyword_execution_result);
						if(Keyword_execution_result.contains("FAIL") && ProceedOnFail.equals("NO")){
							ExecuteNextStep="N";
							}
						if(Correct_Data.contains("N") && ProceedOnFail.equals("NO")){
							ExecuteNextStep="N";
							}
						createXLSReport();
						}else if(method[i].getName().equals(currentKeywordName)&&method[i].getName().equals("CloseWebApp")&&ExecuteNextStep.equals("N")){
							Keyword_execution_result_main=(String) method[i].invoke(keywords, target, data, Correct_Data, browser, ExpectedErrorMsg, currentTestName, currentTSID, currentDSID);
							String[] TesteresultSet=Keyword_execution_result_main.split("&");
							Keyword_execution_result=TesteresultSet[0];
						}
					}
				}
			}
		createGraphReport();
		priorityGraphReport();
		AutomationTestReport();
		}
	//TO PRINT DATE, START TIME, END TIME, ELAPSED TIME AND BROWSER NAME IN RESULTS SHEET
	public void AutomationTestReport(){
		//TO PRINT DATE IN RESULTS SHEET
		ResultsXLS.setCellData("Status", "Results48", 4, FolderName);
		
		//TO PRINT START TIME IN RESULTS SHEET
		String StartTimeHH1 = StartTimeHH.substring(StartTimeHH.length() - 8);
		ResultsXLS.setCellData("Status", "Results48", 5, StartTimeHH1);
		
		//TO PRINT END TIME IN RESULTS SHEET
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		Calendar cal2 = Calendar.getInstance();
		String EndTimeHH1 = dateFormat2.format(cal2.getTime());
		String EndTimeHH = EndTimeHH1.substring(EndTimeHH1.length() - 8);
		
		endTime = System.currentTimeMillis();
		//String endTimestr = Long.toString(endTime);
		ResultsXLS.setCellData("Status", "Results48", 6, EndTimeHH);
		
		//TO PRINT ELAPSED TIME IN RESULTS SHEET
		long elapsedtime=endTime-startTime;
		String elapsedtimestr = Long.toString(elapsedtime);
		ResultsXLS.setCellData("Status", "Results48", 7, elapsedtimestr);
		
		//TO PRINT BROWSER NAME TIME IN RESULTS SHEET
		ResultsXLS.setCellData("Status", "Results48", 8, TestBrowser);
	}
	
	//priority
	public void createGraphReport(){
		//TO PRINT PASS FAIL STATUS IN RESULTS SHEET & TO PRINT PASS FAIL STATUS IN TEST CASE SHEET 
		if(resultSet.contains(Constants.FAIL)){
				FAILCOUNT=FAILCOUNT+1;
				currentTestSuiteXLS.setCellData(Constants.TEST_CASE_SHEET, colName, currentTestCaseID, Constants.FAIL);
				ResultsXLS.setCellData("Status", "Results50", 6, Integer.toString(FAILCOUNT));
			}else{
				PASSCOUNT=PASSCOUNT+1;
				currentTestSuiteXLS.setCellData(Constants.TEST_CASE_SHEET, colName, currentTestCaseID, Constants.PASS);
				ResultsXLS.setCellData("Status", "Results50", 5, Integer.toString(PASSCOUNT));
				}
			}
	public void priorityGraphReport(){
		//TO PRINT P1, P2, P3, P4 COUNT IN RESULTS SHEET
		if(resultSet.contains(Constants.FAIL)){
				if(priority.contains("P1")){
					P1=P1+1;
					ResultsXLS.setCellData("Status", "Results50", 19, Integer.toString(P1));
				}else if(priority.contains("P2")){
					P2=P2+1;
					ResultsXLS.setCellData("Status", "Results50", 20, Integer.toString(P2));
				}else if(priority.contains("P3")){
					P3=P3+1;
					ResultsXLS.setCellData("Status", "Results50", 21, Integer.toString(P3));
				}else if(priority.contains("P4")){
					P4=P4+1;
					ResultsXLS.setCellData("Status", "Results50", 22, Integer.toString(P4));
				}
			}
		}
	
	public void createXLSReport(){
		if(currentTestDataSetID>1){
			colName=Constants.TEST_RESULT+(currentTestDataSetID-1);
		}else{
			colName=Constants.TEST_RESULT+1;
		}
		boolean isColExist=false;
		
		for(int c=0;c<currentTestSuiteXLS.getColumnCount(Constants.TEST_STEPS_SHEET);c++){
			if(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, c, 1).equals(colName)){
				isColExist=true;
				break;
			}
		}
		if(!isColExist){
				currentTestSuiteXLS.addColumn(Constants.TEST_STEPS_SHEET, colName);
				currentTestSuiteXLS.addColumn(Constants.TEST_CASE_SHEET, colName);
		}
		//TO PRINT SKIP STATUS IN TEST CASE SHEET & RESULTS SHEET
		if(resultSet.size()==0){
			SKIPCOUNT=SKIPCOUNT+1;
			currentTestSuiteXLS.setCellData(Constants.TEST_CASE_SHEET, colName, currentTestCaseID, Constants.SKIP);
			ResultsXLS.setCellData("Status", "Results50", 7, Integer.toString(SKIPCOUNT));
			}
	
		if(resultSet.size()==0){
			for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount("TestSteps");currentTestStepID++){
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_STEP_TCID, currentTestStepID))){
				APP_LOGS.debug("**********Current Keyword Name**********"+currentKeywordName);
				
				for(int j=0;j<(method.length);j++){
					if(!method[j].getName().equals(currentKeywordName)){
						currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, currentTestStepID, Constants.SKIP);
					}
				}
			}
		}
	}
		
	//int index=0;
	for(int i=currentTestStepID;i<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);i++){
	if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_STEP_TCID, i))){
		if(Keyword_execution_result.equals(Constants.PASS)){
			currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.PASS);
			break;
		}else if(Keyword_execution_result.equals(Constants.FAIL)){
			currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.FAIL);
			break;
		}else if(Keyword_execution_result.equals("") || resultSet.size()==0){
			currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.SKIP);
			break;
				}
			}
		} 
	//TO PRINT PASS & FAIL STATUS IN EXCEL SHEET FOR GIVEN TEST DATA	
	if(resultSet.size()==0){
		//Print Skip in Results column
		currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.TEST_RESULT, currentTestDataSetID, Constants.SKIP);
		//return;
	}else{
		//TO PRINT PASS & FAIL STATUS IN EXCEL SHEET FOR GIVEN TEST DATA
		for(int i=0; i<resultSet.size(); i++){
			if(resultSet.get(i).equals(Constants.FAIL)){
				currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.TEST_RESULT, currentTestDataSetID, Constants.FAIL);
				break;
			}else{
				currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.TEST_RESULT, currentTestDataSetID, Constants.PASS);
				}
			}
		}
	}
	public void SendEmail() {
	      // Sender's email ID needs to be mentioned
	      String from = "mohan.nimmala@mtuity.com";

	      final String username = "mohan.nimmala@mtuity.com";//change accordingly
	      final String password = "Mobily1981";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(username, password);
	        }
	      });
	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         String[] to =CONFIG.getProperty("EmailAccountsForReports").split("\\|");
	         //String[] to = {"nmksridhar@gmail.com","mohan.nimmala@mtuity.com"};
	         try {
	        	 message.setFrom(new InternetAddress(from));
	             InternetAddress[] addressTo = new InternetAddress[to.length];
	             for (int i = 0; i < to.length; i++)
	             {
	                 addressTo[i] = new InternetAddress(to[i]);
	             }
	             message.setRecipients(RecipientType.TO, addressTo);
	         }catch(Exception exc) {
	             System.out.println(exc);
	         }
	         // Set Subject: header field
	         if(TestBrowser.equals("IE")){
	        	 message.setSubject("Cureatr || IE Browser ||Automation Test Report || "+SubFolderName);	 
	 		}else if(TestBrowser.equals("Chrome")){
	 			message.setSubject("Cureatr || Chrome Browser || Automation Test Report || "+SubFolderName);
	 		}else if(TestBrowser.equals("FF")){
	 			message.setSubject("Cureatr || FireFox Browser || Automation Test Report || "+SubFolderName);
	 		}
	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("PFB... Test Reports. This message generated by Automation Scripts.");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         String[] Attachments=AttachmentPaths.split("&");
	         for(int i=1; i<Attachments.length; i++){
				 addAttachment(multipart, Attachments[i]);
	         }
			 addAttachment(multipart, ResultsPath);
			 //addAttachment(multipart, "D:/CureatrAutomationWorkSpace/CureatrApp/src/com/cureatr/excel/OutputFiles/2016-02-25/FFTestResults_2016-02-25-14-22/Results.xls");
	         //addAttachment(multipart, "D:/CureatrAutomationWorkSpace/CureatrApp/src/com/cureatr/excel/OutputFiles/2016-02-25/FFTestResults_2016-02-25-14-22/Web_MESSAGING.xls");
	         
	         /*
	         messageBodyPart = new MimeBodyPart();
	         String filename = "D:/CureatrAutomationWorkSpace/CureatrApp/src/com/cureatr/excel/OutputFiles/2016-02-25/FFTestResults_2016-02-25-14-22/Results.xls";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
			   */      
	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);
	       } catch (MessagingException e) {
	         throw new RuntimeException(e);
	       }
	   }
	   private void addAttachment(Multipart multipart, String filename) throws MessagingException{
	       DataSource source = new FileDataSource(filename);
	       BodyPart messageBodyPart = new MimeBodyPart();        
	       messageBodyPart.setDataHandler(new DataHandler(source));
	       messageBodyPart.setFileName(filename);
	       multipart.addBodyPart(messageBodyPart);
	}
	  
}
