
Feature:Product Management
  @ADD1
  Scenario: Add a New Product
    Given I am an admin(adding)
    When i choose the Category "Exterior" of the new product "product_name"
    And I fill in the quantity 59
    And I fill in the price 50
    And i fill in the year 2025 of the expirationDate
    And i must scan if this product exist or not
    And if exist i must add the new quantity to the old quantity
    And if not exist i must add a new product with datails above
    And the new product must be added to the product list

  @EDIT1
  Scenario: Edit an Existing Product
    Given I am an admin(editing)
    When i choose the Category "Electronics" to edit the product "car lights"
    And I update the product name to "new_name"
    And I add new rate 2 for the the product
    And the product details must be updated

  @DELETE1
  Scenario: Delete a Product
    Given I am an admin(deleting)
    When i choose the Category "Interior" to delete the product "Vacuum Cleaner"
    And the product should be removed from the product list of the Category "Interior"