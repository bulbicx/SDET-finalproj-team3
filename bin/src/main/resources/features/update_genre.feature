@update_genre
Feature: Update genre
  As a logged in admin, I want to be able to update a genre, 
  so that I a new change is applied.

  Background: Add genre
  	Given I have a genre
  	
	  Scenario: Update a genre
	  	When I am in the genre page
	    And I go to update a genre
	    And I update genre details
	    And I press the update genre button
	    Then the genre is updated
