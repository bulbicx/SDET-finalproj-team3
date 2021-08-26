package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenresPage {
	
	public final String url = "http://127.0.0.1:5500/genres.html";
	
	@FindBy(xpath="/html/body/div[2]/div[2]/div/div/img")
	public WebElement firstCard;
	
	public void clickCard() {
		firstCard.click();
	}

}
