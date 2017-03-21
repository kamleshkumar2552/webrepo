package menteereg;
	import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import org.testng.asserts.Assertion;
    import utility.Read_XLS;
	import utility.SuiteUtility;

	//SuiteTwoCaseOne Class Inherits From SuiteTwoBase Class.
	//So, SuiteTwoCaseOne Class Is Child Class Of SuiteTwoBase Class And SuiteBase Class.
	public class MenteeSignup extends SkoolSuiteBase1{
		
		Read_XLS FilePath = null;	
		String SheetName = null;
		String TestCaseName = null;	
		String ToRunColumnNameTestCase = null;
		String ToRunColumnNameTestData = null;
		String TestDataToRun[]=null;
		static boolean TestCasePass=true;
		static int DataSet=-1;	
		static boolean Testskip=false;
		static boolean Testfail=false;
		Assertion assertionObj = null;
		
		@BeforeTest
		public void checkCaseToRun() throws IOException{
			//Called init() function from SuiteBase class to Initialize .xls Files
			init();	
			
			//To set SuiteTwo.xls file's path In FilePath Variable.
			FilePath = TestCaseListExcelmenteeregistration1;	
			TestCaseName = this.getClass().getSimpleName();
			System.out.println("TestCaseName1::" + TestCaseName);
			
			//SheetName to check CaseToRun flag against test case.
			SheetName = "TestCasesList";
			//Name of column In TestCasesList Excel sheet.
			ToRunColumnNameTestCase = "CaseToRun";
			//Name of column In Test Case Data sheets.
			ToRunColumnNameTestData = "DataToRun";
			
			//To check test case's CaseToRun = Y or N In related excel sheet.
			//If CaseToRun = N or blank, Test case will skip execution. Else It will be executed.
			if(!SuiteUtility.checkToRunUtility(FilePath, SheetName,ToRunColumnNameTestCase,TestCaseName))
			{			
				
				//To report result as skip for test cases In TestCasesList sheet.
				SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "SKIP");
				
				//To throw skip exception for this test case.
				throw new SkipException(TestCaseName+"'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+TestCaseName);
			}
			//To retrieve DataToRun flags of all data set lines from related test data sheet.
		
			TestDataToRun = SuiteUtility.checkToRunUtilityOfData(FilePath, TestCaseName, ToRunColumnNameTestData);
			
		}
		
			
		//Accepts 4 column's String data In every Iteration.
		@Test(dataProvider="EmpLoginTestsData",priority=1)
		public void hotelsGuest(String DataCol1,String DataCol2,String DataCol3,String ExpectedResult){
			
			DataSet++;		
			
			//If found DataToRun = "N" for data set then execution will be skipped for that data set.
			if(!TestDataToRun[DataSet].equalsIgnoreCase("Y")){
				//If DataToRun = "N", Set Testskip=true.
				Testskip=true;
				throw new SkipException("DataToRun for row number "+DataSet+" Is No Or Blank. So Skipping Its Execution.");
			}
			
			//If found DataToRun = "Y" for data set then bellow given lines will be executed.
					
			loadWebBrowser();
			driver.get(Param.getProperty("siteURL"));
			assertionObj = new Assertion();
			try{
				//this way data from excell can be accessed.I am only printing but these data items can be used anywhere
				//in this test
				System.out.println(DataCol1);
				System.out.println(DataCol2);
				System.out.println(DataCol3);
				System.out.println(ExpectedResult);
				NewMenteeSign_up();
				NewMenteeBasicInformation();
				EducationInformation();
				areasNeedingformentoring();     
				Thread.sleep(1000);		
			}catch(Exception | AssertionError e){
				Testfail=true;
				assertionObj.fail(e.getMessage());
			}
		}
		
	    @Test (description="Gmail fetch",priority=2)
		    public void EmailVarification() throws Exception
		    {
		    	try{
			    String host = "imap.gmail.com";
		        String port = "993";
		        String userName = "prklgc@gmail.com";
		        String password = "foramazon";
		        String keyword = "Confirm Account Activation";
			    String s1= searchEmail(host, port, userName, password, keyword);
		       
		        Thread.sleep(5000);
			 
			    loadWebBrowser();
				driver.get(s1);
			}catch(Exception | AssertionError e){
					Testfail=true;
					assertionObj.fail(e.getMessage());
				}
			
		    }
		 @Test (description="mail fetch framework",priority=3)
		 public void LoginAftermailvarification() throws Exception
		    {
		    	try{
		    
		    		Loginafteremailfetch();
		    		Thread.sleep(1500);
		    		ViewEditProfile();
		    		logoutandlogin();
		    		Menteesearchmentorbyfirstname();
		    		Menteesearchadvanced();
		    		MenteesearchadvancedQualification();
		    		Menteesearchmajor();
		   		}catch(Exception | AssertionError e){
					Testfail=true;
					assertionObj.fail(e.getMessage());
				}
			
		    }
		
		//@AfterMethod method will be executed after execution of @Test method every time.
		@AfterMethod
		public void reporterDataResults(){	
			if(Testskip){
				//If found Testskip = true, Result will be reported as SKIP against data set line In excel sheet.
				SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "SKIP");
			}	
			else if(Testfail){
				//To make object reference null after reporting In report.
				//Set TestCasePass = false to report test case as fail In excel sheet.
				TestCasePass=false;
				//If found Testfail = true, Result will be reported as FAIL against data set line In excel sheet.
				SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "FAIL");			
			}
			else{
				//If found Testskip = false and Testfail = false, Result will be reported as PASS against data set line In excel sheet.
				SuiteUtility.WriteResultUtility(FilePath, TestCaseName, "Pass/Fail/Skip", DataSet+1, "PASS");
			}
			//At last make both flags as false for next data set.
			Testskip=false;
			Testfail=false;
		}
		
		//This data provider method will return 3 column's data one by one In every Iteration.
		@DataProvider
		public Object[][] EmpLoginTestsData()
		{
			//To retrieve data from Data 1 Column,Data 2 Column and Expected Result column of SuiteTwoCaseOne data Sheet.
			//Last two columns (DataToRun and Pass/Fail/Skip) are Ignored programatically when reading test data.
			
			return SuiteUtility.GetTestDataUtility(FilePath, TestCaseName);
		}

		//To report result as pass or fail for test cases In TestCasesList sheet.
		@AfterTest
		public void closeBrowser(){
			//To Close the web browser at the end of test.
			//closeWebBrowser();
			if(TestCasePass){
				SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "PASS");
			}
			else{
				SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestCaseName, "FAIL");			
			}		
		}
	}


