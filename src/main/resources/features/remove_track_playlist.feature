@remove_track_playlist
Feature: Remove track from playlist
 	As a user, I want to remove a track from a playlist,
 	so that a track is not the playlist anymore

  Background: Create data
		Given I have an existing user
		And I have a playlist
		And I have already artist
		And I have an genre
  	And I have an available album
  	And I have an existing track
  	And I have a track into the playlist

	  Scenario: Remove track from playlist
	  	When I go in the playlist page
	    And I go to a specific playlist
	    And I remove a track from the playlist
	    Then the track should be removed from the playlist
