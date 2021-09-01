@create_artist
Feature: Create artist

  As a logged in admin, I want to be able to create an artist, 
  so that a new artist is available.

  Scenario: Create a new artist
  	Given I am in the artist page
    When I go to add a new artist
    And I add artist details
    And I press the add artist button
    Then a new artist is added