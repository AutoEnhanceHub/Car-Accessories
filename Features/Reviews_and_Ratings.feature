Feature: Reviews and Ratings
  @rating
  Scenario: Customer leaves a review and rating for a product
    Given i am a customer(review and rate)
    Then i must choose a product "car lights" from category "Electronics"
    And i must enter the new rate 3
    And if the rating is not in the range
    Then Nothing will happen then exit the page
    And if the rating is in the possible range i must leave a review "Good Product!"
    Then the successful message will appear
    And the average rating for the product must be updated
    And the review must be added



  @Review
  Scenario: Display average ratings and reviews on product pages
    Given i am and admin (get informations)
    When i choose a product "car lights" form Category "Electronics"
    Then the average ratings for the product must be displayed and its reviews

