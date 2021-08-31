package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenreCRUDPage {

	public final String URL = "http://localhost:8082/genreCrud.html";
	
	@FindBy(className = "bi-plus-circle-fill")
	private WebElement plusIcon;
	
	@FindBy(id = "new-name")
	private WebElement newNameField;
	
	@FindBy(id = "new-description")
	private WebElement newDescriptionField;
	
	@FindBy(className = "add")
	private WebElement addBtn;
	
	public void clickPlusIcon() {
		plusIcon.click();
	}
	
	public void insertDataOnAddition(String name, String description) {
		newNameField.sendKeys(name);
		newDescriptionField.sendKeys(description);
	}
	
	public void clickAddBtn() {
		addBtn.click();
	}
}
