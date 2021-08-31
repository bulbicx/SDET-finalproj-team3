package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaylistSinglePage {
	
	public final String url = "http://127.0.0.1:5500/playlistsingle.html?id=1";
	
	@FindBy(xpath="/html/body/section/section/section/div")
	public WebElement firstTrackCard;
	
	public void clickTrackCard() {
		firstTrackCard.click();
	}

}
