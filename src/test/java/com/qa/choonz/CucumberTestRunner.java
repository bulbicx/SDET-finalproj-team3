package com.qa.choonz;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features"}, //feature files
		glue = {"classpath:com.qa.choonz.uat"}, //step definition file location
		snippets = SnippetType.CAMELCASE,
		plugin = {
				"summary",
				"html:target/cucumber-reports/cucumber-html-reports.html",
				"junit:target/cucumber-reports/cucumber-junit-report.xml"
		},
		monochrome = true,
		tags = "@navpages"//"@read_albums_home or @read_artists_home or @read_genres_home or @read_tracks_home or @read_playlists_home"
		)
public class CucumberTestRunner {
// many of the assert cases in the stepdefs have a sleep function to wait for the page to load. Implicit wait didn't fix it

}
