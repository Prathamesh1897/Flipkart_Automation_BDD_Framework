Feature: Add to cart validation

  @addToCartTC001
  Scenario Outline: Validation of add to cart functionality
    Given User enter "<searchText>" in search field
    When User click the search button
    Then User Navigate to PLP Page
    Then user click on line item "<lineItem>"
    Then verify add to cart button visible

    Examples: 
      | searchText      | lineItem |
      | tshirt for mens |        1 |
