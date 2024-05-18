/**
 * -----------------------------------------------------
 * File: ResponseREsultHandler.java
 * Created: 14-05-2024
 * Author: HB
 * Last Modified: 14-05-2024
 * -----------------------------------------------------
 *
 * This class is used to handle the response from the API calls.
 * This class is used to write the response to a file and check if the response is an error.
 */

package com.invenna.testcode.employee.cucumber;

import static org.junit.Assert.assertFalse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.invenna.testcode.employee.cucumber.models.TestContextEntity;

import io.restassured.response.Response;

public class ResponseResultsHandler {

    String successResponseWriter(TestContextEntity testContext, Response response) throws IOException {
        if (testContext == null || testContext.getTestPath() == null || testContext.getTestFolder() == null || testContext.getTestName() == null) {
            throw new IllegalArgumentException("Test context or its properties cannot be null");
        }
    
        if (response == null || response.asPrettyString().isEmpty()) {
            throw new IllegalArgumentException("Response cannot be null or empty");
        }
    
        // Beautify the JSON content
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Object json = mapper.readValue(response.asPrettyString(), Object.class);
        String prettyJson = mapper.writeValueAsString(json).trim();
    
        if (prettyJson == null || prettyJson.isEmpty()) {
            throw new IllegalArgumentException("Pretty JSON cannot be null or empty");
        }
    
        // Write the JSON content to a file
        Files.write(Paths.get(testContext.getTestPath(), testContext.getTestFolder(), testContext.getTestName() + "-actual.json"),  prettyJson.getBytes(StandardCharsets.UTF_8));
    
        return prettyJson;
    }

    String successResponseWriter(TestContextEntity testContext, String response) throws IOException {
        if (testContext == null || testContext.getTestPath() == null || testContext.getTestFolder() == null || testContext.getTestName() == null) {
            throw new IllegalArgumentException("Test context or its properties cannot be null");
        }
    
        if (response == null || response.isEmpty()) {
            throw new IllegalArgumentException("Response cannot be null or empty");
        }
    
        // Beautify the JSON content
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Object json = mapper.readValue(response, Object.class);
        String prettyJson = mapper.writeValueAsString(json).trim();
    
        if (prettyJson == null || prettyJson.isEmpty()) {
            throw new IllegalArgumentException("Pretty JSON cannot be null or empty");
        }
    
        // Write the JSON content to a file
        Files.write(Paths.get(testContext.getTestPath(), testContext.getTestFolder(), testContext.getTestName() + "-actual.json"),  prettyJson.getBytes(StandardCharsets.UTF_8));
    
        return prettyJson;
    }

    void ErrorResponseWriter(TestContextEntity testContext, Response response) throws IOException {
        // Write the JSON content to a file
        Files.write(Paths.get(testContext.getTestPath(), testContext.getTestFolder() ,testContext.getTestName() + "-error"),  response.getBody().asString().getBytes(StandardCharsets.UTF_8));
    }

    void DatabaseResponseWriter(TestContextEntity testContext, String response) throws IOException {
        // Write the JSON content to a file
        Files.write(Paths.get(testContext.getTestPath(), testContext.getTestFolder() ,testContext.getTestName() + "-actual"),  response.getBytes(StandardCharsets.UTF_8));
    }

    boolean hasError(Response response){
        // Check if the response status code is greater than or equal to 400
        if(response.getStatusCode() > 400)
        {
            return true;
        }
        else {
            return false;
        }
    }

    void handleResponse(TestContextEntity testContext, Response response) throws IOException{
        this.successResponseWriter(testContext, response);
    }

    void handleResponse(TestContextEntity testContext, String response) throws IOException{
        this.successResponseWriter(testContext, response);
    }

    void handleError(TestContextEntity testContext, Response response) throws IOException{
        this.ErrorResponseWriter(testContext, response);
        assertFalse("Invalid response: " + response.getStatusCode(), true);
    }

    void compareResponse(TestContextEntity testContext, Response response) throws IOException {
        if (testContext == null || testContext.getTestPath() == null || testContext.getTestFolder() == null || testContext.getTestName() == null) {
            throw new IllegalArgumentException("Test context or its properties cannot be null");
        }
    
        if (response == null || response.getBody().asString().isEmpty()) {
            throw new IllegalArgumentException("Response cannot be null or empty");
        }
    
        // Read the expected response from the file
        TestFileReader testFileReader = new TestFileReader();
        String expectedResponse = testFileReader.readTestFile(testContext);
    
        if (expectedResponse == null || expectedResponse.isEmpty()) {
            throw new IllegalArgumentException("Expected response cannot be null or empty");
        }
    
        // Compare the actual response with the expected response
        if(response.getBody().asString().equals(expectedResponse)){
            System.out.println("The actual response matches the expected response.");
        }
        else{
            System.out.println("The actual response does not match the expected response.");
        }
    }

    String removeDynamicValues(String response , String valueToBeRemoved) throws IOException {
        if (response == null || response.isEmpty()) {
            throw new IllegalArgumentException("Response cannot be null or empty");
        }
    
        if (valueToBeRemoved == null || valueToBeRemoved.isEmpty()) {
            throw new IllegalArgumentException("Value to be removed cannot be null or empty");
        }
    
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
    
        // Check if the jsonNode is an instance of ObjectNode
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            // Remove the value
            objectNode.remove(valueToBeRemoved);
        }
    
        // Convert the modified JSON back to a string
        String modifiedResponse = objectMapper.writeValueAsString(jsonNode);
    
        if (modifiedResponse == null || modifiedResponse.isEmpty()) {
            throw new IllegalArgumentException("Modified response cannot be null or empty");
        }
    
        return modifiedResponse;
    }

}