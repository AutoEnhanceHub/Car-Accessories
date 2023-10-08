Feature: Analytics and Reporting
  @report
  Scenario: Generating a sales report for business insights
    Given i am an admin(report)
    Then i am asked to choose report kind "Sales"
    And The report details are printed at a file "report.txt"