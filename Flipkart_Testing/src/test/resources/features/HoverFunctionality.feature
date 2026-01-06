Feature: validate hover functionality 

Scenario Outline: Hovering functionality validation
Given Launch the flipkart application
When Close the popup
Then Navigate to home page
When User Hover on"<hoverText>"
