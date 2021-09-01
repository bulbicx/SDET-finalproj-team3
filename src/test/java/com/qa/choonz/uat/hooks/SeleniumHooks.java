
package com.qa.choonz.uat.hooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class SeleniumHooks {
	
	private WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
//  Is this needed? Not sure how the home page is set up but if that's also needed we could do it manually? sync issues
//	@Before("@navpages")
//	public void dataSetup() {
//		//code here to insert dummy data for the navigation tests
//	}
//	
	
	@After
	public void teardown(Scenario scenario) {
		
		scenario.attach(takeScreenshot(), "image/png", scenario.getName());
		this.driver.quit();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	private byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
