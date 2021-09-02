package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	public final String url = "http://127.0.0.1:5500/login.html";
	
	@FindBy(id="username-login")
	public WebElement usernameField;
	
	@FindBy(id="password-login")
	public WebElement passwordField;
	
	@FindBy(xpath="//*[@id=\"submitLoginForm\"]/div[3]/button")
	public WebElement loginBtn;
	
	@FindBy(id="anc-login-signup-switch")
	public WebElement switchForm;
	
	@FindBy(id="username-signup")
	public WebElement signupUsernameField;
	
	@FindBy(id="name-signup")
	public WebElement signupNameField;
	
	@FindBy(id="password-signup")
	public WebElement signupPasswordField;
	
	@FindBy(id="confirm-password-signup")
	public WebElement signupConfirmPasswordField;
	
	@FindBy(xpath="//*[@id=\"submitSignupForm\"]/div[5]/button")
	public WebElement signupBtn;
	
	@FindBy(id="error-message")
	public WebElement errorMessage;
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	
	public void enterUsername(String input) {
		usernameField.sendKeys(input);
	}
	
	public void enterPassword(String input) {
		passwordField.sendKeys(input);
	}

	public void clickSwitchForm() {
		switchForm.click();
	}
	
	public void clickSignupBtn() {
		signupBtn.click();
	}
	
	public void enterSignupUsername(String input) {
		signupUsernameField.sendKeys(input);
	}
	
	public void enterSignupName(String input) {
		signupNameField.sendKeys(input);
	}
	
	public void enterSignupPassword(String input) {
		signupPasswordField.sendKeys(input);
	}
	
	public void enterSignupConfirmPassword(String input) {
		signupConfirmPasswordField.sendKeys(input);
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
}
