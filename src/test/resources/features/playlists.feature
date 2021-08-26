Feature: playlists page
  I want to use this template for my feature file

  Scenario: Link test
    Given I am on the playlists page
    When I click on the card to the first playlist
    Then I am taken to the page for that playlist

	Scenario: Image Link test
		Given I am on the playlist page with id 1
		When I click on the playlist image of the first track
		Then I am taken to the page for that track from the playlist page