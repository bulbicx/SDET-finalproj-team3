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
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlbumCrudStepDefs {

	private WebDriver driver;
	ScreenshotUtility screenshotUtils;
	AlbumCRUDPage albumCrudPage;
	ArtistCRUDPage artistCrudPage;
	GenreCRUDPage genreCrudPage;
	
	@BeforeStep
	public void waits() {
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public AlbumCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.albumCrudPage = PageFactory.initElements(driver, AlbumCRUDPage.class);
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
		this.genreCrudPage = PageFactory.initElements(driver, GenreCRUDPage.class);
	}
	
	@Given("I have an available artist")
	public void i_have_an_available_artist() {
	    this.driver.get(artistCrudPage.URL);
	    artistCrudPage.addNewArtist("Tupac");
	}

	@Given("I have an available genre")
	public void i_have_an_available_genre() {
		this.driver.switchTo().alert().accept();
		artistCrudPage.clickGenrePanel();
//	    this.driver.get(genreCrudPage.URL);
	    genreCrudPage.addNewGenre("Rap", "Rap music");
	}

	@When("I am in the album page")
	public void i_am_in_the_album_page() {
		this.driver.switchTo().alert().accept();
		genreCrudPage.clickAlbumPanel();
//	    this.driver.get(albumCrudPage.URL);
	    assertEquals("Manage Albums", this.driver.getTitle());
	}

	@When("I go to add a new album")
	public void i_go_to_add_a_new_album() {
	    albumCrudPage.clickPlusIcon();
	}

	@When("I add album details")
	public void i_add_album_details() {
		String name = "Album name";
		String cover = "Image";
		String genre = "Rap";
		String artist = "Tupac";
	    albumCrudPage.insertData(name, cover, genre, artist);
	}

	@When("I press the add album button")
	public void i_press_the_add_album_button() {
	    this.albumCrudPage.clickAddBtn();
	}

	@Then("a new album is added")
	public void a_new_album_is_added() {
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("New Album added!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
