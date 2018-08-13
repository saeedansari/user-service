# User Service

This service is a sample service for making CRUD on person resources


# Tools

This service is developed using following tools and frameworks:
- Spring Boot
- Lombok
- Spock
- Apache Commons 


# Build
Run `mvn clean install`

# Run Test
To run tests run `mvn clean test` 


# Run Service locally

To run service locally you need to run a PostgreSql on your local machine.
Simple way to run postgres is to run a docker image:

`docker run -p 5433:5432  postgres:latest`

then create a database namely `user-service`

Now initiate your database with Flyway:
 
 `mvn compile flyway:migrate`

then run service like below
 `mvn spring-boot:run`
 
# URIs
- GET /user-service/people : returns all persons by pagination
- GET /user-service/people/{id} : returns person by id
- POST /user-service/people : creates person
- DELETE /user-service/people/{id} : deletes person by id






