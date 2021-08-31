package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReadAlbumsHomeStepDefs {
	
	private WebDriver driver;
	private HomePage homePage;
	
	public ReadAlbumsHomeStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Given("I am in the homepage")
	public void i_am_in_the_homepage() {
	    this.driver.get(homePage.url);
	}

	@When("I see the albums section")
	public void i_see_the_albums_section() {
		String titleSection = "Albums for you";
		assertEquals(homePage.getAlbumSectionTitle(), titleSection);
	}

	@Then("I can click onto one album")
	public void i_can_click_onto_one_album() {
	    homePage.clickAlbumCard();
	}

	@Then("I will be redirected to its own page")
	public void i_will_be_redirected_to_its_own_page() {
		String titleAlbumDetailPage = "Choonz - Album details";
	    assertEquals(titleAlbumDetailPage, this.driver.getTitle());
	}
}
