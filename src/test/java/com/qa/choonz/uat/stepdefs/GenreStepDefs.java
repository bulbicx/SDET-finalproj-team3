package com.qa.choonz.uat.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	}
	
	@Given("I am on the genres page")
	public void i_am_on_the_genres_page() {
	    this.driver.get(genresPage.url);
	}

	@When("I click on the first genre")
	public void i_click_on_the_first_genre() {
	    genresPage.clickCard();
	}

	@Then("I am taken to the page for that genre")
	public void i_am_taken_to_the_page_for_that_genre() {
	    assertEquals("http://127.0.0.1:5500/genresingle.html?id=1", this.driver.getCurrentUrl());
	}

	@Given("I am on the genre single page")
	public void i_am_on_the_genre_single_page() {
	    this.driver.get(genreSinglePage.url);
	}

	@When("I click the name of the first album")
	public void i_click_the_name_of_the_first_album() {
	    genreSinglePage.clickAlbumName();
	}

	@Then("I am taken to the page of that album")
	public void i_am_taken_to_the_page_of_that_album() {
	    assertEquals("http://127.0.0.1:5500/albumsingle.html?id=1", this.driver.getCurrentUrl());
	}

	@When("I click on the name of the artist")
	public void i_click_on_the_name_of_the_artist() {
	    genreSinglePage.clickArtistName();
	}

	@Then("I am taken to the page of that artist")
	public void i_am_taken_to_the_page_of_that_artist() {
	    assertEquals("http://127.0.0.1:5500/artistsingle.html?id=1", this.driver.getCurrentUrl());
	}

}
