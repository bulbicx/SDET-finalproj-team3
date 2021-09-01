@add_track_playlist
Feature: Add track to playlist
 	As a user, I want to add a track to a playlist,
 	so that I can personalize the playlist

  Background: Create data
		Given I have an existing user
		And I have a playlist
		And I have already artist
		And I have an genre
  	And I have an available album
  	And I have an existing track

	  Scenario: Add track to playlist
	  	When I go in the playlist page
	    And I go to a specific playlist
	    And I add a track to the playlist
	    Then the playlist should contain the track 
