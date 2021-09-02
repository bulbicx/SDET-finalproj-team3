package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlbumsPage {
	
	public final String url = "http://localhost:8082/albums.html";
	
	@FindBy(xpath="//*[@id=\"card-group\"]/div")
	public WebElement firstCard;
	
	public void clickCard() {
		firstCard.click();
	}

}
