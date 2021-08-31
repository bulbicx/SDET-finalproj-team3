package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	public final String url = "http://localhost:8082/index.html";
	
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
	
	@FindBy(xpath="/html/body/div[4]/footer/div[2]/a[1]")
	public WebElement footerHomeBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[2]/a[2]")
	public WebElement footerArtistsBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[2]/a[3]")
	public WebElement footerAlbumsBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[2]/a[4]")
	public WebElement footerPlaylistsBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[2]/a[5]")
	public WebElement footerGenresBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[4]/ul/li[1]/a")
	public WebElement facebookBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[4]/ul/li[2]/a")
	public WebElement instagramBtn;
	
	@FindBy(xpath="/html/body/div[4]/footer/div[4]/ul/li[3]/a")
	public WebElement twitterBtn;
	
	@FindBy(className = "albums")
	private WebElement albumTitle;
	
	@FindBy(className = "card-albums-1")
	private WebElement firstAlbumCard;
	
	@FindBy(className = "artists")
	private WebElement artistsTitle;
	
	@FindBy(className = "card-artists-1")
	private WebElement firstArtistCard;
	
	@FindBy(className = "genres")
	private WebElement genresTitle;
	
	@FindBy(className = "card-genres-1")
	private WebElement firstGenreCard;
	
	@FindBy(className = "tracks")
	private WebElement tracksTitle;
	
	@FindBy(className = "card-tracks-1")
	private WebElement firstTrackCard;
	
	@FindBy(className = "playlist")
	private WebElement playlistsTitle;
	
	@FindBy(className = "card-playlist-1")
	private WebElement firstPlaylistCard;

	
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
	
	public String getAlbumSectionTitle() {
		return this.albumTitle.getText();
	}
	
	public void clickAlbumCard() {
		this.firstAlbumCard.click();
	}
	
	public String getArtistSectionTitle() {
		return this.artistsTitle.getText();
	}
	
	public WebElement getArtistCard() {
		return firstArtistCard;
	}
	
	public String getGenreSectionTitle() {
		return this.genresTitle.getText();
	}
	
	public WebElement getGenreCard() {
		return firstGenreCard;
	}
	
	public String getTrackSectionTitle() {
		return this.tracksTitle.getText();
	}
	
	public WebElement getTrackCard() {
		return firstTrackCard;
	}
	
	public String getPlaylistSectionTitle() {
		return this.playlistsTitle.getText();
	}
	
	public WebElement getPlaylistCard() {
		return firstPlaylistCard;
	}

}
