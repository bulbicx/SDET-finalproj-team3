package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.HomePage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReadHomeSectionsStepDefs {
	
	private WebDriver driver;
	ScreenshotUtility screenshotUtils;
	private HomePage homePage;
	
	public ReadHomeSectionsStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
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
	
	@Then("I will be redirected to the album detail page")
	public void i_will_be_redirected_to_the_album_detail_page() {
		String titleAlbumDetailPage = "Choonz - Album details";
	    assertEquals(titleAlbumDetailPage, this.driver.getTitle());
	}
	
	/**
	 * Artist section 
	 * from fileName(read_artists_home.feature)
	 */
	@When("I see the artists section")
	public void i_see_the_artists_section() {
	    String titleSection = "Artists for you";
		assertEquals(titleSection, homePage.getArtistSectionTitle());
	}

	@Then("I can click onto one artist")
	public void i_can_click_onto_one_artist() {
		WebElement element = homePage.getArtistCard();
		WebDriverWait wait = new WebDriverWait(driver, 5); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
	
	@Then("I will be redirected to the artist detail page")
	public void i_will_be_redirected_to_the_artist_detail_page() {
	    String titleArtistDetailPage = "Choonz - Artist details";
	    assertEquals(titleArtistDetailPage, this.driver.getTitle());
	}
	
	/**
	 * Genre section
	 * From file(read_genre_home.feature)
	 */
	@When("I see the genres section")
	public void i_see_the_genres_section() {
		WebElement element = homePage.getGenreCard();
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	    String titleSection = "Genres for you";
		assertEquals(titleSection, homePage.getGenreSectionTitle());
	}

	@Then("I can click onto one genre")
	public void i_can_click_onto_one_genre() {
		//move to footer so element becomes visible by driver
		WebElement footer = driver.findElement(By.id("sub-catch-phrase"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", footer);
		WebElement element = homePage.getGenreCard();
		element.click();
	}

	@Then("I will be redirected to the genre detail page")
	public void i_will_be_redirected_to_the_genre_detail_page() {
		String title = "Choonz - Genre details";
		WebDriverWait wait = new WebDriverWait(driver, 3); 
		wait.until(ExpectedConditions.titleContains(title));

	    assertEquals(title, this.driver.getTitle());
	}
	
	/**
	 * Track section
	 * From file(read_tracks_home.feature)
	 */
	@When("I see the tracks section")
	public void i_see_the_tracks_section() {
		WebElement element = homePage.getTrackCard();
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	    String titleSection = "Tracks for you";
		assertEquals(titleSection, homePage.getTrackSectionTitle());
	}

	@Then("I can click onto one track")
	public void i_can_click_onto_one_track() {
		//move to footer so element becomes visible by driver
		WebElement footer = driver.findElement(By.id("sub-catch-phrase"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", footer);
		WebElement element = homePage.getTrackCard();
		element.click();
	}

	@Then("I will be redirected to the track detail page")
	public void i_will_be_redirected_to_the_track_detail_page() {
		String title = "Choonz - Track";
		WebDriverWait wait = new WebDriverWait(driver, 3); 
		wait.until(ExpectedConditions.titleContains(title));

	    assertEquals(title, this.driver.getTitle());
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if(scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());				
		}
	}
}
