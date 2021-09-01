package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.HomePage;
import com.qa.choonz.uat.pages.PlaylistsPage;
import com.qa.choonz.uat.pages.SignUpPage;
import com.qa.choonz.utils.ScreenshotUtility;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistCrudStepDefs {
	
	private WebDriver driver;
	private ScreenshotUtility screenshotUtils;
	private PlaylistsPage playlistPage;
	private SignUpPage signupPage;
	private HomePage homePage;
	
	public PlaylistCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.playlistPage = PageFactory.initElements(driver, PlaylistsPage.class);
		this.signupPage = PageFactory.initElements(driver, SignUpPage.class);
		this.homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Given("I have an existing user")
	public void i_have_an_existing_user() {
	    this.driver.get(signupPage.URL);
	    signupPage.clickSignUpLink();
	    String name = "Charlie";
	    String username = "charlie90";
	    String password = "password123";
	    signupPage.createUser(name, username, password);
	}

	@When("I am in the playlist page")
	public void i_am_in_the_playlist_page() {
	    homePage.clickHeaderPlaylistsBtn();
	    assertTrue(this.driver.getTitle().equals("Playlists"));
	}

	@When("I go to add a new playlist")
	public void i_go_to_add_a_new_playlist() {
	    playlistPage.clickPlusIcon();
	}

	@When("I add playlist details")
	public void i_add_playlist_details() throws InterruptedException {
		String name = "Playlist name";
		String description = "Some description";
		String artwork = "artwork image";
		String user = "1";
	    playlistPage.insertData(name, description, artwork, user);
	    Thread.sleep(2000);
	}

	@When("I press the add playlist button")
	public void i_press_the_add_playlist_button() throws InterruptedException {
		playlistPage.clickAddBtn();
	    Thread.sleep(2000);
	}

	@Then("a new playlist is added")
	public void a_new_playlist_is_added() {
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Playlist added!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
