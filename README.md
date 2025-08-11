# Tax Assistant

![Badge Developing](http://img.shields.io/static/v1?label=STATUS&message=DEVELOPING&color=GREEN)

## Project Summary

Spring REST project for a Tax Assistant tool designed to help the retail sector deal with the complexity of Brazilian
tax regulations. It offers simplified explanations and leverages artificial intelligence to suggest appropriate tax
profiles for customers and products, detect fiscal anomalies, identify unexpected tax differences, and flag incorrect
parameter configurations.

## Tech Stack Used

- `Java` v23.0.2
- `Spring Boot` v3.5.0,
- `spring-boot-starter-actuator`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-data-mongodb`
- `spring-boot-starter-security`
- `spring-boot-starter-oauth2-client`
- `spring-boot-starter-validation`
- `spring-boot-starter-web-services`
- `mssql-jdbc`
- `lombok`
- `SQL Server` v2019 or later

## Installation

git clone https://github.com/santospage/tax-assistant-spring.git

```
├── src
│   ├── main
│   │   └── java
│   │       └── br
│   │           └── com
│   │               └── santospage
│   │                   ├── application
│   │                   │   ├── services
│   │                   │   │   ├── CustomerService.java
│   │                   │   │   ├── FiscalMovementsService.java
│   │                   │   │   └── ProductService.java
│   │                   │   └── usecases
│   │                   ├── domain
│   │                   │   ├── entities
│   │                   │   │   ├── Customer.java
│   │                   │   │   ├── FiscalMovement.java
│   │                   │   │   └── Product.java
│   │                   │   ├── enums
│   │                   │   ├── exceptions
│   │                   │   └── repositories
│   │                   │       ├── CustomerRepository.java
│   │                   │       ├── FiscalMovementRepository.java
│   │                   │       └── ProductRepository.java
│   │                   ├── infrastructure
│   │                   │   ├── database
│   │                   │   │   ├── CustomerRepositoryImpl.java
│   │                   │   │   ├── CustomerRowMapper.java
│   │                   │   │   ├── FiscalMovementRepositoryImpl.java
│   │                   │   │   ├── FiscalMovementRowMapper.java
│   │                   │   │   ├── ProductRepositoryImpl.java
│   │                   │   │   └── ProductRowMapper.java
│   │                   │   ├── external
│   │                   │   └── ia
│   │                   ├── interfaces
│   │                   │   ├── controllers
│   │                   │   │   ├── CustomerController.java
│   │                   │   │   ├── FiscalMovementController.java
│   │                   │   │   └── ProductController.java
│   │                   │   └── dtos
│   │                   │       ├── CustomerDTO.java
│   │                   │       ├── FiscalMovementDTO.java
│   │                   │       └── ProductDTO.java
│   │                   └── TaxAssistantApplication.java
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── santospage
│                       ├── application
│                       │   ├── services
│                       │   │   ├── CustomerServiceTest.java
│                       │   │   ├── FiscalMovementsServiceTest.java
│                       │   │   └── ProductServiceTest.java
│                       │   └── usecases
│                       ├── domain
│                       │   ├── entities
│                       │   ├── enums
│                       │   ├── exceptions
│                       │   └── repositories
│                       ├── infrastructure
│                       │   ├── database
│                       │   │   ├── CustomerRepositoryImplTest.java
│                       │   │   ├── CustomerRowMapperTest.java
│                       │   │   ├── FiscalMovementRepositoryImplTest.java
│                       │   │   ├── FiscalMovementRowMapperTest.java
│                       │   │   ├── ProductRepositoryImplTest.java
│                       │   │   └── ProductRowMapperTest.java
│                       │   ├── external
│                       │   └── ia
│                       ├── interfaces
│                       │   ├── controllers
│                       │   │   ├── CustomerControllerTest.java
│                       │   │   ├── FiscalMovementControllerTest.java
│                       │   │   └── ProductControllerTest.java
│                       │   └── dtos
│                       └── TaxAssistantApplicationTest.java
├── .gitignore
├── .gitattributes
├── pom.xml
└── README.md

```

### Project Installation

Follow the steps below to set up the project on your local machine.

## How to Run the API

### Endpoints

The API exposes the following endpoints under the base URL http://localhost:8080/api:

`/customers`

- `GET /id`

`/fiscal-movements`

- `GET /idMovement`
- `GET /tableCode`

`/products`

- `GET /id`

### DataBase

This project uses SQL Server and MongoDB as database management systems.

## Roadmap

- Authentication
- Error handling
- Validations
