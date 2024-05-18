---

Installation
------------

1) Open VScode and go to Preferences -> Settings
2) Search java.configuration.runtimes
3) Select Edit in settings.json and paste the follwing:

preffered jdk is oracle jdk: more stable

{
"java.configuration.runtimes": [
{
"name": "JavaSE-11",
"path": "C://Program Files/Java/jdk-11",
"default": true
}
]
}

4) open project
5) install gradle
6) run bootRun
7) add folder src/test/java/invenna/testcode/employee
8) add folder src/test/java/resources/features
9) add if not exists to following line to gradle: testImplementation 'org.springframework.boot:spring-boot-starter-test'
10) add testImplementation ("io.cucumber:cucumber-spring:7.17.0")
11) add testImplementation ("io.cucumber:cucumber-junit:7.17.0")
12) add testImplementation ("io.cucumber:cucumber-java:7.17.0")
13) add bellow code in the folder src/test/resources
    @RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/resources")
    public class CucumberIntegrationTest {
    }
14)
