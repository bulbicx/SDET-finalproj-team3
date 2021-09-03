package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlbumSinglePage {
	
	public final String url = "http://localhost:8082/albumsingle.html?id=1";
	
	@FindBy(xpath="//*[@id=\"track-table-body\"]/tr/td[1]")
	public WebElement firstTrackName;
	
	@FindBy(xpath="//*[@id=\"track-table-body\"]/tr/td[2]")
	public WebElement firstTrackDuration;
	
	public void clickTrackName() {
		firstTrackName.click();
	}
	
	public void clickTrackDuration() {
		firstTrackName.click();
	}

}
