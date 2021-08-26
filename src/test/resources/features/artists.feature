
Feature: Artists page
  I want to use this template for my feature file

  Scenario: Link test
    Given I am on the artists page
    When I click on the card to the first artist
    Then I am taken to the page for that artist

	Scenario: Cover Link test
		Given I am on the artist page with id 1
		When I click on the cover of the first album
		Then I am taken to the page for that album

  Scenario: Name Link test
		Given I am on the artist page with id 1
		When I click on the name of the first album
		Then I am taken to the page for that album
		
	Scenario: Genre Link test
		Given I am on the artist page with id 1
		When I click on the genre of the first album
		Then I am taken to the page for that genre
