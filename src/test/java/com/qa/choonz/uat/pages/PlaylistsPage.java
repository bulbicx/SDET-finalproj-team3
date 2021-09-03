package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PlaylistsPage {
	
	public final String url = "http://localhost:8082/playlists.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(className = "bi-pen-fill") 
	private WebElement editIcon;
	
	@FindBy(className = "bi-trash-fill")
	private WebElement deleteIcon;
	
	@FindBy(xpath="/html/body/section/main/div")
	public WebElement firstCard;
	
	@FindBy(id = "new-name")
	private WebElement newName;
	
	@FindBy(id = "new-description")
	private WebElement newDescription;
	
	@FindBy(id = "new-image")
	private WebElement newArtwork;
	
	@FindBy(id = "new-user")
	private WebElement newUser;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	@FindBy(className = "update")
	private WebElement updateBtn;
	
	@FindBy(className = "delete")
	private WebElement deleteBtn;
	
	@FindBy(className = "playlist-list-update")
	private WebElement playlistList;
	
	private Select playlistListSelect;
	
	@FindBy(className = "playlist-list-delete")
	private WebElement playlistListDelete;
	
	private Select playlistListDeleteSelect;
	
	@FindBy(id = "name")
	private WebElement updateName;
	
	@FindBy(id = "description")
	private WebElement updateDescription;
	
	@FindBy(id = "artwork")
	private WebElement updateArtwork;
	
	@FindBy(xpath = "/html/body/section/main/div/span")
	private WebElement firstPlaylistCard;
	
	public void clickCard() {
		firstCard.click();
	}
	
	public void clickPlusIcon() {
		this.plusIcon.click();
	}

	public void clickDeleteIcon() {
		this.deleteIcon.click();
	}

	public void clickEditIcon() {
		this.editIcon.click();
	}
	
	public void insertData(String name, String description, String artwork) {
		this.newName.sendKeys(name);
		this.newDescription.sendKeys(description);
		this.newArtwork.sendKeys(artwork);
	}
	
	public void clickAddBtn() {
		this.addBtn.click();
	}
	
	public void clickUpdateBtn() {
		this.updateBtn.click();
	}
	
	public void clickDeleteBtn() {
		this.deleteBtn.click();
	}

	public void addPlaylist(String name, String description, String artwork) {
		this.plusIcon.click();
		this.newName.sendKeys(name);
		this.newDescription.sendKeys(description);
		this.newArtwork.sendKeys(artwork);
		this.addBtn.click();
	}
	
	public void pickPlaylistToUpdate(String playlist) {
		this.playlistListSelect = new Select(playlistList);
		this.playlistListSelect.selectByVisibleText(playlist);		
	}
	
	public void pickPlaylistToDelete(String playlist) {
		this.playlistListDeleteSelect = new Select(playlistListDelete);
		this.playlistListDeleteSelect.selectByVisibleText(playlist);		
	}

	public void updateData(String name, String desc, String artwork) {
		this.updateName.sendKeys(name);
		this.updateDescription.sendKeys(desc);
		this.updateArtwork.sendKeys(artwork);
	}
	
	public WebElement getSelectPlaylistElement() {
		return this.playlistList;
	}
	
	public void clickFirstCard() {
		this.firstPlaylistCard.click();
	}
}
