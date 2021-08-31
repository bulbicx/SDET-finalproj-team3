package com.qa.choonz.uat.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.TrackPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackStepDefs {
	
	private WebDriver driver;
	private TrackPage trackPage;
	
	public TrackStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.trackPage = PageFactory.initElements(driver, TrackPage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I am on the first track page")
	public void iAmOnTheFirstTrackPage() {
	    this.driver.get(trackPage.url);
	}
	
	@When("I click on the cover of the album")
	public void iClickOnTheCoverOfTheAlbum() {
	    trackPage.clickAlbumCover();
	}

	@Then("I go to the page of that album")
	public void iGoToThePageOfThatAlbum() throws InterruptedException {
		Thread.sleep(500);
	    assertEquals("http://127.0.0.1:5500/albumsingle.html?id=1", this.driver.getCurrentUrl());
	}

	@When("I click on the cover of the artist")
	public void iClickOnTheCoverOfTheArtist() {
	    trackPage.clickArtistCover();
	}

	@Then("I go to the page of that artist")
	public void iGoToThePageOfThatArtist() throws InterruptedException {
		Thread.sleep(500);
	    assertEquals("http://127.0.0.1:5500/artistsingle.html?id=1", this.driver.getCurrentUrl());
	}
}
