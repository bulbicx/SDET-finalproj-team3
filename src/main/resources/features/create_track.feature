@create_track
Feature: Create track
  As a logged in admin, I want to be able to create a track, 
  so that I can add it to an album.

  Background: Create artist & genre & album
  	Given I am logged in as admin
		And I have an existing artist
		And I have an existing genre
		And I have an existing album

	  Scenario: Create a new track
	  	When I am in the track page
	    And I go to add a new track
	    And I add track details
	    And I press the add track button
	    Then a new track is added

