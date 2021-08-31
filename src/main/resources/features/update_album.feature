@update_album
Feature: Update album
  As a logged in admin, I want to be able to update an album, 
  so that I can update the details.

  Background: Add album
		Given I have an available artist
		And I have an available genre
  	And I have an album
  	
	  Scenario: Update an album
	  	When I am in the album page
	    And I go to update an album
	    And I update album details
	    And I press the update album button
	    Then the album is updated
