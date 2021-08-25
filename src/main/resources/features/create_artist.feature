@create_artist
Feature: Create a new artist
 	As a logged in user, I want to be able to create an artist on the homepage, so that a new artist is available on the database

  Scenario: Add new artist
  	Given I am in the
    Given I am logged in
    When I create a new artist
    Then I should be able to see it on the database
