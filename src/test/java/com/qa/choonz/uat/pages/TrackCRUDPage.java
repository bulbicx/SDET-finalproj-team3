package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TrackCRUDPage {
	
	public final String URL = "http://localhost:8082/trackCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-duration")
	private WebElement newDurationField;
	
	@FindBy(className = "album-list-add")
	private WebElement albumListAdd;
	
	@FindBy(id = "new-lyric")
	private WebElement newLyricField;
	
	private Select newAlbumDropdown;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	@FindBy(className = "artist-panel")
	private WebElement artistPanel;
	
	@FindBy(className = "genre-panel")
	private WebElement genrePanel;
	
	@FindBy(className = "track-panel")
	private WebElement trackPanel;
	
	@FindBy(className = "album-panel")
	private WebElement albumPanel;
	
	public void clickPlusIcon() {
		this.plusIcon.click();
	}
	
	public void insertData(String name, String duration, String album, String lyric) {
		this.newAlbumDropdown = new Select(albumListAdd);
		newNameField.sendKeys(name);
		newDurationField.sendKeys(duration);
		newAlbumDropdown.selectByVisibleText(album);
		newLyricField.sendKeys(lyric);
	}
	
	public void clickAddBtn() {
		this.addBtn.click();
	}
	
	public void clickArtistPanel() {
		this.artistPanel.click();
	}
	
	public void clickAlbumPanel() {
		this.albumPanel.click();
	}
	
	public void clickGenrePanel() {
		this.genrePanel.click();
	}
	
	public void clickTrackPanel() {
		this.trackPanel.click();
	}
}
