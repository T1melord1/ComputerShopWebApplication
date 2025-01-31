# ComputerShopWebApplication

## Overview
ComputerShopWebApplication is a web-based application designed for managing computer hardware components. The application provides CRUD (Create, Read, Update, Delete) functionality and user authentication, built using Spring Boot, Spring MVC, JSP, Spring Security, Spring Data JPA (Hibernate ORM), MySQL, Lombok, JDBC (Spring Session JDBC), Maven, Git.

## Features
- Session-based authentication and authorization (Spring Security)
- CRUD operations for managing computer components (Spring MVC + JPA)
-  MySQL database integration (Spring Data JPA)
- Email confirmation for user registration
- Role-based access control (Admin, User)
- Spring MVC with JSP-based UI
- Session management with Spring Session JDBC
- Logging and monitoring with Spring Boot Actuator

## Technologies Used
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** JSP, JSTL, CSS
- **Database:** MySQL
- **Authentication:** Spring Security, BCrypt password hashing
- **Session Management:** Spring Session JDBC
- **Email Services:** Spring Boot Mail
- **Server:** Apache Tomcat(or Spring Boot)
- **Build Tool:** Maven

## Installation
### Prerequisites
- Java 17 or later
- Maven
- MySQL
- Apache Tomcat (if deploying manually)

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repository/ComputerShopWebApplication.git
   ```
2. Navigate to the project directory:
   ```sh
   cd ComputerShopWebApplication
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/computershop
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Build the application:
   ```sh
   mvn clean install
   ```
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage
- Open a web browser and go to `http://localhost:8080/`
- Register or log in as an admin or user
- Manage computer components through the admin panel
- Browse products as a regular user

## Security
- **User Roles:** Admin, User
- **Authentication:** Form-based login
- **Password Encryption:** BCrypt
- **Session Management:** JDBC-based sessions



