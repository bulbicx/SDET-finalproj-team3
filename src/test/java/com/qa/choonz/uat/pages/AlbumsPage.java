package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlbumsPage {
	
	public final String url = "http://127.0.0.1:5500/albums.html";
	
	@FindBy(xpath="//*[@id=\"card-group\"]/div")
	public WebElement firstCard;
	
	public void clickCard() {
		firstCard.click();
	}

}
