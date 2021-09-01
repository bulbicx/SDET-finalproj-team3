package com.qa.choonz.uat.stepdefs;

import org.openqa.selenium.WebDriver;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistCrudStepDefs {
	
	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private PlaylistPage playlistPage;
	
	public PlaylistCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
	}

	@Given("I have an existing user")
	public void i_have_an_existing_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I am in the playlist page")
	public void i_am_in_the_playlist_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I go to add a new playlist")
	public void i_go_to_add_a_new_playlist() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I add playlist details")
	public void i_add_playlist_details() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I press the add playlist button")
	public void i_press_the_add_playlist_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("a new playlist is added")
	public void a_new_playlist_is_added() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
