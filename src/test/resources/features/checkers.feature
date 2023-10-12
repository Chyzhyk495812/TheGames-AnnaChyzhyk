Feature: As a user i want to be able to play checkers game

  Scenario: Make five legal moves as orange player
    Given I am on the Checkers game website
    When I make five legal moves as orange and take a blue piece
    And I restart the game
    Then I confirm that the game has been successfully restarted