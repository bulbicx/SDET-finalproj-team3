@delete_track
Feature: Delete track
  As a logged in admin, I want to be able to delete a track, 
  so that a track is no more available.

  Background: Add album & artist & genre & track
  	Given I am logged in as admin
		And I have an existing artist
		And I have an existing genre
		And I have an existing album
		And I have a track
  	
	  Scenario: Delete a track
	  	When I am in the track page
	    And I delete a track
	    Then the track is deleted
