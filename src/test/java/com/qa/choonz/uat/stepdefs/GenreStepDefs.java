package com.qa.choonz.uat.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.GenreSinglePage;
import com.qa.choonz.uat.pages.GenresPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenreStepDefs {
	
	private WebDriver driver;
	private GenresPage genresPage;
	private GenreSinglePage genreSinglePage;
	
	public GenreStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.genresPage = PageFactory.initElements(driver, GenresPage.class);
		this.genreSinglePage = PageFactory.initElements(driver, GenreSinglePage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I am on the genres page")
	public void iAmOnTheGenresPage() {
	    this.driver.get(genresPage.url);
	}

	@When("I click on the first genre")
	public void iClickOnTheFirstGenre() {
	    genresPage.clickCard();
	}

	
// This function is identical to one in ArtistStepDef
//	@Then("I am taken to the page for that genre")
//	public void iAmTakenToThePageForThatGenre() {
//	    assertEquals("http://127.0.0.1:5500/genresingle.html?id=1", this.driver.getCurrentUrl());
//	}

	@Given("I am on the genre single page")
	public void iAmOnTheGenreSinglePage() {
	    this.driver.get(genreSinglePage.url);
	}

	@When("I click the name of the first album")
	public void iClickTheNameOfTheFirstAlbum() {
	    genreSinglePage.clickAlbumName();
	}

	@Then("I am taken to the page of that album")
	public void iAmTakenToThePageOfThatAlbum() throws InterruptedException {
		Thread.sleep(500);
	    assertEquals("http://127.0.0.1:5500/albumsingle.html?id=1", this.driver.getCurrentUrl());
	}

	@When("I click on the name of the artist")
	public void iClickOnTheNameOfTheArtist() {
	    genreSinglePage.clickArtistName();
	}

	@Then("I am taken to the page of that artist")
	public void iAmTakenToThePageOfThatArtist() throws InterruptedException {
		Thread.sleep(500);
	    assertEquals("http://127.0.0.1:5500/artistsingle.html?id=1", this.driver.getCurrentUrl());
	}

}
