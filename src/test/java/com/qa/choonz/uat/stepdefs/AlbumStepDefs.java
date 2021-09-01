package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.AlbumSinglePage;
import com.qa.choonz.uat.pages.AlbumsPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlbumStepDefs {
	
	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private AlbumsPage albumsPage;
	private AlbumSinglePage singlePage;
	
	public AlbumStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.albumsPage = PageFactory.initElements(driver, AlbumsPage.class);
		this.singlePage = PageFactory.initElements(driver, AlbumSinglePage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I am on the albums page")
	public void iAmOnTheAlbumsPage() {
		this.driver.get("http://127.0.0.1:5500/albums.html");

	}

	@When("I click on the card to the first album")
	public void iClickOnTheCardToTheFirstAlbum() {
	    albumsPage.clickCard();
	}

	@Then("I am taken to the page for that album")
	public void iAmTakenToThePageForThatAlbum() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://127.0.0.1:5500/albumsingle.html?id=1",this.driver.getCurrentUrl());
	}

	@Given("I am on the album page with id {int}")
	public void iAmOnTheAlbumPageWithId(Integer int1) {
		this.driver.get("http://127.0.0.1:5500/albumsingle.html?id=1");
	}

	@When("I click on the name of the first track")
	public void iClickOnTheNameOfTheFirstTrack() {
		singlePage.clickTrackName();
	}

	@Then("I am taken to the page for that track")
	public void iAmTakenToThePageForThatTrack() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://127.0.0.1:5500/track.html?id=1",this.driver.getCurrentUrl());
	}

	@When("I click on the duration of the first track")
	public void iClickOnTheDurationOfTheFirstTrack() {
		singlePage.clickTrackDuration();
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}

}
