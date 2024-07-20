package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Utilities;


//  2:14 




public class Register {
WebDriver driver;

@BeforeMethod
public void setup(){
	
	String browserName ="edge";
	if (browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
		
	}else if (browserName.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
		
	}
	else if (browserName.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
		
	}
	//driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	driver.get("https://tutorialsninja.com/demo");
	driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
	driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
}

@AfterMethod
public void tearDown() {
	driver.quit();
}


	@Test(priority=2)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
	    
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("ravinder");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("kumar");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("ravinder"+Utilities.generateTimeStamp()+"@gmail.com");
		
		
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("123456789");
		
		
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		String AccountCreatedMsg =  driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
		Assert.assertEquals("Your Account Has Been Created!", AccountCreatedMsg, "account creation success message not found");
		//Hint: Assert.assertEquals(ActualValue, ExpectedValue , optional message on fail )
		 
	}
	
	@Test(priority=1)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		
		String actualPrivacyPolicyWarning=  driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedPrivacyPolicyWarning= "Warning: You must agree to the Privacy Policy!" ;
		Assert.assertEquals(actualPrivacyPolicyWarning, expectedPrivacyPolicyWarning, "msg not matching");
		
		String actualFirstNameWarning =  driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String expectedFirstNameWarning="First Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualFirstNameWarning, expectedFirstNameWarning, "msg not matching");
		
		 
	}
}
