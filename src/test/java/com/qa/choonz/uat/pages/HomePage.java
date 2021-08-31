package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	public final String url ="http://127.0.0.1:5500/index.html";
	
	@FindBy(xpath="//*[@id=\"collapsingNavbar\"]/ul/li[1]/a")
	public WebElement headerHomeBtn;
	
	@FindBy(xpath="//*[@id=\"collapsingNavbar\"]/ul/li[2]/a")
	public WebElement headerArtistsBtn;
	
	@FindBy(xpath="//*[@id=\"collapsingNavbar\"]/ul/li[3]/a")
	public WebElement headerAlbumsBtn;
	
	@FindBy(xpath="//*[@id=\"collapsingNavbar\"]/ul/li[4]/a")
	public WebElement headerPlaylistsBtn;
	
	@FindBy(xpath="//*[@id=\"collapsingNavbar\"]/ul/li[5]/a")
	public WebElement headerGenresBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[2]/a[1]")
	public WebElement footerHomeBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[2]/a[2]")
	public WebElement footerArtistsBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[2]/a[3]")
	public WebElement footerAlbumsBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[2]/a[4]")
	public WebElement footerPlaylistsBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[2]/a[5]")
	public WebElement footerGenresBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[4]/ul/li[1]/a")
	public WebElement facebookBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[4]/ul/li[2]/a")
	public WebElement instagramBtn;
	
	@FindBy(xpath="/html/body/div[7]/footer/div[4]/ul/li[3]/a")
	public WebElement twitterBtn;
	
	public void clickHeaderHomeBtn() {
		headerHomeBtn.click();
	}
	
	public void clickHeaderArtistsBtn() {
		headerArtistsBtn.click();
	}
	
	public void clickHeaderAlbumsBtn() {
		headerAlbumsBtn.click();
	}
	
	public void clickHeaderPlaylistsBtn() {
		headerPlaylistsBtn.click();
	}
	
	public void clickHeaderGenresBtn() {
		headerGenresBtn.click();
	}
	
	public void clickFooterHomeBtn() {
		footerHomeBtn.click();
	}
	
	public void clickFooterArtistsBtn() {
		footerArtistsBtn.click();
	}
	
	public void clickFooterAlbumsBtn() {
		footerAlbumsBtn.click();
	}
	
	public void clickFooterPlaylistsBtn() {
		footerPlaylistsBtn.click();
	}
	
	public void clickFooterGenresBtn() {
		footerGenresBtn.click();
	}
	
	public void clickFacebookBtn() {
		facebookBtn.click();
	}
	
	public void clickInstagramBtn() {
		instagramBtn.click();
	}
	
	public void clickTwitterBtn() {
		twitterBtn.click();
	}
	
	

}
