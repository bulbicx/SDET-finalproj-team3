@delete_artist
Feature: Delete artist
  As a logged in admin, I want to delete an artist, 
  so that an artist is no more available.

  Background: Add artist
  	Given I have an artist 
  	
	  Scenario: Delete an artist
	  	When I am in the artist page
	    And I delete an artist
	    Then the artist is deleted
