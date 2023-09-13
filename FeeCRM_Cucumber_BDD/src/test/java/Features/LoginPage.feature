@tag
Feature: Test CRM Login Page Functionality

  Scenario: CRM Loginpage functionality with valid credetials
    Given I launch CRM application and login with "Admin" user
    When I enter username and password
    And I click on login button
    
    

