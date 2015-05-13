Feature:
As an event goer
I want to change the default location to my location
So that I can easily find out the local events

Scenario: Goto home page and change the location to desired location
Given I am on TicketFly home page using CHROME
When I change the location to CANADA then All Canada
Then the default location is Canada

Given I am on TicketFly home page using FIREFOX
When I change the location to CANADA then All Canada
Then the default location is Canada

Given I am on TicketFly home page using SAFARI
When I change the location to CANADA then All Canada
Then the default location is Canada

Given I am on TicketFly home page using HEADLESS_MAC
When I change the location to CANADA then All Canada
Then the default location is Canada