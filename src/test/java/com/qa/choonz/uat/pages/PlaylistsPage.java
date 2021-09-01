package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaylistsPage {
	
	public final String url = "http://localhost:8082/playlists.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(className = "bi-pen-fill") 
	private WebElement editIcon;
	
	@FindBy(xpath="/html/body/section/main/div")
	public WebElement firstCard;
	
	@FindBy(id = "new-name")
	private WebElement newName;
	
	@FindBy(id = "new-description")
	private WebElement newDescription;
	
	@FindBy(id = "new-artwork")
	private WebElement newArtwork;
	
	@FindBy(id = "new-user")
	private WebElement newUser;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	public void clickCard() {
		firstCard.click();
	}
	
	public void clickPlusIcon() {
		this.plusIcon.click();
	}

	public void clickEditIcon() {
		this.editIcon.click();
	}
	
	public void insertData(String name, String description, String artwork, String user) {
		this.newName.sendKeys(name);
		this.newDescription.sendKeys(description);
		this.newArtwork.sendKeys(artwork);
		this.newUser.sendKeys(user);
	}
	
	public void clickAddBtn() {
		this.addBtn.click();
	}

	public void addPlaylist(String name, String description, String artwork, String user) {
		this.plusIcon.click();
		this.newName.sendKeys(name);
		this.newDescription.sendKeys(description);
		this.newArtwork.sendKeys(artwork);
		this.newUser.sendKeys(user);
		this.addBtn.click();
	}
}
