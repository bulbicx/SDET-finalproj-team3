@read_artists_home
Feature: Read artists from homepage
 	As a user, I want to be able to read artists on the homepage, 
 	so that I can go to an artists page

  Scenario: Read artists
    Given I am in the homepage
    When I see the artists section
    Then I can click onto one artist
    And I will be redirected to the artist detail page
