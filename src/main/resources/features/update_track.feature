@update_track
Feature: Update track
  As a logged in admin, I want to be able to update a track, 
  so that its details are updated.

  Background: Add album & artist & genre & track
		Given I have an existing artist
		And I have an existing genre
		And I have an existing album
		And I have a track
  	
	  Scenario: Update a track
	  	When I am in the track page
	    And I go to update a track
	    And I update track details
	    And I press the update track button
	    Then the track is updated
