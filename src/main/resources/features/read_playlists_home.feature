@read_playlists_home
Feature: Read playlists from homepage
  As a user, I want to be able to read playlists on the homepage, 
  so that I can go to the playlists page

  Scenario: Read playlist
    Given I am in the homepage
    When I see the playlists section
    Then I can click onto one playlist
    And I will be redirected to the playlist detail page
