
# SpringBootREST

A Spring Boot REST API project built to practice backend development using Spring Boot, Spring Data JPA, Spring Security, and Spring Data REST.

## 🚀 Features

- RESTful API structure
- CRUD operations with Spring Data JPA
- Auto-generated REST endpoints with Spring Data REST
- Basic authentication with Spring Security
- Exception handling and validation
- JSON request/response handling


## Installation

### Installation

1. Clone the repository:
```bash
git clone https://github.com/YznAlqadery/SpringBootREST.git
cd SpringBootCommand
mvn clean install
```
2️⃣ Configure MySQL:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```
3️⃣ Run the App:
```bash
mvn spring-boot:run
```


    
## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Data REST
- Spring Security
- MySQL
- Maven


## 🧪 Example REST Endpoints



- 🟢 **GET**: api/employees, returns a list of employees if authorized
- 🟢 **GET**: api/employees/{employeeId}, returns a specific employee
- 🟡 **POST**: api/employees, adds a new employee with the request body data
- 🟡 **PUT**: api/employees, updates an employee with the request body data with id
- 🟡 **PATCH**: api/employees/{employeeId}, patches a part of an employee data with employeeId
- 🔴 **Delete**: api/employees/{employeeId}, deletes employee based on employeeId
## 📫 Contact

Created by [Yazan Al-Qadery](https://github.com/YznAlqadery) — feel free to reach out via GitHub if you have any questions or suggestions!