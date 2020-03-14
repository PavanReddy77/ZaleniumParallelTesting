package com.Zalenium.Tests;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Zalenium.BaseClass.TestBase;

public class LoginPageTest extends TestBase
{
	public LoginPageTest()
	{
		super();
	}
	
	@Parameters("Browser")
	@BeforeMethod
	public void setUp(String Browser) throws MalformedURLException
	{
		initialization(Browser);
	}
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{	
		String loginPageTitle = driver.getTitle();
		System.out.println("The Title is ::: " +loginPageTitle);
		Assert.assertEquals(loginPageTitle, "CRMPRO  - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void homePageTitleTest()
	{
		driver.findElement(By.name("username")).sendKeys(property.getProperty("Username"));
		driver.findElement(By.name("password")).sendKeys(property.getProperty("Password"));
		driver.findElement(By.xpath("//input[@class='btn btn-small' and @type='submit']")).click();
		
		String homePageTitle = driver.getTitle();
		System.out.println("The Title is ::: " +homePageTitle);
		Assert.assertEquals(homePageTitle, "CRMPRO");
	}
}
