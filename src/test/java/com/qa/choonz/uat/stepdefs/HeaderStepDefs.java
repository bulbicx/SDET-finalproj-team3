package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HeaderStepDefs {
	
	private WebDriver driver;
	private HomePage page;
	
	public HeaderStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.page = PageFactory.initElements(driver, HomePage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I am on the home page")
	public void iAmOnTheHomePage() {
		this.driver.get("http://127.0.0.1:5500/index.html");

	}

	@When("I click on the header home button")
	public void iClickOnTheHeaderHomeButton() {
		page.clickHeaderHomeBtn();
	}

	@Then("I am taken to the home page")
	public void iAmTakenToTheHomePage() {
		assertEquals("http://127.0.0.1:5500/index.html", this.driver.getCurrentUrl());
	}

	@When("I click on the header artist button")
	public void iClickOnTheHeaderArtistButton() {
		page.clickHeaderArtistsBtn();
	}

	@Then("I am taken to the artists page")
	public void iAmTakenToTheArtistsPage() {
		assertEquals("http://127.0.0.1:5500/artists.html", this.driver.getCurrentUrl());
	}

	@When("I click on the header album button")
	public void iClickOnTheHeaderAlbumButton() {
		page.clickHeaderAlbumsBtn();
	}

	@Then("I am taken to the albums page")
	public void iAmTakenToTheAlbumsPage() {
		assertEquals("http://127.0.0.1:5500/albums.html", this.driver.getCurrentUrl());
	}

	@When("I click on the header playlist button")
	public void iClickOnTheHeaderPlaylistButton() {
		page.clickHeaderPlaylistsBtn();
	}
	
	@Then("I am taken to the playlists page")
	public void iAmTakenToThePlaylistsPage() {
		assertEquals("http://127.0.0.1:5500/playlists.html", this.driver.getCurrentUrl());
	}


	@When("I click on the header genre button")
	public void iClickOnTheHeaderGenreButton() {
		page.clickHeaderGenresBtn();
	}

	@Then("I am taken to the genres page")
	public void iAmTakenToTheGenresPage() {
		assertEquals("http://127.0.0.1:5500/genres.html", this.driver.getCurrentUrl());
	}

}
