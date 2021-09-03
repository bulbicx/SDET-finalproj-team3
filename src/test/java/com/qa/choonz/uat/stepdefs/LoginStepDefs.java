package com.qa.choonz.uat.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs {
	
	private WebDriver driver;
	private LoginPage page;
	
	public LoginStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.page = PageFactory.initElements(driver, LoginPage.class);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Given("I am on the login page")
	public void iAmOnTheLoginPage() {
		this.driver.get(page.url);
	}

	@When("I enter my username")
	public void iEnterMyUsername() {
		page.enterUsername("bigred");
	}

	@When("I enter my password")
	public void iEnterMyPassword() {
		page.enterPassword("newpass");
	}

	@When("I click log in")
	public void iClickLogIn() {
		page.clickLoginBtn();
	}

	@Then("I become logged in")
	public void iBecomeLoggedIn() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://localhost:8082/index.html", this.driver.getCurrentUrl());
	}

	@When("I click the sign up link")
	public void iClickTheSignUpLink() {
		page.clickSwitchForm();
	}

	@When("I enter my new username")
	public void iEnterMyNewUsername() {
		page.enterSignupUsername("new user");
	}

	@When("I enter my real name")
	public void iEnterMyRealName() {
		page.enterSignupName("Bob Bobley");
	}

	@When("I enter my new password")
	public void iEnterMyNewPassword() {
		page.enterSignupPassword("new password");
	}

	@When("I reenter my new password")
	public void iReenterMyNewPassword() {
		page.enterSignupConfirmPassword("new password");
	}

	@When("I click sign up")
	public void iClickSignUp() {
		page.clickSignupBtn();
	}

	@Then("I become signed up")
	public void iBecomeSignedUp() throws InterruptedException {
		Thread.sleep(500);
		assertEquals("http://localhost:8082/index.html", this.driver.getCurrentUrl());
	}

	@When("I click the log in link")
	public void iClickTheLogInLink() {
		page.clickSwitchForm();
	}

	@Then("I will be on the login page")
	public void iWillBeOnTheLoginPage() {
		assertEquals(page.url, this.driver.getCurrentUrl());
	}
	
	@When("I enter the wrong password")
	public void iEnterTheWrongPassword() {
		page.passwordField.sendKeys("wrong password");
	}

	@Then("I am told I have the wrong credentials")
	public void iAmToldIHaveTheWrongCredentials() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@When("I enter the wrong username")
	public void iEnterTheWrongUsername() {
		page.usernameField.sendKeys("wrong user name");
	}

	@When("I reenter the wrong password")
	public void iReenterTheWrongPassword() {
		page.signupConfirmPasswordField.sendKeys("wrong password reentry");
	}

	@Then("I am told the passwords do not match")
	public void iAmToldThePasswordsDoNotMatch() {
		assertEquals("Passwords do not match",page.getErrorMessage());
	}

}
