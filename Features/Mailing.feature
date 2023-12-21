Feature: Mailing Feature

  Scenario: Send verification code successfully
    Given the mailing system is set up
    When the user sends a verification code
    Then the verification code should be sent successfully

  Scenario: Send verification code with invalid email
    Given the mailing system is set up with an invalid email
    When the user sends a verification code
    Then an error should occur

  Scenario: Send verification code with email sending failure
    Given the mailing system is set up to simulate email sending failure
    When the user sends a verification code
    Then an error should occur during the email sending process

  Scenario: Create a new mailing with valid email
    Given a valid email address is provided
    When a new mailing instance is created
    Then the 'from' field should be set to the default email address
    And the 'to' field should be set to the provided email address

  Scenario: Send email successfully
    Given the mailing system is set up
    When the user sends an email with a subject and text
    Then the email should be sent successfully

  Scenario: Send email with invalid email
    Given the mailing system is set up with an invalid email
    When the user sends an email with a subject and text
    Then an error should occur

  Scenario: Send email with email sending failure
    Given the mailing system is set up to simulate email sending failure
    When the user sends an email with a subject and text
    Then an error should occur during the email sending process
