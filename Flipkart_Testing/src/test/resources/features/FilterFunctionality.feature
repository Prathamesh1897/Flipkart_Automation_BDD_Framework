Feature: validate ecommernce filter section application

  Scenario Outline: Validate filter functionality
    Given Launch the flipkart application
    When Close the popup
    Then Navigate to home page
    Then User enter the text in search box
    When User click the search button
    Then User Navigate to PLP Page
    Then User applies filter "<FilterCategory>" with option "<FilterOption>"
    Then User get the applied filters list
    #Then User applies filters

    Examples: 
      | Product | FilterCategory | FilterOption |
      | Mobiles | Brand          | vivo         |
      | Mobiles | Ram            | 6 GB         |
