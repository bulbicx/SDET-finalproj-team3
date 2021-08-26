package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenreSinglePage {
	
public final String url = "http://127.0.0.1:5500/genresingle.html?id=1";
	
	@FindBy(xpath="/html/body/div[2]/div[2]/div/div/img")
	public WebElement firstAlbum;
	
	@FindBy(xpath="/html/body/div[2]/div[2]/div/div/img")
	public WebElement firstArtist;
	
	public void clickAlbumName() {
		firstAlbum.click();
	}
	
	public void clickArtistName() {
		firstArtist.click();
	}

}
