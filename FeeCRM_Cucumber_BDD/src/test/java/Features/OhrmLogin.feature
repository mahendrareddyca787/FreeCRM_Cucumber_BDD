@login
Feature: Test the OHRM Login page functionality

  @ohrmlogin
  Scenario: Verify Login button functionality
    Given I launch Ohrm application
    When I enter username as "Admin" and password as "admin123" for Ohrm login
    And I click on "Login" button
    Then I verify "Dashboard" page is displayed

	@addemp
  Scenario Outline: Verify Add Employee functionality
    Given I launch Ohrm application
    When I enter username as "Admin" and password as "admin123" for Ohrm login
    And I click on "Login" button
    And I click on "PIM" link
    Then I verify "PIM" page is displayed
    And I click on "Add Employee" link
    Then I verify "Add Employee" page is displayed
    And I enter FirstName as <fname> and LastName as <lname>
    And I click on "Save" button
    Then I verify "Personal Details" page is displayed

    Examples: 
      | fname      | lname      |
      | "Selenium"   | "Automation" |
      | "Manual"     | "Testing"    |
      | "Automation" | "Testing"    |
