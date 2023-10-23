
# Contact Management Web API

A simple Java Spring Boot application for managing contacts and companies via a RESTful API. This project serves as a contact and company management system with basic functionalities.

## Features

- Create a contact
- Update a contact
- Delete a contact
- Retrieve a list of all contacts
- Create a business (company)
- Update a company
- Search for a company using it's VAT number
- Retrieve a list of all companies
- Add a contact to a company

## Business Constraints

- A contact must have an address, a first and last name.
- A contact works in one or more companies.
- A company has an address and a VAT number.
- A contact can be employee or freelancer, with VAT number required for freelancers.

## Technical Stack

- Spring Boot
- JPA (Java Persistence API)
- In-memory H2 Database
- Synchronous JSON RESTful API
- Swagger UI for API documentation
- Maven for build management

## Getting Started

1. Clone the repository.

2. Navigate to the project directory.
```bash
cd spring-rest
```
3. Run the application.
```bash
mvn spring-boot:run
```

4. Access the Swagger UI for API documentation.
   
    http://localhost:8080/api/v1/swagger-ui.html



5. Access the H2 database console

    http://localhost:8080/h2-console


## API Endpoints
- Detailed API documentation and usage instructions are available in the Swagger UI.

## Configuration
- Configure database connection and other application properties in application.properties.

