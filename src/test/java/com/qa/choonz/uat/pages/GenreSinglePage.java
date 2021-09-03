package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenreSinglePage {
	
	public final String url = "http://localhost:8082/genresingle.html?id=1";
	
	@FindBy(xpath="//*[@id=\"album-table-body\"]/tr/td[2]")
	public WebElement firstAlbum;
	
	@FindBy(xpath="//*[@id=\"album-table-body\"]/tr/td[3]")
	public WebElement firstArtist;
	
	public void clickAlbumName() {
		firstAlbum.click();
	}
	
	public void clickArtistName() {
		firstArtist.click();
	}

}
