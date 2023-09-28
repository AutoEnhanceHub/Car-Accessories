Feature: test

  Scenario: test a phone
    Given going to a phone shop
    When i choose a new phone
    Then i must test it working


  Scenario: Breaker joins a game
    Given the Maker has started a game with the word "silky"
    When the Breaker joins the Maker's game
    Then the Breaker must guess a word with 5 characters