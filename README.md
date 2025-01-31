# ComputerShopWebApplication

## Overview
ComputerShopWebApplication is a web-based application designed for managing computer hardware components. It provides CRUD (Create, Read, Update, Delete) functionality and user authentication. The application is built using **Spring Boot, Spring MVC, JSP, Spring Security, Spring Data JPA (Hibernate ORM), MySQL, Lombok, JDBC (Spring Session JDBC), Maven, Git**.

## Features
- **Session-based authentication and authorization** with Spring Security & Spring Session JDBC  
- **CRUD operations** for managing computer components (Spring MVC + JPA)  
- **MySQL database integration** (Spring Data JPA)  
- **Email confirmation** for user registration (Spring Boot Email)  
- **Role-based access control** (Admin, User)  
- **JSP-based UI** with Spring MVC  
- **Session management stored in the database** (Spring Session JDBC)  
- **Logging and monitoring** with Spring Boot Actuator  

## Technologies Used
### Backend:
- **Java (Spring Boot, Spring Security, Spring Data JPA, Spring Session JDBC)**
- **Authentication:** Spring Security (session-based), BCrypt password hashing  
- **Session Management:** Spring Session JDBC (MySQL storage)  
- **Email Services:** Spring Boot Email  
- **Logging & Monitoring:** Spring Boot Actuator  

### Frontend:
- **JSP, JSTL, CSS**  

### Database:
- **MySQL (Spring Data JPA, Hibernate ORM)**  

### Other:
- **Lombok** (to reduce boilerplate code)  
- **Server:** Spring Boot embedded server (Tomcat)  
- **Build Tool:** Maven  
- **Version Control:** Git (GitHub)  

## Installation
### Prerequisites
- **Java 17 or later**  
- **Maven**  
- **MySQL (ensure it's running)**  
- **Apache Tomcat** *(only if deploying as a WAR instead of running Spring Boot standalone)*  

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/T1melord1/ComputerShopWebApplication.git
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
   Ensure MySQL is running before executing the command.
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



