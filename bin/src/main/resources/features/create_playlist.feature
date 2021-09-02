@create_playlist
Feature: Create playlist
  As a logged in user, I can create a playlist, 
  so that I can have tracks on them

  Background: Create user 
		Given I have an existing user

	  Scenario: Create a new playlist
	  	When I am in the playlist page
	    And I go to add a new playlist
	    And I add playlist details
	    And I press the add playlist button
	    Then a new playlist is added
