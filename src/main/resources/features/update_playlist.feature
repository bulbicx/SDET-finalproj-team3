@update_playlist
Feature: Update playlist
  As a logged in user, I can update a playlist, 
  so that I can modify a change that need to be made

  Background: Create user & playlist
		Given I have an existing user
		And I have a playlist

	  Scenario: Update a playlist
	  	When I am in the playlist page
	    And I go to update a playlist
	    And I update playlist details
	    And I press the update playlist button
	    Then the playlist is updated
