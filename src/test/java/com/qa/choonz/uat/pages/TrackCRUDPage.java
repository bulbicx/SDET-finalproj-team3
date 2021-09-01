package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TrackCRUDPage {
	
	public final String URL = "http://localhost:8082/trackCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(className = "bi-pen-fill")
	private WebElement editIcon;
	
	@FindBy(className = "bi-trash-fill")
	private WebElement deleteIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-duration")
	private WebElement newDurationField;
	
	@FindBy(className = "album-list-add")
	private WebElement albumListAdd;
	
	private Select newAlbumDropdown;
	
	@FindBy(id = "new-lyric")
	private WebElement newLyricField;
	
	@FindBy(id = "name")
	private WebElement updateNameField;
	
	@FindBy(id = "duration")
	private WebElement updateDurationField;
	
	@FindBy(className = "album-list-update")
	private WebElement albumListUpdate;
	
	@FindBy(id = "lyric")
	private WebElement updateLyricField;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	@FindBy(className = "update")
	private WebElement updateBtn;
	
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
	
	public void clickEditIcon() {
		editIcon.click();
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
	
	public void clickUpdateBtn() {
		updateBtn.click();
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
	
	public void addNewTrack(String name, String duration, String album, String lyric) {
		this.plusIcon.click();
		this.newAlbumDropdown = new Select(albumListAdd);
		newNameField.sendKeys(name);
		newDurationField.sendKeys(duration);
		newAlbumDropdown.selectByVisibleText(album);
		newLyricField.sendKeys(lyric);
		this.addBtn.click();
	}
	
	public void updateData(String name, String duration, String lyric) {
		updateNameField.sendKeys(name);
		updateDurationField.sendKeys(duration);
		updateLyricField.sendKeys(lyric);
	}
}
