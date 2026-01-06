Feature: validate ecommernce application

  #Background:
  #Given Launch the flipkart application
  #When Close the popup
  #Then Navigate to home page
  @ExampleTc
  Scenario: Validate search functionality
    Given User enter the text in search box
    When User click the search button
    Then User Navigate to PLP Page

  @Smoke
  Scenario Outline: Validate the search functionality with different values
    Given User enter "<searchText>" in search field
    When User click the search button
    Then User Navigate to PLP Page
    Then Navigate to home page
    Then Click on cross icon for login popup

    Examples: 
      | searchText     |
      | shoes for mens |
      | speakers       |

  @ExcelDemo1
  Scenario Outline: search functionality with excel sheet attached
    Given User searches product using Excel row <row>
    Then Search results should be deisplayed for Excel row <row>
    Examples: 
      | row |
      |   1 |
      |   3 |
