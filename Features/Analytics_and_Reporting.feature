Feature: Analytics and Reporting
  @report
  Scenario: Generating a sales report for business insights
    Given i am an admin(report)
    Then i am asked to choose report1 kind "Sales"
    And The report1 details are printed at a file "report1.txt"
    Then i am asked to choose report2 kind "Product rates"
    And The report2 details are printed at a file "report2.txt"
    Then i am asked to choose report3 kind "Category products"
    And The report3 details are printed at a file "report3.txt"
    Then i am asked to choose report4 kind "rates and reviews"
    And The report4 details are printed at a file "report4.txt"