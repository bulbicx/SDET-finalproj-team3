package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AlbumCRUDPage {

	public final String URL = "http://localhost:8082/albumCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-cover")
	private WebElement newCoverField;
	
	@FindBy(className = "genre-list-add")
	private WebElement genreListAdd;
	
	@FindBy(className = "artist-list-add")
	private WebElement artistListAdd;
	
	private Select newGenreDropdown;
	private Select newArtistDropdown;
	
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
	
	public void insertData(String name, String cover, String genre, String artist) {
		this.newGenreDropdown = new Select(genreListAdd);
		this.newArtistDropdown = new Select(artistListAdd);
		newNameField.sendKeys(name);
		newCoverField.sendKeys(cover);
		newGenreDropdown.selectByVisibleText(genre);
		newArtistDropdown.selectByVisibleText(artist);
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
