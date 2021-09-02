package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.AlbumCRUDPage;
import com.qa.choonz.uat.pages.ArtistCRUDPage;
import com.qa.choonz.uat.pages.GenreCRUDPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlbumCrudStepDefs {

	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private AlbumCRUDPage albumCrudPage;
	private ArtistCRUDPage artistCrudPage;
	private GenreCRUDPage genreCrudPage;
	
	public AlbumCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.albumCrudPage = PageFactory.initElements(driver, AlbumCRUDPage.class);
		this.artistCrudPage = PageFactory.initElements(driver, ArtistCRUDPage.class);
		this.genreCrudPage = PageFactory.initElements(driver, GenreCRUDPage.class);
	}
	
	@Given("I have an available artist")
	public void i_have_an_available_artist() {
	    this.driver.get(artistCrudPage.URL);
	    String name = "Tupac";
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    artistCrudPage.addNewArtist(name, image);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@Given("I have an available genre")
	public void i_have_an_available_genre() {
		artistCrudPage.clickGenrePanel();
		String name = "Rap";
		String desc = "Rap music";
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    genreCrudPage.addNewGenre(name, desc, image);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I am in the album page")
	public void i_am_in_the_album_page() {
		genreCrudPage.clickAlbumPanel();
	    assertEquals("Manage Albums", this.driver.getTitle());
	}

	@When("I go to add a new album")
	public void i_go_to_add_a_new_album() {
	    albumCrudPage.clickPlusIcon();
	}

	@When("I add album details")
	public void i_add_album_details() {
		String name = "Album name";
		String cover = "C:/Users/arkan/Downloads/Choonz.png";
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
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Album added!"));
	    this.driver.switchTo().alert().accept();

	}
	
	@Given("I have an album")
	public void i_have_an_album() {
		genreCrudPage.clickAlbumPanel();
		String name = "Album 2";
		String cover = "C:/Users/arkan/Downloads/Choonz.png";
		String genre = "Rap";
		String artist = "Tupac";
	    albumCrudPage.addNewAlbum(name, cover, genre, artist);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I go to update an album")
	public void i_go_to_update_an_album(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("bi-pen-fill")));
	    albumCrudPage.clickEditIcon();
	}

	@When("I update album details")
	public void i_update_album_details() {
		String newName = "Updated album";
	    albumCrudPage.updateData(newName);
	}

	@When("I press the update album button")
	public void i_press_the_update_album_button() {
	    albumCrudPage.clickUpdateBtn();
	}

	@Then("the album is updated")
	public void the_album_is_updated() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Album updated!"));
	    this.driver.switchTo().alert().accept();
	}
	
	/**
	 * Delete
	 */
	@When("I delete an album")
	public void i_delete_an_album() {
	    albumCrudPage.deleteAlbum();
	}

	@Then("the album is deleted")
	public void the_album_is_deleted() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Album deleted!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
