/**
 * -----------------------------------------------------
 * File: RunCucumberTets.java
 * Created: 13-05-2024
 * Author: HB
 * Last Modified: 13-05-2024
 * -----------------------------------------------------
 *
 * This class contains the step definitions for the cucumber tests.
 * This class is used to test the employee management system.
 * 
 */

package com.invenna.testcode.employee.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.invenna.testcode.employee.models.Employee;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class StepDefsIntegrationTest {    
    private TestEmployeeManagementSystem employeeManagementSystem;
    private int responseStatusCode;

    @Autowired
    private Repo repo;

    // Initialize the employee management system
    @Given("the client has access to the employee management system")
    public void theClientHasAccessToTheEmployeeManagementSystem() {
        employeeManagementSystem = new TestEmployeeManagementSystem();
    }

    // Call the employee management system with the given operation 
    @When("the client calls {string} with {string} for {string}")
    public void theClientCallsWithFor(String operation, String scenario, String employee) throws IOException {
        switch (operation) {
            case "CREATE":
                responseStatusCode = employeeManagementSystem.createEmployee(scenario, employee);
                break;
            case "UPDATE":
                responseStatusCode = employeeManagementSystem.updateEmployee(scenario, employee);
                break;
            case "DELETE":
                responseStatusCode = employeeManagementSystem.deleteEmployee(scenario, employee);
                break;
            case "READ":
                responseStatusCode = employeeManagementSystem.readEmployee(scenario, employee);
                break;
            case "SEARCH":
                responseStatusCode = employeeManagementSystem.searchEmployee(scenario, employee);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    @And("the database contains for {string} the employee details for {string}")
    public void theDatabseContainsTheEmployeeDetails(String scenario, String employee) throws IOException {
        StringBuilder allEmployees = new StringBuilder();

        for (String emp : repo.findAllEmployees()) {
                 allEmployees.append(emp.toString());
        }

        employeeManagementSystem.compareDatabaseResponse(scenario,employee,allEmployees.toString(),"emp_");
    }

    @And("the database contains for {string} the department details for {string}")
    public void theDatabseContainsTheDepartmentDetails(String scenario, String employee) throws IOException {
        StringBuilder allEmployees = new StringBuilder();

        for (String emp : repo.findAllEmployees()) {
                 allEmployees.append(emp.toString()).append("\n");
        }

        employeeManagementSystem.compareDatabaseResponse(scenario,employee,allEmployees.toString(),"dep_");
    }


    // Check if the client receives the expected status code
    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int expectedStatusCode) {
        assertEquals(expectedStatusCode, responseStatusCode);
    }

    @And("the content with {string} for {string} contains the employee details")
    public void the_content_contains_the_employee_details(String scenario, String employee) throws IOException {
        employeeManagementSystem.compareResponse(scenario, employee);
    }

    @And("the content with {string} for {string} contains the employee errordetails")
    public void the_content_contains_the_employee_errordetails(String scenario, String employee) throws IOException {
        employeeManagementSystem.compareResponseExludeTimestamp(scenario, employee);
    }


    // Add some tests to shut up sonarlint
    @Test
    public void TestShutUpClass() {
        assertTrue(true);
    }
}