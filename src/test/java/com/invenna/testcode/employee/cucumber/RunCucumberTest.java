/**
 * -----------------------------------------------------
 * File: RunCucumberTets.java
 * Created: 13-05-2024
 * Author: HB
 * Last Modified: 13-05-2024
 * -----------------------------------------------------
 *
 * This Entity is used to run and configure the cucumber tests.
 */

package com.invenna.testcode.employee.cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {"pretty", "html:build/reports/tests/test/cucumber-report.html"},
  features = {"src/test/java/resources"}
)
public class RunCucumberTest {

}
