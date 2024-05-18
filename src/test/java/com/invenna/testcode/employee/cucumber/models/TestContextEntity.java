/**
 * -----------------------------------------------------
 * File: TestContextEntity.java
 * Created: 13-05-2024
 * Author: HB
 * Last Modified: 13-05-2024
 * -----------------------------------------------------
 *
 * This Entity is used to store the test context for the cucumber tests.
 * This is used to store the test name and the path of the test.
 */

package com.invenna.testcode.employee.cucumber.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class TestContextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String testName;
    private String testPath;
    private String testFolder;
    private String body;

    public String getTestFolder() {
        return testFolder;
    }

    public void setTestFolder(String testFolder) {
        this.testFolder = testFolder;
    }

    public String getTestPath() {
        return testPath;
    }

    public void setTestPath(String testPath) {
        this.testPath = testPath;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String string) {
        this.setBody(string);
    }
}
