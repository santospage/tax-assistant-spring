# Tax Assistant

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN)

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
│   ├── application
│   │   ├── services
│   │   │   └── FiscalMovementsService
│   │   └── usecases
│   ├── domain
│   │   ├── entities
│   │   │   ├── FiscalMovement
│   │   │   └── Product
│   │   ├── enums
│   │   ├── exceptions
│   │   └── repositories
│   │       ├── FiscalMovementRepository
│   │       └── ProductRepository
│   ├── infrastructure
│   │   ├── database
│   │   ├── external
│   │   └── ia
│   ├── interfaces
│   │   ├── controllers
│   │   │   ├── FiscalMovementController
│   │   │   └── ProductController
│   │   └── dtos
│   │       └── FiscalMovementDTO
│   └── TaxAssistantApplication 
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

`/fiscal-movements`

- `GET /idMovement`
- `GET /tableCode`

### DataBase

This project uses SQL Server and MongoDB as database management systems.

## Roadmap

- Authentication
- Error handling
- Validations
