package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenreCRUDPage {

	public final String URL = "http://localhost:8082/genreCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(className = "bi-pen-fill")
	private WebElement editIcon;
	
	@FindBy(className = "bi-trash-fill")
	private WebElement deleteIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-description")
	private WebElement newDescriptionField;
	
	@FindBy(id = "name")
	private WebElement updateNameField;
	
	@FindBy(id = "description")
	private WebElement updateDescriptionField;
	
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
	
	public void insertDataOnAddition(String name, String description) {
		newNameField.sendKeys(name);
		newDescriptionField.sendKeys(description);
	}
	
	public void clickAddBtn() {
		addBtn.click();
	}
	
	public void clickUpdateBtn() {
		updateBtn.click();
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

	public void updateData(String newName, String newDescription) {
		this.updateNameField.sendKeys(newName);
		this.updateDescriptionField.sendKeys(newDescription);
	}

	public void deleteGenre() {
		this.deleteIcon.click();
	}
}
