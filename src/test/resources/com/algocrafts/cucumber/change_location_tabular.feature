Feature:
  As an event goer
  I want to change the default location to my location
  So that I can easily find out the local events

  Scenario Outline: Goto home page and change the location to desired location
    Given I am on TicketFly home page using <browser>
    When I change the location to <country> then <location>
    Then the default location is <result>

    Examples:
      | browser      | country | location   | result |
      | CHROME       | CANADA  | All Canada | Canada |
      | SAFARI       | CANADA  | All Canada | Canada |
      | HEADLESS_MAC | CANADA  | All Canada | Canada |
      | FIREFOX      | CANADA  | All Canada | Canada |
