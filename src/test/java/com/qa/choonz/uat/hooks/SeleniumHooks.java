
package com.qa.choonz.uat.hooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
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
	
	private byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(takeScreenshot(), "image/png", scenario.getName());
		}
	}
	
	@After
	public void teardown() {
		this.driver.quit();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
