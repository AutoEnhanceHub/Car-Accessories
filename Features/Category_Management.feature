

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
    When I edit the category with the name "Interior"
    Then i enter a new name "new_name"

    And i must scan if the new name is for another Catogry
    And if found i will not edit it
    And if not found i must change its name
    Then the category "car_requirements" must be edited

  @DELETE0
  Scenario: Deleting a Category
    Given I am an admin(deleting) by admin
    When I delete the category with the name "Exterior"
    Then the category "Exterior" must be deleted