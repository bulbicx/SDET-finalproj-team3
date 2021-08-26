
Feature: Albums page
  I want to use this template for my feature file

  Scenario: Link test
    Given I am on the albums page
    When I click on the card to the first album
    Then I am taken to the page for that album

  Scenario: Name Link test
		Given I am on the album page with id 1
		When I click on the name of the first track
		Then I am taken to the page for that track
		
	Scenario: Genre Link test
		Given I am on the album page with id 1
		When I click on the duration of the first track
		Then I am taken to the page for that track


