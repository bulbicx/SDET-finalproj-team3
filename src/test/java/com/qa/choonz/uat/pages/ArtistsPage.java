package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtistsPage {
	
	public final String url = "http://127.0.0.1:5500/artists.html";
	
	@FindBy(xpath="//*[@id=\"card-group\"]/div/img")
	public WebElement firstCard;
	
	public void clickCard() {
		firstCard.click();
	}

}
