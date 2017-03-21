package app1;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class A {
	 @Test (description="Google Login")
	    public void GoogleLogin() throws Exception
	    {
		 
		 WebDriver driver= null;
			
			
			
			
		  File chromeDriver = new File("/usr/lib/chromium-browser/chromedriver");
		    System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		    driver = new ChromeDriver();
		/*String s1="http://sandbox.skoolmentor.com/?q=user/activation/akh6r7sUXJ";*/
		driver.get("http://www.gmail.com");
		//driver.get(s1);
		 Assert.assertEquals("Sign in", driver.findElement(By.id("signIn")).getAttribute("value"));
		 driver.findElement(By.cssSelector("#Email")).sendKeys("prklgc@gmail.com");
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#next")).click();
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#Passwd")).sendKeys("foramazon");
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#signIn")).click();
		 /*driver.findElement(By.cssSelector("#gbqfq")).sendKeys("administer@skoolmentor.com");
		 driver.findElement(By.cssSelector("#gbqfb > span")).click();
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#\3a 2 > div")).click();*/
		
		 
	    }	    


}


