Feature:Users Management
  Scenario: Add an exist User
    Given I am an admin
    When i choose to add new user but the user is already exist
    Then user added failed

  Scenario: Add a New User
    Given I am an admin
    When i choose to add new user with with valid formatting
    Then user successfully added

  Scenario: Edit an Existing User
    Given I am an admin
    When i choose the user and setting the new value with valid formatting
    Then user successfully updating

  Scenario: Delete a User
    Given I am an admin
    When i choose the user i want to delete
    Then user successfully deleting