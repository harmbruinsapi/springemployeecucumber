Instructions:
1. I have used gradle. so once application downloaded. 
To build the project
 ./gradlew clean build 
To run the project.
./gradlew bootRun 

For DB, you can set the below properties in environment variable(DATASOURCE_URL, DATASOURCE_USERNAME, DATASOURCE_PASSWORD).
Also in local, if application is started, it will run with h2(in-memory databse)

I have defined 2 profiles(local, prod). local will run with h2 database, prod profile will run with whatever db we provide, it will use.

I have added swagger for documentation, so once application is started swagger documentation is available in http://localhost:8080/swagger-ui/index.html.
