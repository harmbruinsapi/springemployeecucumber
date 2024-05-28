Instructions:
1. I have used gradle. so once application downloaded. 
To build the project
 ./gradlew clean build 
To run the project.
./gradlew bootRun
To test the project
./gradlew test

Swagger documentation is available in http://localhost:8080/swagger-ui/index.html.

*=====================================* 
Initial Setup 
*=====================================*
In this test case, I established a behavior-driven test framework for integration testing and a unit test framework with a mock service. The initial setup involved meeting the base requirements, such as Java 8 or 11. However, due to startup issues, I resorted to using Java 17. After some research, I discovered that the minimum requirement was a combination of Spring Boot 3 and Java 17. It was confirmed that this wasn't a strict requirement and proceeded with the development.

The first step was to tackle the most complex tasks. It had been a while since I last worked with Jenkins, so I started with the familiar Cucumber framework. Setting up Jenkins was challenging as I usually work with Azure DevOps pipelines, and I had to use a Docker container for the base setup. I discovered that Jenkins doesn't support YAML by default, which was disappointing. I decided to stick with an example due to time constraints, outlining the general scans and procedures I use.

===================================== 
Defining Requirements 
===================================== 
Upon starting, I realized that the requirements were not comprehensive. I decided to define some requirements, which I usually do with a team. I created a 'testdecisions' folder and set some rules. For instance, the salary must be higher than 0, there should be three statuses, start dates should not exceed end dates, and some process requirements. I also added some standard input validation checks.

===================================== 
Cucumber File and Rest Assured 
=====================================

Typically, the process begins with the Cucumber files, which also serve as the code's placeholder. I chose Rest Assured for integration tests, which is used to call the endpoints and can be seen as an integration test. I created two classes: the StepDefinitions for mapping the Cucumber file and the EmployeeManagementSystem class to support the StepDefinitions. I also used a data-driven model, which uses an input file to send as a body to the server. The response generates an actual response file and compares it with an expected response.

===================================== 
Unit Tests 
===================================== 
I added an MVC unit test to demonstrate my understanding of the Spring Boot model. This is located in the EmployeeControllerIntegrationTest folder. It stubs some services and creates two objects: one for the department and one for the employee. It also calls the search service. The unit test can easily be expanded, but I created it as an example. It does not cover 100% of the code.

===================================== 
Overview 
===================================== 
This project showcases a Spring Boot 3 application with an H2 database. It demonstrates the power of Rest Assured in combination with Cucumber. As a bonus, I created the layout of one unit test. The Cucumber test is a data-driven test, partially utilizing the power of Spring Boot. The framework can easily be expanded with new flows, depending on the risk, which can be calculated based on the requirements.
