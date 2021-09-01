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
	
	public void clickCard() {
		firstCard.click();
	}
	
	public void clickPlusIcon() {
		this.plusIcon.click();
	}

	public void clickEditIcon() {
		this.editIcon.click();
	}

}
