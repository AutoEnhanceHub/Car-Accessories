

Feature: Category Management

  @ADD0
  Scenario: Adding new Category
    Given I am an admin(adding) by admin
    When I add a new category with the name "car_requirements"
    Then i must scan if the name "car_requirements" is exits before
    And if found i must not add the name "car_requirements"
    And if not found the category with name "car requirements" must be added
    And i must confirm the adding by admin

  @EDIT0
  Scenario: Editing a Category
    Given I am an admin(editing) by admin
    When I edit the category with the name "old_name"
    Then i enter a new name "new_name"
    And i must scan if the new name is for another Catogry
    And if found i will not edit it
    And if not found i must change its name
    Then the category "old_name" must be edited

  @DELETE0
  Scenario: Deleting a Category
    Given I am an admin(deleting) by admin
    When I delete the category with the name "Interior"
    Then the category "Interior" must be deleted
    
    
    
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



