@navpages
Feature: Login pages
  I want to use this template for my feature file

  Scenario: Login Function Test
  	Given I am on the login page
  	When I enter my username
  	And I enter my password
  	And I click log in
  	Then I become logged in
  	
  Scenario: Bad Password Login Test
  	Given I am on the login page
  	When I enter my username
  	And I enter the wrong password
  	And I click log in
  	Then I am told I have the wrong credentials
  	
  Scenario: Bad Username Login Test
  	Given I am on the login page
  	When I enter the wrong username
  	And I enter the wrong password
  	And I click log in
  	Then I am told I have the wrong credentials
  	
  Scenario: Signup Function Test
  	Given I am on the login page
  	When I click the sign up link
  	And I enter my new username
  	And I enter my real name
  	And I enter my new password
  	And I reenter my new password
  	And I click sign up
  	Then I become signed up
  	
  Scenario: Bad Password Signup Test
    Given I am on the login page
 		When I click the sign up link
 		And I enter my new username
 		And I enter my real name
 		And I enter my new password
 		And I reenter the wrong password
 		And I click sign up
 		Then I am told the passwords do not match
  	
  Scenario: Link Test
  	Given I am on the login page
  	When I click the sign up link
  	And I click the log in link
  	Then I will be on the login page
  	

