
Feature: Track page
	As a user, I want to be able to click on different links on a track page, so that I can navigate to other pages. 
	
	Background:
		Given I am on the first track page
	
	Scenario: Artist Link Test
		When I click on the name of the artist
		Then I go to the page of that artist
		
	Scenario: Album Link Test
		When I click on the name of the album
		Then I go to the page of that album

