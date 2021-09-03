package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PlaylistSinglePage {
	
	public final String url = "http://localhost:8082/playlistsingle.html?id=1";
	
	@FindBy(xpath="/html/body/section/section/section/div")
	public WebElement firstTrackCard;
	
	@FindBy(className = "bi-track")
	private WebElement addTrackIcon;
	
	@FindBy(className = "bi-trash-fill")
	private WebElement deleteTrackIcon;
	
	@FindBy(className = "track-list-dropdown")
	private WebElement trackList;
	
	private Select trackListSelect;
	
	@FindBy(className = "add")
	private WebElement addTrackBtn;
	
	public void clickTrackCard() {
		firstTrackCard.click();
	}
	
	public void clickAddTrackIcon() {
		this.addTrackIcon.click();
	}

	public void pickTrack(String trackName) {
		this.trackListSelect = new Select(trackList);
		this.trackListSelect.selectByVisibleText(trackName);	
	}
	
	public void clickAddTrackBtn() {
		this.addTrackBtn.click();
	}
	
	public void addTrack(String trackName) {
		this.addTrackIcon.click();
		this.trackListSelect = new Select(trackList);
		this.trackListSelect.selectByVisibleText(trackName);	
		this.addTrackBtn.click();
	}
	
	public void deleteTrack() {
		this.deleteTrackIcon.click();
	}
}
