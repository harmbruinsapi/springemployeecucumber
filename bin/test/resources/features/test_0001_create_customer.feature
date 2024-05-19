## Integration Scenarios - BDD Testing Framework

Feature: Integration Tests - Create -Read - Update - Delete operations for Employee Management System

  Scenario Outline: Employee operations CREATE, READ, UPDATE
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee details
    And the database contains for "<scenario>" the employee details for "<employee>"

    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # Happy path scenarios:
      | CREATE    | scenario_integration_happy_flow   | 1_create_employee_active_joined_in_the_past | 201         |
      | READ      | scenario_integration_happy_flow   | 1_read_employee_active_joined_in_the_past   | 200         |
      | UPDATE    | scenario_integration_happy_flow   | 1_update_employee_active_joined_in_the_past | 200         |



  Scenario Outline: Employee operations DELETE
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee details

    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # Happy path scenarios:      
      | DELETE    | scenario_integration_happy_flow   | 1_delete_employee_active_joined_in_the_past | 204         |

  Scenario Outline: Employee operations NULL
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee errordetails

    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # High risk scenarios:
      | CREATE    | scenario_integration_happy_flow   | 2_deparment_with_null_name                  | 400         |      

  Scenario Outline: Employee search operations
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee errordetails

    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # UnHappy path scenarios:
      | CREATE    | scenario_integration_happy_flow   | 2_create_employee_active_joined_in_the_past | 201         |

  Scenario Outline: Create Employee Test
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee errordetails
    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # UnHappy path scenarios:
      | CREATE    | scenario_integration_unhappy_flow   | 2_invalid_status                          | 400         |
      | CREATE    | scenario_integration_unhappy_flow   | 2_negative_salary                         | 400         |
      | CREATE    | scenario_integration_unhappy_flow   | 2_joiningdate_in_the_future               | 400         |
      | CREATE    | scenario_integration_unhappy_flow   | 2_invalid_status                          | 400         |

  Scenario Outline: Create Employee Test
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee details
    And the database contains for "<scenario>" the department details for "<employee>"

    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # Happy path scenarios:
      | CREATE    | scenario_integration_happy_flow   | 3_create_empl_for_same_department           | 201         |
      # | CREATE    | scenario_integration_happy_flow   | 4_create_empl_for_same_department           | 201         |
      # | READ      | scenario_integration_happy_flow   | 3_read_empl_for_same_department           | 200         |
      # | READ      | scenario_integration_happy_flow   | 4_read_empl_for_same_department           | 200         |

  Scenario Outline: Update Employee Test - Unhappy Path
    Given the client has access to the employee management system
    When the client calls "<operation>" with "<scenario>" for "<employee>"
    Then the client receives status code of <status_code>
    And the content with "<scenario>" for "<employee>" contains the employee errordetails
  
    Examples:
      | operation | scenario                          | employee                                    | status_code |
      # UnHappy path scenarios:
      | CREATE    | scenario_integration_unhappy_flow   | 3_salary_not_lower_then_0                 | 400         |
      | CREATE    | scenario_integration_unhappy_flow   | 4_joining_date_not_in_the_past            | 400         |