Feature:Sign up


  Scenario Outline:trying to signing up with an existing email
    Given that the user is not logged in
    When the information is exist email is "<Email>"
    Then creating an account failed

    Examples:
      | Email                         |
      | ibrahim.sadi.asad@gmail.com   |
      | ibrahimeceasad@gmail.com      |
      | i.a.s.assad33@gmail.com       |


  Scenario:trying to signing up with incorrect email format
    Given that the user is not logged in
    When the information is not formatly correct
    Then creating an account failed


  Scenario Outline:trying to signing up with new account
    Given that the user is not logged in
    When the information is not exist email is not "<Email>"
    Then creating an account successfully

    Examples:
      | Email                         |
      | ibrahim.sadi.asad@gmail.com   |
      | ibrahimeceasad@gmail.com      |
      | i.a.s.assad33@gmail.com       |





