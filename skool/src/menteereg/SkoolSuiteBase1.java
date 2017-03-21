
package menteereg;

import java.io.IOException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import testSuiteBase.SuiteBase;
import utility.Read_XLS;
import utility.SuiteUtility;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

public class SkoolSuiteBase1 extends SuiteBase{	
	
	
	Read_XLS FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String test = null;
	String completerevhotelnameText;
	String completerevroomtypeText;
	String completerevcheckinText;
	String completerevcheckoutText;
	String completerevLocationText;
	String completerevCheckinTimeText;
    String completerevNumberofRoomsText;
    String completerevNumberofNightsText;
    
	String completehotelBKGREGNumberText;
	String completehotelNameText;
	String completehotelCheckinText;
	String completehotelCheckoutText;
	String completehotelRoomTypeText;
	String completehotelAddressText;
    String completeCheckinTimeText;
    String completeNumberofNightsText;
    String completeNumberofRoomsText;
    String username=getrandomusername();
    String firstname=getrandomfirstname();
	//This function will be executed before SuiteTwo's test cases to check SuiteToRun flag.
	@BeforeSuite
	public void checkSuiteToRun() throws IOException{
		//Called init() function from SuiteBase class to Initialize .xls Files
		init();	
		//To set TestSuiteList.xls file's path In FilePath Variable.
		FilePath = TestSuiteListExcel;
		SheetName = "SuitesList";
		SuiteName = "menteereg";
		ToRunColumnName = "SuiteToRun";
		//If SuiteToRun !== "y" then SuiteTwo will be skipped from execution.
		if(!SuiteUtility.checkToRunUtility(FilePath, SheetName,ToRunColumnName,SuiteName)){
			
			//To report SuiteTwo as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = N.
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Skipped/Executed", SuiteName, "Skipped");
			
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(SuiteName+"'s SuiteToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		//To report SuiteTwo as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		SuiteUtility.WriteResultUtility(FilePath, SheetName, "Skipped/Executed", SuiteName, "Executed");
	}
	
	

	
	public static String getrandomfirstname() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
     return "MentorFname" +output;
	

	}
	
	public void NewMenteeSign_up(){
		try{
			driver.findElement(By.xpath(Object.getProperty("Click_sign-in"))).click(); 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath(Object.getProperty("click_reg"))).click();  
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath(Object.getProperty("select_mentee"))).click();  
			Thread.sleep(10000);
			driver.findElement(By.xpath(Object.getProperty("click_signup"))).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("NewMenteeSign_up-- successful");
		}catch(Throwable e){
			Add_Log.error("NewMenteeSign_up-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void NewMenteeBasicInformation(){
		try{
			driver.findElement(By.xpath(Object.getProperty("First_Name"))).sendKeys("TestFirstName");
			driver.findElement(By.xpath(Object.getProperty("Last_Name"))).sendKeys("TestFirstName");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Gender")))).selectByVisibleText("M");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Month")))).selectByVisibleText("Apr");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Date")))).selectByVisibleText("7");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_year")))).selectByVisibleText("1986");
			
			driver.findElement(By.xpath(Object.getProperty("Enter_UserName"))).sendKeys(username);
			driver.findElement(By.xpath(Object.getProperty("Enter_EmailId"))).sendKeys("prklgc@gmail.com");
			//driver.findElement(By.xpath(Object.getProperty("Enter_Confirmemail"))).sendKeys("prklgc1@gmail.com");
			//driver.findElement(By.xpath(Object.getProperty("Enter_Parentemail"))).sendKeys("parentmenteeeemail@gmail.com");
			
			driver.findElement(By.xpath(Object.getProperty("Enter_Mentee_Password"))).sendKeys("TestPa55w0rd");
			driver.findElement(By.xpath(Object.getProperty("Enter_Mentee_ConfirmPass"))).sendKeys("TestPa55w0rd");
			driver.findElement(By.xpath(Object.getProperty("Enter_Zipcode"))).sendKeys("89076");
			driver.findElement(By.xpath(Object.getProperty("Enter_Mobile"))).sendKeys("(498)345-4566");
			driver.findElement(By.xpath(Object.getProperty("Enter_SkypeID"))).sendKeys("testskype");
			driver.findElement(By.xpath(Object.getProperty("click_choosefile"))).sendKeys("/home/kamalesh/Desktop/photo.jpg");
			Thread.sleep(2000);
			driver.findElement(By.xpath(Object.getProperty("click_upload"))).click(); 
			
			
			
			Thread.sleep(300);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("NewMenteeBasicInformation-- successful");
		}catch(Throwable e){
			Add_Log.error("NewMenteeBasicInformation-- failed");
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getrandomusername() {

		Random random = new Random();
		int number = random.nextInt(1000);

		String randoms = "MenteeTest" + number ;
		return randoms;

	}
	
	public void EducationInformation(){
		try{
			
			driver.findElement(By.xpath(Object.getProperty("Enter_SchoolName"))).sendKeys("TestSchool");
			
			 new Select (driver.findElement(By.xpath(Object.getProperty("Select_Country")))).selectByVisibleText("USA");
			    Thread.sleep(1000);
			    new Select (driver.findElement(By.xpath(Object.getProperty("Select_State")))).selectByVisibleText("Alaska");
			    Thread.sleep(1000);
			    new Select (driver.findElement(By.xpath(Object.getProperty("Select_City")))).selectByVisibleText("Anchorage");
			    Thread.sleep(1000);
			    new Select (driver.findElement(By.xpath(Object.getProperty("Select_Yr_Graduation")))).selectByVisibleText("2018");
			
			Thread.sleep(300);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("EducationInformation-- successful");
		}catch(Throwable e){
			Add_Log.error("EducationInformation-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void areasNeedingformentoring(){
		try{
			driver.findElement(By.xpath(Object.getProperty("Click_Area_Mentor"))).click(); 
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Study")))).selectByVisibleText("Applied Sciences");
			driver.findElement(By.xpath(Object.getProperty("Click_Specialization"))).click(); 
			
			driver.findElement(By.xpath(Object.getProperty("Enter_AboutMe"))).sendKeys("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath(Object.getProperty("Click_Condition"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Click_CreateAc"))).click(); 
			Add_Log.info("areasNeedingformentoring-- successful");
		}catch(Throwable e){
			Add_Log.error("areasNeedingformentoring-- failed");
			throw new RuntimeException(e);
		}
	}
	
	
	public String searchEmail(String host, String port, String userName,
            String password, final String keyword){
		System.out.println("coming here");
		 String s1="http://192.168.1.190/";
		try{

	        Properties properties = new Properties();
	 

	        properties.put("mail.imap.host", host);
	        properties.put("mail.imap.port", port);
	 
	       
	        properties.setProperty("mail.imap.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty("mail.imap.socketFactory.fallback", "false");
	        properties.setProperty("mail.imap.socketFactory.port",
	                String.valueOf(port));
	 
	        Session session = Session.getDefaultInstance(properties);
	 
	        try {
	        
	            Store store = session.getStore("imap");
	            store.connect(userName, password);
	 
	   
	            Folder folderInbox = store.getFolder("INBOX");
	            folderInbox.open(Folder.READ_ONLY);
	 
	          
	            SearchTerm searchCondition = new SearchTerm() {
	                @Override
	                public boolean match(Message message) {
	                    try {
	                        if (message.getSubject().equalsIgnoreCase(keyword)) {
	                            return true;
	                        }
	                    } catch (MessagingException ex) {
	                        ex.printStackTrace();
	                    }
	                    return false;
	                }
	            };
	            Message[] messages = folderInbox.search(searchCondition);   
	    	    Arrays.sort( messages, ( m1, m2 ) -> {
	    	      try {
	    	        return m2.getSentDate().compareTo( m1.getSentDate() );
	    	      } catch ( MessagingException e ) {
	    	        throw new RuntimeException( e );
	    	      }
	    	    } );

	    	 
	                Message message = messages[0];
                    s1=s1.concat(message.getContent().toString().substring(92,121));
                folderInbox.close(false);
	            store.close();
	        } catch (NoSuchProviderException ex) {
	            
	            ex.printStackTrace();
	        } catch (MessagingException ex) {
	            
	            ex.printStackTrace();
	        }
	    return s1;
		}catch(Throwable e){
			Add_Log.error("fetching mail-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void Loginafteremailfetch(){
		try{
			
			driver.findElement(By.xpath(Object.getProperty("Enter_Activation_UserName"))).sendKeys(username);
			
			driver.findElement(By.xpath(Object.getProperty("Enter_Activation_LastName"))).sendKeys("TestPa55w0rd");
			driver.findElement(By.xpath(Object.getProperty("Click_Activation_Login"))).click(); 
			
			Thread.sleep(300);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("Loginafteremailfetch-- successful");
		}catch(Throwable e){
			Add_Log.error("Loginafteremailfetch-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void ViewEditProfile(){
		try{
			
			
			driver.findElement(By.xpath(Object.getProperty("clickeditprofile"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Get_FirstName"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Get_FirstName"))).sendKeys(firstname);
			driver.findElement(By.xpath(Object.getProperty("Get_LastName"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Get_LastName"))).sendKeys("Manteelastname");
    		new Select (driver.findElement(By.xpath(Object.getProperty("Get_Gender")))).selectByVisibleText("F");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Month")))).selectByVisibleText("Aug");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Date")))).selectByVisibleText("10");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Year")))).selectByVisibleText("1990");
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Zipcode"))).clear();
			Thread.sleep(100);
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Zipcode"))).sendKeys("89078");
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Phone"))).clear();
			Thread.sleep(100);
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Phone"))).sendKeys("(498)345-4676");
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Skype"))).clear();
			Thread.sleep(100);
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Skype"))).sendKeys("edittestskype");
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_SchoolName"))).clear();
			Thread.sleep(100);
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_SchoolName"))).sendKeys("TestSchool1");
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Year_Grd")))).selectByVisibleText("2020");
	   	    new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Country")))).selectByVisibleText("USA");
		    Thread.sleep(1000);
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_State")))).selectByVisibleText("Alaska");
			Thread.sleep(1000);
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_City1")))).selectByVisibleText("Anchorage");
			Thread.sleep(1000);
			driver.findElement(By.xpath(Object.getProperty("Click_Edit_Finances"))).click(); 
			new Select (driver.findElement(By.xpath(Object.getProperty("Select_Edit_Business")))).selectByVisibleText("Business");
			driver.findElement(By.xpath(Object.getProperty("Click_Edit_Accounting"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Aboume"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Enter_Edit_Aboume"))).sendKeys("This is all about me ");
			driver.findElement(By.xpath(Object.getProperty("Save_Edit"))).click(); 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("ViewEditProfile-- successful");
		}catch(Throwable e){
			Add_Log.error("ViewEditProfile-- failed");
			throw new RuntimeException(e);
		}
	}
	
	
	public void logoutandlogin(){
		try{
			
			
			driver.findElement(By.xpath(Object.getProperty("click_to_logout"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("logout"))).click(); 
			Thread.sleep(1000);
			driver.findElement(By.xpath(Object.getProperty("Click_Sign_in"))).click(); 
			Thread.sleep(200);
			driver.findElement(By.xpath(Object.getProperty("Enter_UserName"))).sendKeys(username);
			driver.findElement(By.xpath(Object.getProperty("Enter_Password"))).sendKeys("TestPa55w0rd");
			driver.findElement(By.xpath(Object.getProperty("Click_Login"))).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    Add_Log.info("logoutandlogin-- successful");
		}catch(Throwable e){
			Add_Log.error("logoutandlogin-- failed");
			throw new RuntimeException(e);
		}
	}
	
	
	public void Menteesearchmentorbyfirstname(){
		try{
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_SearchMentor"))).click(); 
			
			
			driver.findElement(By.xpath(Object.getProperty("Search_FirstName"))).sendKeys("MentorTest631");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Search_FirstName"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Search_LastName"))).sendKeys("Mantorlastname");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click(); 
			
			driver.findElement(By.xpath(Object.getProperty("Search_LastName"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Search_City"))).sendKeys("American Canyon");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			
			driver.findElement(By.xpath(Object.getProperty("Search_City"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Search_State"))).sendKeys("California");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			
			driver.findElement(By.xpath(Object.getProperty("Search_State"))).clear();
			driver.findElement(By.xpath(Object.getProperty("Search_Select_Type"))).sendKeys("Student");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			Thread.sleep(200);
		
			driver.findElement(By.xpath(Object.getProperty("Search_Select_Type"))).sendKeys("Professional");
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			
			driver.findElement(By.xpath(Object.getProperty("Search_Click_On_Demand_1_hr"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			
			driver.findElement(By.xpath(Object.getProperty("Search_Click_3_Month"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
			
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Both"))).click(); 
			driver.findElement(By.xpath(Object.getProperty("Search_Click_Go"))).click();
     		Thread.sleep(200);
			
     		
     		
     		
			
		    Add_Log.info("Menteesearchmentorbyfirstname-- successful");
		}catch(Throwable e){
			Add_Log.error("Menteesearchmentorbyfirstname-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void Menteesearchadvanced(){
		try{
					
     		 
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
	    	int var_ele_size= driver.findElements(By.cssSelector("#edit-areaofmentoring-1")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-1")).get(var_ele_size-1).click();
			
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-1_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
	    	int var_ele_size1= driver.findElements(By.cssSelector("#edit-areaofmentoring-2")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-2")).get(var_ele_size1-1).click();
			
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-2_filter_span > img")).click();
     		
		
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			int var_ele_size11= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).get(var_ele_size11-1).click();
			Thread.sleep(1300);
			int var_ele_size3= driver.findElements(By.cssSelector("#edit-areaofmentoring-3")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-3")).get(var_ele_size3-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-3_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			int var_ele_size12= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).get(var_ele_size12-1).click();
			Thread.sleep(1300);
			int var_ele_size4= driver.findElements(By.cssSelector("#edit-areaofmentoring-4")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-4")).get(var_ele_size4-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-4_filter_span > img")).click();
							
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			int var_ele_size13= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).get(var_ele_size13-1).click();
			Thread.sleep(1300);
			int var_ele_size5= driver.findElements(By.cssSelector("#edit-areaofmentoring-5")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-5")).get(var_ele_size5-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-5_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			int var_ele_size14= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(1) > div > a")).get(var_ele_size14-1).click();
			Thread.sleep(1300);
			int var_ele_size6= driver.findElements(By.cssSelector("#edit-areaofmentoring-6")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-areaofmentoring-6")).get(var_ele_size6-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
			Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-areaofmentoring-6_filter_span > img")).click();
			
    		
		    Add_Log.info("Menteesearchadvanced-- successful");
		}catch(Throwable e){
			Add_Log.error("Menteesearchadvanced-- failed");
			throw new RuntimeException(e);
		}
	}
	
	public void MenteesearchadvancedQualification(){
		try{
	
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
        driver.findElement(By.xpath(Object.getProperty("Adv_Click_Qua_Certificate"))).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		
		driver.findElement(By.xpath(Object.getProperty("Cancel_Certificate"))).click();
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
    	int var_ele_size= driver.findElements(By.cssSelector("#edit-degree-3")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-3")).get(var_ele_size-1).click();
		
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-3_filter_span > img")).click();
		
		
		
		
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size1= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size1-1).click();
		Thread.sleep(1300);
		int var_ele_size2= driver.findElements(By.cssSelector("#edit-degree-4")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-4")).get(var_ele_size2-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-4_filter_span > img")).click();
		
		
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size11= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size11-1).click();
		Thread.sleep(1300);
		int var_ele_size3= driver.findElements(By.cssSelector("#edit-degree-5")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-5")).get(var_ele_size3-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-5_filter_span > img")).click();
		
		
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size12= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size12-1).click();
		Thread.sleep(1300);
		int var_ele_size4= driver.findElements(By.cssSelector("#edit-degree-6")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-6")).get(var_ele_size4-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-6_filter_span > img")).click();
		
		//md
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size13= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size13-1).click();
		Thread.sleep(1300);
		int var_ele_size5= driver.findElements(By.cssSelector("#edit-degree-7")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-7")).get(var_ele_size5-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-7_filter_span > img")).click();
		
		//jd
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size14= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size14-1).click();
		Thread.sleep(1300);
		int var_ele_size6= driver.findElements(By.cssSelector("#edit-degree-8")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-8")).get(var_ele_size6-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-8_filter_span > img")).click();
				
		//MBA
		
		driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
		Thread.sleep(100);
		int var_ele_size15= driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#advanceSearchPanel > div:nth-child(2) > div:nth-child(1) > a")).get(var_ele_size15-1).click();
		Thread.sleep(1300);
		int var_ele_size7= driver.findElements(By.cssSelector("#edit-degree-9")).size();
    	Thread.sleep(1300);
		driver.findElements(By.cssSelector("#edit-degree-9")).get(var_ele_size7-1).click();
		driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#edit-degree-9_filter_span > img")).click();
		
		
		
		
		
		
		
		
		
		    Add_Log.info("MenteesearchadvancedQualification-- successful");
		}catch(Throwable e){
			Add_Log.error("MenteesearchadvancedQualification-- failed");
			throw new RuntimeException(e);
		}
	}
	
	
	public void Menteesearchmajor(){
		try{
					
     		
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
		    int var_ele_size8= driver.findElements(By.cssSelector("#edit-fieldofspecialization-1")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-1")).get(var_ele_size8-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-1_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
		    int var_ele_size9= driver.findElements(By.cssSelector("#edit-fieldofspecialization-2")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-2")).get(var_ele_size9-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-2_filter_span > img")).click();
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			
			int var_ele_size16= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size16-1).click();
			Thread.sleep(1300);
			
		    int var_ele_size10= driver.findElements(By.cssSelector("#edit-fieldofspecialization-97")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-97")).get(var_ele_size10-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-97_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size17= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size17-1).click();
			Thread.sleep(1300);
		    int var_ele_size111= driver.findElements(By.cssSelector("#edit-fieldofspecialization-98")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-98")).get(var_ele_size111-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-98_filter_span > img")).click();
			
			
			//communcation

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size24= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size24-1).click();
			Thread.sleep(1300);
		    int var_ele_size25= driver.findElements(By.cssSelector("#edit-fieldofspecialization-3")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-3")).get(var_ele_size25-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-3_filter_span > img")).click();
			
			//counseling

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size26= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size26-1).click();
			Thread.sleep(1300);
		    int var_ele_size27= driver.findElements(By.cssSelector("#edit-fieldofspecialization-90")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-90")).get(var_ele_size27-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-90_filter_span > img")).click();
			
			//Health Science

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size28= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size28-1).click();
			Thread.sleep(1300);
		    int var_ele_size29= driver.findElements(By.cssSelector("#edit-fieldofspecialization-91")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-91")).get(var_ele_size29-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-91_filter_span > img")).click();
			
			//Hospital Management
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size30= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size30-1).click();
			Thread.sleep(1300);
		    int var_ele_size31= driver.findElements(By.cssSelector("#edit-fieldofspecialization-92")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-92")).get(var_ele_size31-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-92_filter_span > img")).click();
			
			//Journlsim
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size32= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size32-1).click();
			Thread.sleep(1300);
		    int var_ele_size33= driver.findElements(By.cssSelector("#edit-fieldofspecialization-93")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-93")).get(var_ele_size33-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-93_filter_span > img")).click();
			
			//justice Studies

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size34= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size34-1).click();
			Thread.sleep(1300);
		    int var_ele_size35= driver.findElements(By.cssSelector("#edit-fieldofspecialization-94")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-94")).get(var_ele_size35-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-94_filter_span > img")).click();
			
			//kienesology

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size36= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size36-1).click();
			Thread.sleep(1300);
		    int var_ele_size37= driver.findElements(By.cssSelector("#edit-fieldofspecialization-95")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-95")).get(var_ele_size37-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-95_filter_span > img")).click();
			
			//Library and infromation science

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size38= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size38-1).click();
			Thread.sleep(1300);
		    int var_ele_size39= driver.findElements(By.cssSelector("#edit-fieldofspecialization-96")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-96")).get(var_ele_size39-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-96_filter_span > img")).click();
			
			//Occupational Theraphy

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size40= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size40-1).click();
			Thread.sleep(1300);
		    int var_ele_size41= driver.findElements(By.cssSelector("#edit-fieldofspecialization-99")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-99")).get(var_ele_size41-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-99_filter_span > img")).click();
			
			//public realtions

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size42= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size42-1).click();
			Thread.sleep(1300);
		    int var_ele_size43= driver.findElements(By.cssSelector("#edit-fieldofspecialization-100")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-100")).get(var_ele_size43-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-100_filter_span > img")).click();
			
			//Recreations

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size44= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size44-1).click();
			Thread.sleep(1300);
		    int var_ele_size45= driver.findElements(By.cssSelector("#edit-fieldofspecialization-101")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-101")).get(var_ele_size45-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-101_filter_span > img")).click();
			
			//Social Work

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size46= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size46-1).click();
			Thread.sleep(1300);
		    int var_ele_size47= driver.findElements(By.cssSelector("#edit-fieldofspecialization-102")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-102")).get(var_ele_size47-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-102_filter_span > img")).click();
			
			//Other
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Applied Sciences");
			int var_ele_size48= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size48-1).click();
			Thread.sleep(1300);
		    int var_ele_size49= driver.findElements(By.cssSelector("#edit-fieldofspecialization-103")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-103")).get(var_ele_size49-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-103_filter_span > img")).click();
			
			
			//Business
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
		    int var_ele_size50= driver.findElements(By.cssSelector("#edit-fieldofspecialization-4")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-4")).get(var_ele_size50-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-4_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
		    int var_ele_size51= driver.findElements(By.cssSelector("#edit-fieldofspecialization-5")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-5")).get(var_ele_size51-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-5_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size52= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size52-1).click();
			Thread.sleep(1300);
		    int var_ele_size53= driver.findElements(By.cssSelector("#edit-fieldofspecialization-6")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-6")).get(var_ele_size53-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-6_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size54= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size54-1).click();
			Thread.sleep(1300);
		    int var_ele_size55= driver.findElements(By.cssSelector("#edit-fieldofspecialization-7")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-7")).get(var_ele_size55-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-7_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size56= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size56-1).click();
			Thread.sleep(1300);
		    int var_ele_size57= driver.findElements(By.cssSelector("#edit-fieldofspecialization-8")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-8")).get(var_ele_size57-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-8_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size58= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size58-1).click();
			Thread.sleep(1300);
		    int var_ele_size59= driver.findElements(By.cssSelector("#edit-fieldofspecialization-9")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-9")).get(var_ele_size59-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-9_filter_span > img")).click();
			
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size60= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size60-1).click();
			Thread.sleep(1300);
		    int var_ele_size61= driver.findElements(By.cssSelector("#edit-fieldofspecialization-10")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-10")).get(var_ele_size61-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-10_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Business");
			int var_ele_size62= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size62-1).click();
			Thread.sleep(1300);
		    int var_ele_size63= driver.findElements(By.cssSelector("#edit-fieldofspecialization-11")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-11")).get(var_ele_size63-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-11_filter_span > img")).click();
			
			
			//Education
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
		    int var_ele_size64= driver.findElements(By.cssSelector("#edit-fieldofspecialization-12")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-12")).get(var_ele_size64-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-12_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
		    int var_ele_size65= driver.findElements(By.cssSelector("#edit-fieldofspecialization-13")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-13")).get(var_ele_size65-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-13_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size66= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size66-1).click();
			Thread.sleep(1300);
		    int var_ele_size67= driver.findElements(By.cssSelector("#edit-fieldofspecialization-15")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-15")).get(var_ele_size67-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-15_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size68= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
	    	driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size68-1).click();
			Thread.sleep(1300);
		    int var_ele_size69= driver.findElements(By.cssSelector("#edit-fieldofspecialization-16")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-16")).get(var_ele_size69-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-16_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size70= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size70-1).click();
			Thread.sleep(1300);
		    int var_ele_size71= driver.findElements(By.cssSelector("#edit-fieldofspecialization-17")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-17")).get(var_ele_size71-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-17_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size72= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size72-1).click();
			Thread.sleep(1300);
		    int var_ele_size73= driver.findElements(By.cssSelector("#edit-fieldofspecialization-18")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-18")).get(var_ele_size73-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-18_filter_span > img")).click();
			
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size74= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size74-1).click();
			Thread.sleep(1300);
		    int var_ele_size75= driver.findElements(By.cssSelector("#edit-fieldofspecialization-19")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-19")).get(var_ele_size75-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-19_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Education");
			int var_ele_size76= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size76-1).click();
			Thread.sleep(1300);
		    int var_ele_size77= driver.findElements(By.cssSelector("#edit-fieldofspecialization-20")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-20")).get(var_ele_size77-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-20_filter_span > img")).click();
			
			
			//Engineering
		
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
		    int var_ele_size78= driver.findElements(By.cssSelector("#edit-fieldofspecialization-22")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-22")).get(var_ele_size78-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-22_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
		    int var_ele_size79= driver.findElements(By.cssSelector("#edit-fieldofspecialization-23")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-23")).get(var_ele_size79-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-23_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size80= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size80-1).click();
			Thread.sleep(1300);
		    int var_ele_size81= driver.findElements(By.cssSelector("#edit-fieldofspecialization-24")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-24")).get(var_ele_size81-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-24_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size82= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
	    	driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size82-1).click();
			Thread.sleep(1300);
		    int var_ele_size83= driver.findElements(By.cssSelector("#edit-fieldofspecialization-25")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-25")).get(var_ele_size83-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-25_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size84= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size84-1).click();
			Thread.sleep(1300);
		    int var_ele_size85= driver.findElements(By.cssSelector("#edit-fieldofspecialization-26")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-26")).get(var_ele_size85-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-26_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size86= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size86-1).click();
			Thread.sleep(1300);
		    int var_ele_size87= driver.findElements(By.cssSelector("#edit-fieldofspecialization-27")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-27")).get(var_ele_size87-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-27_filter_span > img")).click();
			
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size88= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size88-1).click();
			Thread.sleep(1300);
		    int var_ele_size89= driver.findElements(By.cssSelector("#edit-fieldofspecialization-28")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-28")).get(var_ele_size89-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-28_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size90= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size90-1).click();
			Thread.sleep(1300);
		    int var_ele_size91= driver.findElements(By.cssSelector("#edit-fieldofspecialization-29")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-29")).get(var_ele_size91-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-29_filter_span > img")).click();
			
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size92= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size92-1).click();
			Thread.sleep(1300);
		    int var_ele_size93= driver.findElements(By.cssSelector("#edit-fieldofspecialization-30")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-30")).get(var_ele_size93-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-30_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size94= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size94-1).click();
			Thread.sleep(1300);
		    int var_ele_size95= driver.findElements(By.cssSelector("#edit-fieldofspecialization-31")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-31")).get(var_ele_size95-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-31_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Engineering");
			int var_ele_size96= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size96-1).click();
			Thread.sleep(1300);
		    int var_ele_size97= driver.findElements(By.cssSelector("#edit-fieldofspecialization-32")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-32")).get(var_ele_size97-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-32_filter_span > img")).click();
			
			
			//Humanities and the Arts
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
		    int var_ele_size98= driver.findElements(By.cssSelector("#edit-fieldofspecialization-33")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-33")).get(var_ele_size98-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-33_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
		    int var_ele_size99= driver.findElements(By.cssSelector("#edit-fieldofspecialization-34")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-34")).get(var_ele_size99-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-34_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size100= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size100-1).click();
			Thread.sleep(1300);
		    int var_ele_size101= driver.findElements(By.cssSelector("#edit-fieldofspecialization-35")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-35")).get(var_ele_size101-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-35_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size102= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
	    	driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size102-1).click();
			Thread.sleep(1300);
		    int var_ele_size103= driver.findElements(By.cssSelector("#edit-fieldofspecialization-36")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-36")).get(var_ele_size103-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-36_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size104= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size104-1).click();
			Thread.sleep(1300);
		    int var_ele_size105= driver.findElements(By.cssSelector("#edit-fieldofspecialization-37")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-37")).get(var_ele_size105-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-37_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size106= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size106-1).click();
			Thread.sleep(1300);
		    int var_ele_size107= driver.findElements(By.cssSelector("#edit-fieldofspecialization-38")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-38")).get(var_ele_size107-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-38_filter_span > img")).click();
			
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size108= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size108-1).click();
			Thread.sleep(1300);
		    int var_ele_size109= driver.findElements(By.cssSelector("#edit-fieldofspecialization-39")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-39")).get(var_ele_size109-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-39_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size110= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size110-1).click();
			Thread.sleep(1300);
		    int var_ele_size112= driver.findElements(By.cssSelector("#edit-fieldofspecialization-40")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-40")).get(var_ele_size112-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-40_filter_span > img")).click();
			
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size113= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size113-1).click();
			Thread.sleep(1300);
		    int var_ele_size114= driver.findElements(By.cssSelector("#edit-fieldofspecialization-41")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-41")).get(var_ele_size114-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-41_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size115= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size115-1).click();
			Thread.sleep(1300);
		    int var_ele_size116= driver.findElements(By.cssSelector("#edit-fieldofspecialization-42")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-42")).get(var_ele_size116-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-42_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size117= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size117-1).click();
			Thread.sleep(1300);
		    int var_ele_size118= driver.findElements(By.cssSelector("#edit-fieldofspecialization-43")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-43")).get(var_ele_size118-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-43_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size119= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size119-1).click();
			Thread.sleep(1300);
		    int var_ele_size120= driver.findElements(By.cssSelector("#edit-fieldofspecialization-44")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-44")).get(var_ele_size120-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-44_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size121= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size121-1).click();
			Thread.sleep(1300);
		    int var_ele_size122= driver.findElements(By.cssSelector("#edit-fieldofspecialization-45")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-45")).get(var_ele_size122-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-45_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size123= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size123-1).click();
			Thread.sleep(1300);
		    int var_ele_size124= driver.findElements(By.cssSelector("#edit-fieldofspecialization-46")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-46")).get(var_ele_size124-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-46_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size125= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size125-1).click();
			Thread.sleep(1300);
		    int var_ele_size126= driver.findElements(By.cssSelector("#edit-fieldofspecialization-47")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-47")).get(var_ele_size126-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-47_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size127= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size127-1).click();
			Thread.sleep(1300);
		    int var_ele_size128= driver.findElements(By.cssSelector("#edit-fieldofspecialization-48")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-48")).get(var_ele_size128-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-48_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size129= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size129-1).click();
			Thread.sleep(1300);
		    int var_ele_size130= driver.findElements(By.cssSelector("#edit-fieldofspecialization-49")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-49")).get(var_ele_size130-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-49_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size131= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size131-1).click();
			Thread.sleep(1300);
		    int var_ele_size132= driver.findElements(By.cssSelector("#edit-fieldofspecialization-50")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-50")).get(var_ele_size132-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-50_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size133= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size133-1).click();
			Thread.sleep(1300);
		    int var_ele_size134= driver.findElements(By.cssSelector("#edit-fieldofspecialization-51")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-51")).get(var_ele_size134-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-51_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size135= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size135-1).click();
			Thread.sleep(1300);
		    int var_ele_size136= driver.findElements(By.cssSelector("#edit-fieldofspecialization-52")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-52")).get(var_ele_size136-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-52_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Humanities and the Arts");
			int var_ele_size137= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size137-1).click();
			Thread.sleep(1300);
		    int var_ele_size138= driver.findElements(By.cssSelector("#edit-fieldofspecialization-53")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-53")).get(var_ele_size138-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-53_filter_span > img")).click();
			
			
			
			//Science
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
		    int var_ele_size139= driver.findElements(By.cssSelector("#edit-fieldofspecialization-54")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-54")).get(var_ele_size139-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-54_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
		    int var_ele_size140= driver.findElements(By.cssSelector("#edit-fieldofspecialization-55")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-55")).get(var_ele_size140-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-55_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size141= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size141-1).click();
			Thread.sleep(1300);
		    int var_ele_size142= driver.findElements(By.cssSelector("#edit-fieldofspecialization-56")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-56")).get(var_ele_size142-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-56_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size143= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
	    	driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size143-1).click();
			Thread.sleep(1300);
		    int var_ele_size144= driver.findElements(By.cssSelector("#edit-fieldofspecialization-57")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-57")).get(var_ele_size144-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-57_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size145= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size145-1).click();
			Thread.sleep(1300);
		    int var_ele_size146= driver.findElements(By.cssSelector("#edit-fieldofspecialization-58")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-58")).get(var_ele_size146-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-58_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size147= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size147-1).click();
			Thread.sleep(1300);
		    int var_ele_size148= driver.findElements(By.cssSelector("#edit-fieldofspecialization-59")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-59")).get(var_ele_size148-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-59_filter_span > img")).click();
			
			
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size149= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size149-1).click();
			Thread.sleep(1300);
		    int var_ele_size150= driver.findElements(By.cssSelector("#edit-fieldofspecialization-60")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-60")).get(var_ele_size150-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-60_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size151= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size151-1).click();
			Thread.sleep(1300);
		    int var_ele_size152= driver.findElements(By.cssSelector("#edit-fieldofspecialization-61")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-61")).get(var_ele_size152-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-61_filter_span > img")).click();
			
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size153= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size153-1).click();
			Thread.sleep(1300);
		    int var_ele_size154= driver.findElements(By.cssSelector("#edit-fieldofspecialization-62")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-62")).get(var_ele_size154-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-62_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size155= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size155-1).click();
			Thread.sleep(1300);
		    int var_ele_size156= driver.findElements(By.cssSelector("#edit-fieldofspecialization-63")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-63")).get(var_ele_size156-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-63_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size157= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size157-1).click();
			Thread.sleep(1300);
		    int var_ele_size158= driver.findElements(By.cssSelector("#edit-fieldofspecialization-64")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-64")).get(var_ele_size158-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-64_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size159= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size159-1).click();
			Thread.sleep(1300);
		    int var_ele_size160= driver.findElements(By.cssSelector("#edit-fieldofspecialization-65")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-65")).get(var_ele_size160-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-65_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Science");
			int var_ele_size161= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size161-1).click();
			Thread.sleep(1300);
		    int var_ele_size162= driver.findElements(By.cssSelector("#edit-fieldofspecialization-66")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-66")).get(var_ele_size162-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-66_filter_span > img")).click();
			
			
			//Social Science
			

			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
		    int var_ele_size18= driver.findElements(By.cssSelector("#edit-fieldofspecialization-67")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-67")).get(var_ele_size18-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-67_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
		    int var_ele_size19= driver.findElements(By.cssSelector("#edit-fieldofspecialization-68")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-68")).get(var_ele_size19-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-68_filter_span > img")).click();
			
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size163= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size163-1).click();
			Thread.sleep(1300);
		    int var_ele_size164= driver.findElements(By.cssSelector("#edit-fieldofspecialization-69")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-69")).get(var_ele_size164-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-69_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size165= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size165-1).click();
			Thread.sleep(1300);
		    int var_ele_size166= driver.findElements(By.cssSelector("#edit-fieldofspecialization-70")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-70")).get(var_ele_size166-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-70_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size167= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size167-1).click();
			Thread.sleep(1300);
		    int var_ele_size168= driver.findElements(By.cssSelector("#edit-fieldofspecialization-71")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-71")).get(var_ele_size168-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-71_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size169= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size169-1).click();
			Thread.sleep(1300);
		    int var_ele_size170= driver.findElements(By.cssSelector("#edit-fieldofspecialization-72")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-72")).get(var_ele_size170-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-72_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size171= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size171-1).click();
			Thread.sleep(1300);
		    int var_ele_size172= driver.findElements(By.cssSelector("#edit-fieldofspecialization-73")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-73")).get(var_ele_size172-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-73_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size173= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size173-1).click();
			Thread.sleep(1300);
		    int var_ele_size174= driver.findElements(By.cssSelector("#edit-fieldofspecialization-74")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-74")).get(var_ele_size174-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-74_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size175= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size175-1).click();
			Thread.sleep(1300);
		    int var_ele_size176= driver.findElements(By.cssSelector("#edit-fieldofspecialization-75")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-75")).get(var_ele_size176-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-75_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size177= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size177-1).click();
			Thread.sleep(1300);
		    int var_ele_size178= driver.findElements(By.cssSelector("#edit-fieldofspecialization-76")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-76")).get(var_ele_size178-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-76_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size179= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size179-1).click();
			Thread.sleep(1300);
		    int var_ele_size180= driver.findElements(By.cssSelector("#edit-fieldofspecialization-77")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-77")).get(var_ele_size180-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-77_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size181= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size181-1).click();
			Thread.sleep(1300);
		    int var_ele_size182= driver.findElements(By.cssSelector("#edit-fieldofspecialization-78")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-78")).get(var_ele_size182-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-78_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size183= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size183-1).click();
			Thread.sleep(1300);
		    int var_ele_size184= driver.findElements(By.cssSelector("#edit-fieldofspecialization-79")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-79")).get(var_ele_size184-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-79_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size185= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size185-1).click();
			Thread.sleep(1300);
		    int var_ele_size186= driver.findElements(By.cssSelector("#edit-fieldofspecialization-80")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-80")).get(var_ele_size186-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-80_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size187= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size187-1).click();
			Thread.sleep(1300);
		    int var_ele_size188= driver.findElements(By.cssSelector("#edit-fieldofspecialization-81")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-81")).get(var_ele_size188-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-81_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size189= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size189-1).click();
			Thread.sleep(1300);
		    int var_ele_size190= driver.findElements(By.cssSelector("#edit-fieldofspecialization-82")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-82")).get(var_ele_size190-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-82_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size191= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size191-1).click();
			Thread.sleep(1300);
		    int var_ele_size192= driver.findElements(By.cssSelector("#edit-fieldofspecialization-83")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-83")).get(var_ele_size192-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-83_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size193= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size193-1).click();
			Thread.sleep(1300);
		    int var_ele_size194= driver.findElements(By.cssSelector("#edit-fieldofspecialization-84")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-84")).get(var_ele_size194-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-84_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size195= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size195-1).click();
			Thread.sleep(1300);
		    int var_ele_size196= driver.findElements(By.cssSelector("#edit-fieldofspecialization-85")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-85")).get(var_ele_size196-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-85_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size197= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size197-1).click();
			Thread.sleep(1300);
		    int var_ele_size198= driver.findElements(By.cssSelector("#edit-fieldofspecialization-86")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-86")).get(var_ele_size198-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-86_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size199= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size199-1).click();
			Thread.sleep(1300);
		    int var_ele_size200= driver.findElements(By.cssSelector("#edit-fieldofspecialization-87")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-87")).get(var_ele_size200-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-87_filter_span > img")).click();
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size201= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size201-1).click();
			Thread.sleep(1300);
		    int var_ele_size202= driver.findElements(By.cssSelector("#edit-fieldofspecialization-88")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-88")).get(var_ele_size202-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-88_filter_span > img")).click();
			
			
			driver.findElement(By.xpath(Object.getProperty("Click_AdvanceSearch"))).click();
			Thread.sleep(100);
			new Select (driver.findElement(By.cssSelector("#edit-fieldofstudy"))).selectByVisibleText("Social Science");
			int var_ele_size203= driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#fieldOfSpecializationCon > a")).get(var_ele_size203-1).click();
			Thread.sleep(1300);
		    int var_ele_size204= driver.findElements(By.cssSelector("#edit-fieldofspecialization-89")).size();
	    	Thread.sleep(1300);
			driver.findElements(By.cssSelector("#edit-fieldofspecialization-89")).get(var_ele_size204-1).click();
			driver.findElement(By.xpath(Object.getProperty("Adv_Click_Go"))).click();
		    Thread.sleep(200);
			driver.findElement(By.cssSelector("#edit-fieldofspecialization-89_filter_span > img")).click();
			
				
		    Add_Log.info("Menteesearchmajor-- successful");
		}catch(Throwable e){
			Add_Log.error("Menteesearchmajor-- failed");
			throw new RuntimeException(e);
		}
	}
	

}