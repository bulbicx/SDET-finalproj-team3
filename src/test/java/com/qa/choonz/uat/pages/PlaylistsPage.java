package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaylistsPage {
	
	public final String url = "http://127.0.0.1:5500/playlists.html";
	
	@FindBy(xpath="/html/body/section/main/div")
	public WebElement firstCard;
	
	public void clickCard() {
		firstCard.click();
	}


}
