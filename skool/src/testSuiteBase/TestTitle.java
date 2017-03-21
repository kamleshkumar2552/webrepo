package testSuiteBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class TestTitle {
	public static WebDriver driver=null;
	public void varifyTitle() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://www.terralogic.com/");
		String actual=driver.getTitle();
		Assert.assertEquals(actual, "Terralogic Inc");
	}

}
