@delete_playlist
Feature: Delete playlist
  As a logged in user, I want to delete a playlist on the homepage, 
  so that a playlist is no longer available

  Background: Create user & playlist
		Given I have an existing user
		And I have a playlist

	  Scenario: Delete a playlist
	  	When I am in the playlist page
	    And I delete a playlist
	    Then the playlist is deleted
