# Repo Structure

A typical top-level directory layout
.

── src                     # Source and test files 
── test                    # Automated tests 
── target                   # Target files
── pom.xml                   # dependencies 

# Requirements
Java - 1.8.x

Maven - 3.x.x

Mysql - 5.x.x

# Steps to Setup
1. Clone the application

git clone https://github.com/mickmohit/Nokia-Account-Test.git

2. Create Mysql database

create database test_database

3. Change mysql username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation

4. Build and run the app using maven

mvn package
java -jar target/mohit-test-accounts-0.0.1-SNAPSHOT.jar
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at http://localhost:8080.

# Explore Rest APIs
The app defines following CRUD APIs.

GET /api/accounts

POST /api/accounts

PUT /api/accounts/{accountId}

DELETE /api/accounts/{accountId}

You can test them using postman or any other rest client.

# Test approach
Automation test cases are under */demo/automationTests/*.
For Automation ``TestNG framework``` is used.

Make sure for update/delete automation test cases, value referring to specific id's for these test cases exists in backend.

