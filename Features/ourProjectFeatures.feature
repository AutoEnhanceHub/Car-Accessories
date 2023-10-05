Feature: Authentication and User Authorization


  Scenario Outline: Valid information
    Given that the user is not logged in
    When the information is valid email is "<Email>" and password is "<Password>"
    And verification code is "123"
    Then user successfully log in

    Examples:
      | Email                         | Password |
      | ibrahim.sadi.asad@gmail.com   | 123456   |
      | ibrahimeceasad@gmail.com      | 654321   |
      | i.a.s.assad33@gmail.com       | 987654   |

  Scenario Outline: Invalid email
    Given that the user is not logged in
    When the email is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                         | Password |
      | ibrahim.sadi.asad@gmail.com   | 123456   |
      | ibrahimeceasad@gmail.com      | 654321   |
      | i.a.s.assad33@gmail.com       | 987654   |

  Scenario Outline: Invalid password
    Given that the user is not logged in
    When the password is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                         | Password |
      | ibrahim.sadi.asad@gmail.com   | 123456   |
      | ibrahimeceasad@gmail.com      | 654321   |
      | i.a.s.assad33@gmail.com       | 987654   |


  Scenario Outline: Invalid information
    Given that the user is not logged in
    When the information are invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in
    Examples:
      | Email                         | Password |
      | ibrahim.sadi.asad@gmail.com   | 123456   |
      | ibrahimeceasad@gmail.com      | 654321   |
      | i.a.s.assad33@gmail.com       | 987654   |


