package com.qa.choonz.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

	private byte[] currentScreenshot;
	
	public ScreenshotUtility() { }

	public byte[] takeScreenshot(WebDriver driver) {
		return currentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
