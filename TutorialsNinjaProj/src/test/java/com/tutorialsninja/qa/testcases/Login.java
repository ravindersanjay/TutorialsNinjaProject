package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Utilities;

public class Login {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		String browserName = "firefox";
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("ravinderjayalwal@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Expected message not displayed");
		driver.quit();
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='input-email']"))
				.sendKeys("ravinderjayalwal" +  Utilities.generateTimeStamp() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("1234567");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Warning:
		// No match for E-Mail Address and/or Password.')]")).isDisplayed());
		String ExpectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		String ActualMessage = driver
				.findElement(
						By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		Assert.assertEquals(ActualMessage, ExpectedMessage);
		driver.quit();

	}

	
}
