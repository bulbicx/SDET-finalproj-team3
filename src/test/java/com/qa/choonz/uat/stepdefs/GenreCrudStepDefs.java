package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.GenreCRUDPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenreCrudStepDefs {

	private WebDriver driver;
	ScreenshotUtility screenshotUtils;
	GenreCRUDPage genreCrudPage;
	
	public GenreCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.genreCrudPage = PageFactory.initElements(this.driver, GenreCRUDPage.class);
	}
	
	@Given("I am in the genre page")
	public void i_am_in_the_genre_page() {
	    this.driver.get(genreCrudPage.URL);
	    assertEquals("Manage Genres", this.driver.getTitle());
	}

	@When("I go to add a new genre")
	public void i_go_to_add_a_new_genre() {
	    genreCrudPage.clickPlusIcon();
	}

	@When("I add genre details")
	public void i_add_genre_details() {
	    String name = "Rock";
	    String description = "Rock music";
	    genreCrudPage.insertDataOnAddition(name, description);
	}

	@When("I press the add genre button")
	public void i_press_the_add_genre_button() {
	    genreCrudPage.clickAddBtn();
	}

	@Then("a new genre is added")
	public void a_new_genre_is_added() {
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("New Genre added!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
