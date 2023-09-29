Feature:Authentication and User Authorization

  Scenario:valid information
    Given that the user is not logged in
    When the information is valid email is "Admin@mail.com" and password is "admin123"
    Then user successfully log in

  Scenario:invalid email
    Given that the user is not logged in
    When the email is invalid email is "gg@mail.com" and password is "admin123"
    Then user failed in log in

  Scenario:invalid password
    Given that the user is not logged in
    When the password is invalid email is "Admin@mail.com" and password is "00"
    Then user failed in log in


  Scenario:invalid information
    Given that the user is not logged in
    When the information are invalid email is "gg@mail.com" and password is "00"
    Then user failed in log in