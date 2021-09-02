package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private ScreenshotUtility screenshotUtils;
	private GenreCRUDPage genreCrudPage;
	
	public GenreCrudStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		screenshotUtils = new ScreenshotUtility();
		this.genreCrudPage = PageFactory.initElements(this.driver, GenreCRUDPage.class);
	}
	
	@Given("I am logged in as admin")
	public void i_am_logged_in_as_admin() {
		//Create admin before running those tests
		String username = "admin";
		String password = "password123";
	    this.driver.get("http://localhost:8082/adminlogin.html");
	    WebElement usernameInput = driver.findElement(By.id("username-login"));
	    WebElement pwdInput = driver.findElement(By.id("password-login"));
	    WebElement loginBtn = driver.findElement(By.className("btn-primary"));
	    usernameInput.sendKeys(username);
	    pwdInput.sendKeys(password);
	    loginBtn.click();
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
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    genreCrudPage.insertDataOnAddition(name, description, image);
	}

	@When("I press the add genre button")
	public void i_press_the_add_genre_button() {
	    genreCrudPage.clickAddBtn();
	}

	@Then("a new genre is added")
	public void a_new_genre_is_added() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Genre added!"));
	}
	
	/**
	 * Update
	 */
	@Given("I have a genre")
	public void i_have_a_genre() {
	    this.driver.get(genreCrudPage.URL);
	    String name = "Hip";
	    String description = "Hip music";
		String image = "C:/Users/arkan/Downloads/Choonz.png";
	    genreCrudPage.addNewGenre(name, description, image);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	    this.driver.switchTo().alert().accept();
	}

	@When("I go to update a genre")
	public void i_go_to_update_a_genre() {
	    genreCrudPage.clickEditIcon();
	}

	@When("I update genre details")
	public void i_update_genre_details() {
	    String newName = "New genre";
	    String newDescription = "New description";
	    genreCrudPage.updateData(newName, newDescription);
	}

	@When("I press the update genre button")
	public void i_press_the_update_genre_button() {
	    genreCrudPage.clickUpdateBtn();
	}

	@Then("the genre is updated")
	public void the_genre_is_updated() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Genre updated!"));
	}
	
	/**
	 * Delete
	 */
	@When("I delete a genre")
	public void i_delete_a_genre() {
	    genreCrudPage.deleteGenre();
	}

	@Then("the genre is deleted")
	public void the_genre_is_deleted() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		String alertMsg = this.driver.switchTo().alert().getText();
		assertTrue(alertMsg.equals("Genre deleted!"));
	}
	
	@AfterStep
	public void takeScreenshotAfterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(screenshotUtils.takeScreenshot(driver), "image/png", scenario.getName());
		}
	}
}
