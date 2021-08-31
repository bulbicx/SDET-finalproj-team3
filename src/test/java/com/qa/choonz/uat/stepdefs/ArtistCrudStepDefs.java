package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.ArtistCRUDPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArtistCrudStepDefs {

	private WebDriver driver;
	ScreenshotUtility screenshotUtils;
	ArtistCRUDPage artistCrudPage;
	
	public ArtistCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
	}
	
	@Given("I am in the artist page")
	public void i_am_in_the_artist_page() {
	    this.driver.get(artistCrudPage.URL);
	    assertEquals("Manage Artists", this.driver.getTitle());
	}
	
	@When("I go to add a new artist")
	public void i_go_to_add_a_new_artist() {
	    artistCrudPage.clickPlusIcon();
	}
	
	@When("I add artist details")
	public void i_add_artist_details() {
		String name = "Madonna";
	    artistCrudPage.insertDataOnNameField(name);
	}
	
	@When("I press the add artist button")
	public void i_press_the_add_artist_button() {
		artistCrudPage.clickAddBtn();
	}
	
	@Then("a new artist is added")
	public void a_new_artist_is_added() {
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("New Artist added!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
