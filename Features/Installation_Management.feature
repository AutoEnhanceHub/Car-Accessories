Feature: Order Management
  @Installation
  Scenario: Customer Orders products
    Given i am a Customer to get a product
    When i select the product "car lights" from the category "Electronics"
    And i must fill in the quantity 3
    Then i must see the available quantity
    And if the requested quantity if not enough the request should be cancelled
    And if enough i must fill in the car name "Kia"
    Then the customer should receive an appointment scheduling email about receiving the order
    And the product availability should be updated