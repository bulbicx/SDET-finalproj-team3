package com.qa.choonz.uat.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.TrackPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackStepDefs {
	
	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private TrackPage trackPage;
	
	public TrackStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.trackPage = PageFactory.initElements(driver, TrackPage.class);
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
	    assertEquals("http://localhost:8082/albumsingle.html?id=1", this.driver.getCurrentUrl());
	}

	@When("I click on the cover of the artist")
	public void iClickOnTheCoverOfTheArtist() {
	    trackPage.clickArtistCover();
	}

	@Then("I go to the page of that artist")
	public void iGoToThePageOfThatArtist() throws InterruptedException {
		Thread.sleep(500);
	    assertEquals("http://localhost:8082/artistsingle.html?id=1", this.driver.getCurrentUrl());
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
