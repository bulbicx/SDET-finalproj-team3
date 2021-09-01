@navpages
Feature: Genres page
	As a user, I want to view a genres page, so that I can browse through all the genres
	
	Scenario:
		Given I am on the genres page
		When I click on the first genre
		Then I am taken to the page for that genre
	
	Scenario: Album link test
		Given I am on the genre single page
		When I click the name of the first album
		Then I am taken to the page of that album
		
	Scenario: Artist link test
		Given I am on the genre single page
		When I click on the name of the artist
		Then I am taken to the page of that artist