package app11;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class A {
	/*@Test
	public void varifyTitle()
	{
		WebDriver driver= null;
		
		
		
		
		  File chromeDriver = new File("/usr/lib/chromium-browser/chromedriver");
		    System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		    driver = new ChromeDriver();
		
		driver.get("http://www.terralogic.com/");
		String actual=driver.getTitle();
		Assert.assertEquals(actual, "Terralogic Inc");
		    
		   
		    
	}*/
	
	 @Test (description="Google Login")
	    public void GoogleLogin() throws Exception
	    {
		 
		 WebDriver driver= null;
			
			
			
			
		  File chromeDriver = new File("/usr/lib/chromium-browser/chromedriver");
		    System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		    driver = new ChromeDriver();
		
		 driver.get("http://www.gmail.com");
		 Assert.assertEquals("Sign in", driver.findElement(By.id("signIn")).getAttribute("value"));
		 driver.findElement(By.cssSelector("#Email")).sendKeys("prklgc@gmail.com");
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#next")).click();
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#Passwd")).sendKeys("foramazon");
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#signIn")).click();
		 driver.findElement(By.cssSelector("#gbqfq")).sendKeys("cs-reply@amazon.in");
		 driver.findElement(By.cssSelector("#gbqfb > span")).click();
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);}	    
	    

}
