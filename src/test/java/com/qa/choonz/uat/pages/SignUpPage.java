package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

	public final String URL = "http://localhost:8082/login.html";
	
	@FindBy(id = "anc-login-signup-switch")
	private WebElement signupLink;
	
	@FindBy(id = "username-signup")
	private WebElement username;
	
	@FindBy(id = "name-signup")
	private WebElement name;
	
	@FindBy(id = "password-signup")
	private WebElement password;
	
	@FindBy(id = "confirm-password-signup")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "/html/body/div[3]/div/div/div/div/form[2]/div[5]/button")
	private WebElement signupBtn;
	
	public void clickSignUpLink() {
		this.signupLink.click();
	}
	
	public void createUser(String name, String username, String password ) {
		this.name.sendKeys(name);
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.confirmPassword.sendKeys(password);
		this.signupBtn.click();
	}
}
