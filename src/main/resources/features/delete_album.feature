@delete_album
Feature: Delete album

  As a logged in admin, I want to be able to delete an album, 
  so that an album is not available anymore.
	
  Background: Add genre & artist & album
  	Given I am logged in as admin
		Given I have an available artist
		And I have an available genre
  	And I have an album
  	
	  Scenario: Delete an album
	  	When I am in the album page
	    And I delete an album
	    Then the album is deleted
