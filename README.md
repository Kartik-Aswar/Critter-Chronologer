# Critter Chronologer

A **Spring Boot REST API** for managing pet-care appointments, customers, pets, employees, and schedules.

## 📌 Overview

Critter Chronologer is a backend application built with Java and Spring Boot. It provides REST endpoints to manage pet-care information and coordinate appointments between customers, their pets, and employees.

The project helped me practise REST API development, layered application architecture, database persistence, and automated testing in Java.

## ✨ Features

- **Customer Management** — Create and retrieve customer information.
- **Pet Management** — Register pets and associate them with customers.
- **Employee Management** — Manage employee information and skills.
- **Scheduling** — Create and retrieve pet-care appointments.
- **Database Persistence** — Use Spring Data JPA to interact with a relational database.
- **API Testing** — Includes a Postman collection and functional tests.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot 3.3.5 | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database persistence |
| MySQL | Development database |
| H2 Database | Test database |
| Maven | Build and dependency management |
| Postman | API testing |

## 📂 Project Structure

```text
critter/
├── src/
│   ├── main/
│   │   ├── java/com/udacity/jdnd/course3/critter/
│   │   │   ├── pet/
│   │   │   ├── repository/
│   │   │   ├── schedule/
│   │   │   ├── service/
│   │   │   └── user/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── Udacity.postman_collection.json
│   └── test/
│       ├── java/
│       └── resources/
├── pom.xml
└── .gitignore
```

## ⚙️ Getting Started

### Prerequisites

- JDK 21
- Maven (or use the Maven Wrapper if available)
- MySQL Server

### 1. Clone the repository

```bash
git clone https://github.com/Kartik-Aswar/Critter-Chronologer.git
cd Critter-Chronologer
```

### 2. Configure the database

Create a MySQL database for the application, then configure the following environment variables to match your local setup:

| Variable | Description |
|---|---|
| `DB_URL` | JDBC URL of your MySQL database |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |

Set these variables in your operating system or IDE run configuration. **Do not commit database credentials or secrets to GitHub.**

Ensure that the variable names match those referenced in `src/main/resources/application.properties`.

### 3. Run the application

From the directory containing `pom.xml`, execute:

```bash
mvn spring-boot:run
```

The application is configured to run on port `8082`, unless overridden by configuration.

### 4. Run the tests

```bash
mvn test
```

## 🧪 API Testing

Import the included Postman collection into Postman to explore and test the API endpoints.

**Collection:** `src/main/resources/Udacity.postman_collection.json`

## 📚 What I Learned

- Building RESTful APIs using Spring Boot
- Organizing backend code into controllers, services, repositories, and entities
- Working with relational databases and Spring Data JPA
- Using DTOs to transfer data between API layers
- Configuring application environments and test databases
- Testing application functionality with Maven and JUnit

## 🙌 Acknowledgment

This project is based on the **Udacity Java Web Developer Nanodegree** Critter Chronologer starter project and was used as a hands-on learning exercise.

## 👨‍💻 Author

**Kartik Aswar**

[GitHub Profile](https://github.com/Kartik-Aswar)
