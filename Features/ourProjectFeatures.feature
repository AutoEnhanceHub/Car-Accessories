
Feature: Category Management

  @ADD0
  Scenario: Adding new Category
    Given I am an admin(adding) with email "man" and password "manypass"
    When I add a new category with the name "car_requirements"
    Then i must scan if the name "car_requirements" is exits before
    And if found i must not add the name "car_requirements"
    And if not found the category with name "car requirements" must be added
    And i must confirm the adding with email "man" and password "manypass"

  @EDIT0
  Scenario: Editing a Category
    Given I am an admin(editing) with email "man" and password "manypass"
    When I edit the category with the name "old_name" and change its name to "new_name"
    Then the category "old_name" must be edited

  @DELETE0
  Scenario: Deleting a Category
    Given I am an admin(deleting) with email "man" and password "manypass"
    When I delete the category with the name "car_requirements"
    Then the category "car_requirements" must be deleted

