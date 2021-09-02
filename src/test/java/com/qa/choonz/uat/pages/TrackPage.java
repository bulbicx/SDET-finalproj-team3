package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrackPage {
	
	public final String url = "http://localhost:8082/track.html?id=1";
	
	@FindBy(xpath="/html/body/div[2]/div[1]/div[1]/img")
	public WebElement albumCover;
	
	@FindBy(xpath="/html/body/div[2]/div[1]/div[2]/div/img")
	public WebElement artistCover;
	
	public void clickAlbumCover() {
		albumCover.click();
	}
	
	public void clickArtistCover() {
		artistCover.click();
	}
}
