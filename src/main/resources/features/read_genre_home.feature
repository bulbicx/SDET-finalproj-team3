@read_genres_home
Feature: Read genres from homepage
  As a user, I want to be able to read genres on the homepage, 
  so that I can go to an genres page

  Scenario: Read genre
    Given I am in the homepage
    When I see the genres section
    Then I can click onto one genre
    And I will be redirected to the genre detail page

