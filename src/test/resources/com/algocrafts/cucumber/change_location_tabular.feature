Feature:
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Scenario Outline: scenario description
    Given I am on TicketFly home page using <browser>
    When I change the location to <country> then <location>
    Then the default location is <result>

    Examples:
      | browser      | country | location   | result |
      | CHROME       | CANADA  | All Canada | Canada |
      | SAFARI       | CANADA  | All Canada | Canada |
      | HEADLESS_MAC | CANADA  | All Canada | Canada |
      | FIREFOX      | CANADA  | All Canada | Canada |
