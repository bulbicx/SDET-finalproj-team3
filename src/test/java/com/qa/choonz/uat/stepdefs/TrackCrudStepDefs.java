package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.AlbumCRUDPage;
import com.qa.choonz.uat.pages.ArtistCRUDPage;
import com.qa.choonz.uat.pages.GenreCRUDPage;
import com.qa.choonz.uat.pages.TrackCRUDPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackCrudStepDefs {

	private WebDriver driver;
	ScreenshotUtility screenshotUtils;
	AlbumCRUDPage albumCrudPage;
	ArtistCRUDPage artistCrudPage;
	GenreCRUDPage genreCrudPage;
	TrackCRUDPage trackCrudPage;
	
	public TrackCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.albumCrudPage = PageFactory.initElements(driver, AlbumCRUDPage.class);
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
		this.genreCrudPage = PageFactory.initElements(driver, GenreCRUDPage.class);
		this.trackCrudPage = PageFactory.initElements(driver, TrackCRUDPage.class);
	}
	
	@BeforeStep
	public void waits() {
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I have an existing artist")
	public void i_have_an_existing_artist() {
	    this.driver.get(artistCrudPage.URL);
	    artistCrudPage.addNewArtist("Artist");
	}

	@Given("I have an existing genre")
	public void i_have_an_existing_genre() {
		this.driver.switchTo().alert().accept();
		artistCrudPage.clickGenrePanel();
	    genreCrudPage.addNewGenre("Genre", "Genre music");
	}

	@Given("I have an existing album")
	public void i_have_an_existing_album() {
		this.driver.switchTo().alert().accept();
		genreCrudPage.clickAlbumPanel();
	    albumCrudPage.addNewAlbum("Album 1", "Image cover", "Genre", "Artist");
	}

	@When("I am in the track page")
	public void i_am_in_the_track_page() {
		this.driver.switchTo().alert().accept();
		albumCrudPage.clickTrackPanel();
	    assertEquals("Manage Tracks", this.driver.getTitle());
	}

	@When("I go to add a new track")
	public void i_go_to_add_a_new_track() {
	    trackCrudPage.clickPlusIcon();
	}

	@When("I add track details")
	public void i_add_track_details() {
		String name = "Track";
		String duration = "10";
		String album = "Album 1";
		String lyric = "la la la";
	    trackCrudPage.insertData(name, duration, album, lyric);
	}

	@When("I press the add track button")
	public void i_press_the_add_track_button() {
	    trackCrudPage.clickAddBtn();
	}

	@Then("a new track is added")
	public void a_new_track_is_added() {
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("New Track added!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
