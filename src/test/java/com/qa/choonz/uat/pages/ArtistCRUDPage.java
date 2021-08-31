package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtistCRUDPage {

	public final String URL = "http://localhost:8082/artistCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(className = "bi-pen-fill")
	private WebElement editIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "name")
	private WebElement updateNameField;
	
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
		plusIcon.click();
	}
	
	public void clickEditIcon() {
		editIcon.click();
	}
	
	public void insertDataOnNameField(String name) {
		newNameField.sendKeys(name);
	}
	
	public void clickAddBtn() {
		addBtn.click();
	}
	
	public void clickUpdateBtn() {
		updateBtn.click();
	}
	
	public void addNewArtist(String name) {
		this.plusIcon.click();
		newNameField.sendKeys(name);
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

	public void updateData(String newName) {
		this.updateNameField.sendKeys(newName);
	}
}
