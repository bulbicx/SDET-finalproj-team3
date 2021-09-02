package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.ArtistCRUDPage;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArtistCrudStepDefs {

	private WebDriver driver;
	private ArtistCRUDPage artistCrudPage;
	
	public ArtistCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
	}
	
	@BeforeStep
	public void waits() {
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@When("I am in the artist page")
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
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    artistCrudPage.insertDataOnNameField(name, image);
	}
	
	@When("I press the add artist button")
	public void i_press_the_add_artist_button() {
		artistCrudPage.clickAddBtn();
	}
	
	@Then("a new artist is added")
	public void a_new_artist_is_added() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Artist added!"));
	}
	
	/**
	 * Update
	 */
	@Given("I have an artist")
	public void i_have_an_artist() {
	    this.driver.get(artistCrudPage.URL);
		String name = "Artist name";
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    artistCrudPage.addNewArtist(name, image);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I go to update an artist")
	public void i_go_to_update_an_artist() {
	    artistCrudPage.clickEditIcon();
	}

	@When("I update artist details")
	public void i_update_artist_details() {
		String newName = "Another";
	    artistCrudPage.updateData(newName);
	}

	@When("I press the update artist button")
	public void i_press_the_update_artist_button() {
	    artistCrudPage.clickUpdateBtn();
	}

	@Then("the artist is updated")
	public void the_artist_is_updated() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Artist updated!"));
	}
	
	/**
	 * Delete
	 */
	@When("I delete an artist")
	public void i_delete_an_artist() {
	    artistCrudPage.deleteArtist();
	}

	@Then("the artist is deleted")
	public void the_artist_is_deleted() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Artist deleted!"));
	}
}
