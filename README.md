# Bank Management System

A Bank Management System developed using **Java, Jakarta Persistence API (JPA), Hibernate ORM, and MySQL**. The project demonstrates object-relational mapping, entity relationships, inheritance, database management, and different approaches to querying data using JPA.

## Overview

The application models the core operations and structure of a banking system. It manages banks, clients, employees, accounts, transactions, cards, and loans through a relational database.

The project was developed as an academic project to demonstrate practical knowledge of Java persistence technologies and relational database design.

## Technologies Used

- **Java 21** – Core programming language
- **Jakarta Persistence API (JPA)** – Object-relational mapping specification
- **Hibernate ORM 6.6.18** – JPA implementation
- **MySQL** – Relational database management system
- **Maven** – Dependency management and project build
- **IntelliJ IDEA** – Development environment

## Database Design

The database consists of the following main entities:

- **Bank** – Represents a banking institution.
- **Client** – Stores customer information.
- **Account** – Represents client bank accounts.
- **Transaction** – Stores account transactions.
- **Card** – Represents debit and credit cards.
- **Employee** – Base entity for bank employees.
- **Manager** – Specialized employee entity.
- **Teller** – Specialized employee entity.
- **Loan** – Stores client loan information.
- **LoanApproval** – Represents loan approval records.

### Entity Relationships

The project demonstrates several types of relationships:

- **One-to-Many (1:N)** – Bank and Clients, Bank and Employees, Client and Accounts, Account and Transactions.
- **Many-to-One (N:1)** – Accounts and Clients, Transactions and Accounts.
- **Many-to-Many (N:M)** – Managers and Loans, implemented through the `LoanApproval` associative entity.
- **Inheritance** – Employee, Manager, and Teller entities using the `JOINED` inheritance strategy.

### Inheritance Strategy

The project uses:

`@Inheritance(strategy = InheritanceType.JOINED)`

Common employee attributes are stored in the `Employee` table, while manager-specific and teller-specific attributes are stored in their respective tables.

## JPA Querying

The application demonstrates multiple ways of retrieving and filtering database data:

- **JPQL** – Retrieving entities using Java Persistence Query Language.
- **TypedQuery** – Executing type-safe queries.
- **NamedQuery** – Defining reusable named queries.
- **Multiple NamedQueries** – Organizing multiple predefined queries.
- **Criteria API** – Building dynamic queries programmatically.
- **Complex JPQL Queries** – Using JOIN, GROUP BY, and aggregate functions such as SUM.

## Project Structure

```text
bank-management-system-jpa/
├── src/
│   └── main/
│       ├── java/
│       │   └── org/example/
│       │       ├── model/
│       │       ├── enums/
│       │       ├── init/
│       │       ├── util/
│       │       └── App.java
│       └── resources/
│           └── META-INF/
│               └── persistence.xml
├── database/
│   └── bank_management_db.sql
├── docs/
│   └── ERD.png
├── pom.xml
├── .gitignore
└── README.md
```

## Getting Started

### Prerequisites

Before running the application, make sure you have installed:

- JDK 21
- MySQL Server
- Maven
- IntelliJ IDEA or another Java IDE

### Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE bank_management_db;
```

2. Open `persistence.xml` located in:

```text
src/main/resources/META-INF/persistence.xml
```

3. Configure your MySQL connection settings:

```xml
<property name="jakarta.persistence.jdbc.url"
          value="jdbc:mysql://localhost:3306/bank_management_db"/>

<property name="jakarta.persistence.jdbc.user"
          value="root"/>

<property name="jakarta.persistence.jdbc.password"
          value=""/>
```

Update the username and password according to your local MySQL configuration.

### Running the Application

1. Clone the repository:

```bash
git clone https://github.com/0bito27/bank-management-system-jpa.git
```

2. Open the project in IntelliJ IDEA.

3. Reload the Maven dependencies.

4. Make sure your MySQL server is running and the database connection is configured.

5. Run `App.java`.

Hibernate will automatically create or update the database tables based on the entity mappings.

## Learning Objectives

Through this project, I gained practical experience with:

- Object-relational mapping using JPA and Hibernate.
- Designing relational databases and entity relationships.
- Implementing inheritance in JPA.
- Managing entity persistence and relationships.
- Writing JPQL and type-safe queries.
- Using the Criteria API for dynamic queries.
- Integrating Java applications with MySQL.
- Managing project dependencies using Maven.

## Future Improvements

Potential future improvements include:

- Implementing a graphical user interface.
- Adding user authentication and authorization.
- Implementing additional banking operations.
- Improving transaction management and validation.
- Adding automated unit and integration tests.
