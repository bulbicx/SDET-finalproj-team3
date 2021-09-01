package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.HomePage;
import com.qa.choonz.uat.pages.PlaylistsPage;
import com.qa.choonz.uat.pages.SignUpPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistCrudStepDefs {
	
	private WebDriver driver;
	private PlaylistsPage playlistPage;
	private SignUpPage signupPage;
	private HomePage homePage;
	
	public PlaylistCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
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
	public void i_add_playlist_details() {
		String name = "Playlist name";
		String description = "Some description";
		String artwork = "artwork image";
		String user = "1";
	    playlistPage.insertData(name, description, artwork, user);
	}

	@When("I press the add playlist button")
	public void i_press_the_add_playlist_button() {
		playlistPage.clickAddBtn();
	}

	@Then("a new playlist is added")
	public void a_new_playlist_is_added() {
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Playlist added!"));
	}

	/**
	 * Update
	 */
	@Given("I have a playlist")
	public void i_have_a_playlist() {
	    homePage.clickHeaderPlaylistsBtn();
	    playlistPage.addPlaylist("Playlist 10", "A desc", "Image 10", "1");
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I go to update a playlist")
	public void i_go_to_update_a_playlist() {
		WebElement select = this.driver.findElement(By.className("playlist-list-update"));
		Select playlistListSelect = new Select(select);
		playlistPage.clickEditIcon();
		Awaitility.await()
		.atMost(5, TimeUnit.SECONDS)
		.until(() -> playlistListSelect.getOptions().size() > 0);
	    playlistPage.pickPlaylistToUpdate("Playlist 10");
	}

	@When("I update playlist details")
	public void i_update_playlist_details() {
		playlistPage.updateData("Update playlist", "Updated desc", "Update artwork");
	}

	@When("I press the update playlist button")
	public void i_press_the_update_playlist_button() {
	    playlistPage.clickUpdateBtn();
	}

	@Then("the playlist is updated")
	public void the_playlist_is_updated() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Playlist updated!"));
	}
	
	/**
	 * Delete
	 */
	@When("I delete a playlist")
	public void i_delete_a_playlist() {
	    playlistPage.deletePlaylist();
	}

	@Then("the playlist is deleted")
	public void the_playlist_is_deleted() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Playlist deleted!"));
	}
}
