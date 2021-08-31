@read_albums_home
Feature: Read albums from homepage
  As a user, I want to be able to read albums on the homepage, 
  so that I can go to an albums page

  Scenario: Read albums
    Given I am in the homepage
    When I see the albums section
    Then I can click onto one album
    And I will be redirected to the album detail page
