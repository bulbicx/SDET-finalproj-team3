@navpages
Feature: Header feature
  I want to use this template for my feature file

  Scenario: Header Home Link
    Given I am on the home page
    When I click on the header home button
    Then I am taken to the home page

  Scenario: Header Artist Test
    Given I am on the home page
		When I click on the header artist button
		Then I am taken to the artists page
		
  Scenario: Header Album Test
    Given I am on the home page
		When I click on the header album button
		Then I am taken to the albums page
		
	Scenario: Header Playlist Test
    Given I am on the home page
		When I click on the header playlist button
		Then I am taken to the playlists page
		
	Scenario: Header Genre Test
    Given I am on the home page
		When I click on the header genre button
		Then I am taken to the genres page
		
#Also need test for login and profile, once those pages are implemented
