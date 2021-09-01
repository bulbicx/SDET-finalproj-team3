@delete_genre
Feature: Remove genre
  As a logged in admin, I want to be able to remove a genre, 
  so that a genre is not available anymore
  
  Background: Add genre
  	Given I have a genre
  	
	  Scenario: Delete a genre
	  	When I am in the genre page
	    And I delete a genre
	    Then the genre is deleted
