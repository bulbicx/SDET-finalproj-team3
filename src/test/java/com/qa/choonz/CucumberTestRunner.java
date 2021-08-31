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
		tags = "@read_albums_home or @read_artists_home or @read_genres_home"
		)
public class CucumberTestRunner {


}
