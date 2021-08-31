package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.PlaylistSinglePage;
import com.qa.choonz.uat.pages.PlaylistsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistStepDefs {
	
	private WebDriver driver;
	private PlaylistsPage playlistsPage;
	private PlaylistSinglePage singlePage;
	
	public PlaylistStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.playlistsPage = PageFactory.initElements(driver, PlaylistsPage.class);
		this.singlePage = PageFactory.initElements(driver, PlaylistSinglePage.class);
	}
	
	@Given("I am on the playlists page")
	public void iAmOnThePlaylistsPage() {
		this.driver.get("http://127.0.0.1:5500/playlists.html");
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("I click on the card to the first playlist")
	public void iClickOnTheCardToTheFirstPlaylist() {
		playlistsPage.clickCard();
	}

	@Then("I am taken to the page for that playlist")
	public void iAmTakenToThePageForThatPlaylist() {
		assertEquals("http://127.0.0.1:5500/playlistsingle.html?id=1",this.driver.getCurrentUrl());
	}

	@Given("I am on the playlist page with id {int}")
	public void iAmOnThePlaylistPageWithId(Integer int1) {
		this.driver.get("http://127.0.0.1:5500/playlistsingle.html?id=1");
	}

	@When("I click on the playlist image of the first track")
	public void iClickOnThePlaylistImageOfTheFirstTrack() {
		singlePage.clickTrackCard();
	}

	@Then("I am taken to the page for that track from the playlist page")
	public void iAmTakenToThePageForThatTrackFromThePlaylistPage() {
		assertEquals("http://127.0.0.1:5500/track.html?id=1",this.driver.getCurrentUrl());
	}

	

}
