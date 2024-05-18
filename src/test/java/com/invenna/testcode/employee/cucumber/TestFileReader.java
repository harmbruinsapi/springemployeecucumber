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

 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.fasterxml.jackson.databind.JsonNode;
 import com.invenna.testcode.employee.cucumber.models.TestContextEntity;
 
 public class TestFileReader {
 
     public String readTestFile(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + ".json");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFile(TestContextEntity testcontext, String fileType) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-" + fileType + ".json");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFileActualResponse(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-actual.json");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFileExpecteResponse(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-expected.json");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFileActualResponseDB(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-actual");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFileExpecteResponseDB(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-expected");
         String content = Files.readString(path);
     
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
     
         return content;
     }
 
     public String readTestFileExpectedResponseAndGetId(TestContextEntity testcontext) throws IOException {
         if (testcontext == null || testcontext.getTestPath() == null || testcontext.getTestFolder() == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
 
         Path path = Paths.get(testcontext.getTestPath() + "/" + testcontext.getTestFolder() + "/" + testcontext.getTestName() + "-expected.json");
         String content = Files.readString(path);
 
         if (content == null || content.isEmpty()) {
             throw new IllegalArgumentException("File content cannot be null or empty");
         }
 
         ObjectMapper objectMapper = new ObjectMapper();
         JsonNode jsonNode = objectMapper.readTree(path.toFile());
         return jsonNode.get("id").asText();
     }
     
 
     public String getIdFromTestFileName(TestContextEntity testcontext) {
         if (testcontext == null || testcontext.getTestName() == null) {
             throw new IllegalArgumentException("Test context or its properties cannot be null");
         }
     
         String testName = testcontext.getTestName();
     
         if (testName == null || !testName.contains("_")) {
            throw new IllegalArgumentException("File name must contain an underscore");
        }
    
        String identifier = testName.substring(0, testName.indexOf('_'));
    
        return identifier;
     }
 }