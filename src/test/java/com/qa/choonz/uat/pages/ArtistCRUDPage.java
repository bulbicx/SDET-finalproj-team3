package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtistCRUDPage {

	public final String URL = "http://localhost:8082/artistCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	public void clickPlusIcon() {
		plusIcon.click();
	}
	
	public void insertDataOnNameField(String name) {
		newNameField.sendKeys(name);
	}
	
	public void clickAddBtn() {
		addBtn.click();
	}
}
