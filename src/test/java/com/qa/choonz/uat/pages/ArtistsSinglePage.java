package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtistsSinglePage {
	
	public final String url = "http://127.0.0.1:5500/artistsingle.html?id=1";
	
	@FindBy(xpath="//*[@id=\"album-table-body\"]/tr/td[1]/img")
	public WebElement firstAlbumCover;
	
	@FindBy(xpath="//*[@id=\"album-table-body\"]/tr/td[2]")
	public WebElement firstAlbumName;
	
	@FindBy(xpath="//*[@id=\"album-table-body\"]/tr/td[3]")
	public WebElement firstAlbumGenre;
	
	public void clickAlbumCover() {
		firstAlbumCover.click();
	}
	
	public void clickAlbumName() {
		firstAlbumName.click();
	}
	
	public void clickGenreName() {
		firstAlbumGenre.click();
	}

}
