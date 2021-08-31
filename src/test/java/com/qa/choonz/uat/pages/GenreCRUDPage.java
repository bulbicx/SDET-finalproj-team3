package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenreCRUDPage {

	public final String URL = "http://localhost:8082/genreCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-description")
	private WebElement newDescriptionField;
	
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
		plusIcon.click();
	}
	
	public void insertDataOnAddition(String name, String description) {
		newNameField.sendKeys(name);
		newDescriptionField.sendKeys(description);
	}
	
	public void clickAddBtn() {
		addBtn.click();
	}
	
	public void addNewGenre(String name, String description) {
		plusIcon.click();
		newNameField.sendKeys(name);
		newDescriptionField.sendKeys(description);
		addBtn.click();
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
