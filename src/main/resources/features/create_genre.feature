@create_genre
Feature: Create genre
  
  As a logged in admin, I want to be able to create a genre, 
  so that a new genre is available.
	
  Scenario: Create a new genre
  	Given I am in the genre page
	  When I go to add a new genre
	  And I add genre details
	  And I press the add genre button
	  Then a new genre is added