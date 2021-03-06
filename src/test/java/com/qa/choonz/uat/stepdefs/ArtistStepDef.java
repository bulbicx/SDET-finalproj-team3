package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.ArtistsPage;
import com.qa.choonz.uat.pages.ArtistsSinglePage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArtistStepDef {
	
	
	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private ArtistsPage artistsPage;
	private ArtistsSinglePage singlePage;
	
	public ArtistStepDef(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.artistsPage = PageFactory.initElements(driver, ArtistsPage.class);
		this.singlePage = PageFactory.initElements(driver, ArtistsSinglePage.class);
	}
	
	@Given("I am on the artists page")
	public void iAmOnTheArtistsPage() {
		this.driver.get("http://localhost:8082/artists.html");
	}

	@When("I click on the card to the first artist")
	public void iClickOnTheCardToTheFirstArtist() {
		artistsPage.clickCard();
	}

	@Then("I am taken to the page for that artist")
	public void iAmTakenToThePageForThatArtist() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://localhost:8082/artistsingle.html?id=1",this.driver.getCurrentUrl());
	}

	@Given("I am on the artist page with id {int}")
	public void iAmOnTheArtistPageWithId(Integer int1) {
		this.driver.get("http://localhost:8082/artistsingle.html?id=1");
	}

	@When("I click on the name of the first album")
	public void iClickOnTheNameOfTheFirstAlbum() {
		singlePage.clickAlbumName();
	}

	@When("I click on the genre of the first album")
	public void iClickOnTheGenreOfTheFirstAlbum() {
		singlePage.clickGenreName();
		
	}
	
	@When("I click on the cover of the first album")
	public void iClickOnTheCoverOfTheFirstAlbum() {
		singlePage.clickAlbumCover();
	}

	@Then("I am taken to the page for that genre")
	public void iAmTakenToThePageForThatGenre() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://localhost:8082/genresingle.html?id=1",this.driver.getCurrentUrl());
	}

	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
