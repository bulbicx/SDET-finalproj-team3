package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.AlbumCRUDPage;
import com.qa.choonz.uat.pages.ArtistCRUDPage;
import com.qa.choonz.uat.pages.GenreCRUDPage;
import com.qa.choonz.uat.pages.TrackCRUDPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackCrudStepDefs {

	private WebDriver driver;
	private AlbumCRUDPage albumCrudPage;
	private ArtistCRUDPage artistCrudPage;
	private GenreCRUDPage genreCrudPage;
	private TrackCRUDPage trackCrudPage;
	
	public TrackCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.albumCrudPage = PageFactory.initElements(driver, AlbumCRUDPage.class);
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
		this.genreCrudPage = PageFactory.initElements(driver, GenreCRUDPage.class);
		this.trackCrudPage = PageFactory.initElements(driver, TrackCRUDPage.class);
	}
	
	@Given("I have an existing artist")
	public void i_have_an_existing_artist() {
	    this.driver.get(artistCrudPage.URL);
	    String name = "Artist";
	    String image = "C:/Users/arkan/Downloads/Choonz.png";
	    artistCrudPage.addNewArtist(name, image);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@Given("I have an existing genre")
	public void i_have_an_existing_genre() {
		artistCrudPage.clickGenrePanel();
		String name = "Genre";
		String desc = "Genre music";
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    genreCrudPage.addNewGenre(name, desc ,image);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@Given("I have an existing album")
	public void i_have_an_existing_album() {
		genreCrudPage.clickAlbumPanel();
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    albumCrudPage.addNewAlbum("Album 1", image, "Genre", "Artist");
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I am in the track page")
	public void i_am_in_the_track_page() {
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
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("New Track added!"));
	}
	
	/**
	 * Update
	 */
	@Given("I have a track")
	public void i_have_a_track() {
		albumCrudPage.clickTrackPanel();
		String name = "Track 2";
		String duration = "120";
		String album = "Album 1";
		String lyric = "po po po";
		trackCrudPage.addNewTrack(name, duration, album, lyric);
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I go to update a track")
	public void i_go_to_update_a_track() {
	    trackCrudPage.clickEditIcon();
	}

	@When("I update track details")
	public void i_update_track_details() {
		String newName = "Track 3";
		String newDuration = "210";
		String newLyric = "la po la po";
	    trackCrudPage.updateData(newName, newDuration, newLyric);
	}

	@When("I press the update track button")
	public void i_press_the_update_track_button() {
	    trackCrudPage.clickUpdateBtn();
	}

	@Then("the track is updated")
	public void the_track_is_updated() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Track updated!"));
	}
	
	/**
	 * Delete
	 */
	@When("I delete a track")
	public void i_delete_a_track() {
	    albumCrudPage.deleteTrack();
	}
	
	@Then("the track is deleted")
	public void the_track_is_deleted() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Track deleted!"));
	}
}
