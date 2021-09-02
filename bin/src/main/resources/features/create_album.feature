@create_album
Feature: Create album

  As a logged in admin, I want to be able to create an album, 
  so that I can add artists and tracks to it.
  
  Background: Create artist and genre
		Given I have an available artist
		And I have an available genre

	  Scenario: Create a new album
	  	When I am in the album page
	    And I go to add a new album
	    And I add album details
	    And I press the add album button
	    Then a new album is added
