package com.parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelTesting 
{

	
	
	WebDriver driver=null;
	
	@Parameters("browser")
	@BeforeClass
	public void launchBrowser(String browser)
	{
		
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demo.guru99.com/test/guru99home/");
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("https://demo.guru99.com/test/guru99home/");
		}
		
	}
	
	
	@Test
	public void automate() throws InterruptedException
	{
		try
		{
			
			WebElement home= driver.findElement(By.xpath("//*[@id=\"rt-header\"]/div/div[2]/div/ul/li[1]/a"));
			if(home.isDisplayed())
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", home);
				
				Thread.sleep(5000);	
				home.click();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		System.out.println("Browser closed");
	}
	
	
}//class close
