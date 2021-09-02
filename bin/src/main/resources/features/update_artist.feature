@update_artist
Feature: Update artist
  As a logged in admin, I want to be able to update an artist, 
  so that I can update the details

  Background: Add artist
  	Given I have an artist 
  	
	  Scenario: Update an artist
	  	When I am in the artist page
	    And I go to update an artist
	    And I update artist details
	    And I press the update artist button
	    Then the artist is updated
