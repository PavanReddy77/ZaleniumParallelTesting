package com.Zalenium.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

public class TestBase 
{
	public static RemoteWebDriver driver;
	public static DesiredCapabilities desiredCapabilities;
	public static Properties property;
	public static URL url;
	
	public TestBase()
	{
		try 
		{
			property = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/Zalenium/Configuration/Configuration.properties");
			property.load(ip);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization(String Browser) throws MalformedURLException
	{
		desiredCapabilities = new DesiredCapabilities();
		if(Browser.equals("Chrome"))
		{
			desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		}
		else if(Browser.equals("Firefox"))
		{
			desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		}
		
		url = new URL("http://192.168.99.100:4444/wd/hub");
		
		driver = new RemoteWebDriver(url, desiredCapabilities);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get(property.getProperty("Url"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver != null)
		{
			driver.quit();
		}
	}
}
