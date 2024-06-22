# Developer Management REST API

This project is a RESTful API for managing developers, designed to practice writing tests, working with Data Transfer Objects (DTOs), and utilizing Testcontainers for integration testing. The application is built using Spring Boot and leverages various libraries for data persistence, testing, and containerization.

## Technologies Used

- **Spring Boot**
- **Lombok**
- **Database**:
  - `com.h2database:h2`: In-memory database for development and testing.
  - `org.postgresql:postgresql`: PostgreSQL database for production.
- **Testing**:
  - `org.springframework.boot:spring-boot-starter-test`: Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest, and Mockito.
  - `org.springframework.boot:spring-boot-testcontainers`: Integration with Testcontainers for Spring Boot.
  - `org.testcontainers:junit-jupiter`: Testcontainers support for JUnit 5.
  - `org.testcontainers:postgresql`: PostgreSQL module for Testcontainers.
  - `org.junit.platform:junit-platform-launcher`: For launching the JUnit Platform.

## Features

- **CRUD Operations**: Manage developer entities through RESTful endpoints.
- **DTOs**: Utilize Data Transfer Objects to encapsulate data sent over the network.
- **Testcontainers**: Use Docker containers for setting up and tearing down test environments, ensuring consistent and isolated testing scenarios.

## Prerequisites

- Java 17
- Maven or Gradle for building the project
- Docker for running Testcontainers
