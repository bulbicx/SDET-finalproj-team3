package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.GenresPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FooterStepDefs {
	
	private WebDriver driver;
	private GenresPage page;
	
	public FooterStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.page = PageFactory.initElements(driver, GenresPage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@When("I click on the footer home button")
	public void iClickOnTheFooterHomeButton() {
		page.clickFooterHomeBtn();
	}

	@When("I click on the footer artist button")
	public void iClickOnTheFooterArtistButton() {
		page.clickFooterArtistsBtn();
	}

	@When("I click on the footer album button")
	public void iClickOnTheFooterAlbumButton() {
		page.clickFooterAlbumsBtn();
	}

	@When("I click on the footer playlist button")
	public void iClickOnTheFooterPlaylistButton() {
		page.clickFooterPlaylistsBtn();
	}

	@When("I click on the footer genre button")
	public void iClickOnTheFooterGenreButton() {
		page.clickFooterGenresBtn();
	}


	@When("I click on the footer facebook button")
	public void iClickOnTheFooterFacebookButton() {
		page.clickFacebookBtn();
	}

	@Then("I am taken to facebook")
	public void iAmTakenToFacebook() {
		assertEquals("https://en-gb.facebook.com/",this.driver.getCurrentUrl());
	}

	@When("I click on the footer twitter button")
	public void iClickOnTheFooterTwitterButton() {
		page.clickTwitterBtn();
	}

	@Then("I am taken to twitter")
	public void iAmTakenToTwitter() {
		assertEquals("https://twitter.com/?lang=en-gb",this.driver.getCurrentUrl());
	}

	@When("I click on the footer instagram button")
	public void iClickOnTheFooterInstagramButton() {
		page.clickInstagramBtn();
	}

	@Then("I am taken to instagram")
	public void iAmTakenToInstagram() {
		assertEquals("https://www.instagram.com/",this.driver.getCurrentUrl());
	}

}
