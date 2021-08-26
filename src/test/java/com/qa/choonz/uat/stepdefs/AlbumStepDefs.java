package com.qa.choonz.uat.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.uat.hooks.SeleniumHooks;
import com.qa.choonz.uat.pages.AlbumSinglePage;
import com.qa.choonz.uat.pages.AlbumsPage;

public class AlbumStepDefs {
	
	private WebDriver driver;
	private AlbumsPage albumsPage;
	private AlbumSinglePage singlePage;
	
	public AlbumStepDefs(SeleniumHooks hooks) {
		this.driver = hooks.getDriver();
		this.albumsPage = PageFactory.initElements(driver, AlbumsPage.class);
		this.singlePage = PageFactory.initElements(driver, AlbumSinglePage.class);
	}

}
