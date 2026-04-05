# 💰 Finance Dashboard Backend (Spring Boot)

## 📌 Project Overview
This project is a backend system for a finance dashboard application. It manages financial records, users, roles, and provides summary analytics such as total income, expenses, and category-wise insights.

The system is built using Spring Boot, PostgreSQL, and follows a clean layered architecture.

---

## 🚀 Features Implemented

### 🔐 User & Role Management
- Create and manage users
- Assign roles (ADMIN, ANALYST, VIEWER)
- Role stored in a separate table and linked via foreign key
- User status (ACTIVE / INACTIVE)

---

### 💳 Financial Records Management
- Create, update, delete financial records
- Fields:
  - Amount
  - Record Type (Income / Expense)
  - Category
  - Description
  - Created Date (auto-generated using @PrePersist)
- Filtering support:
  - Record type
  - Category
  - Date range

---

### 📊 Dashboard API
Provides aggregated dashboard data:
- Total Income
- Total Expenses
- Net Balance
- Recent Transactions (last 5 records)
- Category-wise summary

---

### 🔒 Role-Based Access (Without Spring Security)
Custom role validation implemented using utility class:

- ADMIN → Full access
- ANALYST → Read + Dashboard access
- VIEWER → Dashboard only

Access checks are handled in the service layer.

---

### ⚠️ Global Exception Handling
Centralized exception handling using @ControllerAdvice.

Handled errors:
- 400 → Bad Request
- 404 → Resource Not Found
- 403 → Forbidden (Unauthorized access)
- 500 → Internal Server Error

---

### 🔄 DTO Mapping
Used ModelMapper for:
- Entity → DTO conversion
- Cleaner and maintainable service logic

---

## 🗄️ Database Design

### Tables
- user
- role
- record

### Relationships
- User → Role (Many-to-One)
- Record → createdBy (User reference via ID)

---

## 🧠 Key Design Decisions
- Business logic implemented in Service layer
- Used Java Streams for filtering and aggregation
- Clean separation of Entity and DTO
- Modular structure for scalability

---

## 🛠️ Tech Stack
- Java
- Spring Boot
- PostgreSQL
- JPA / Hibernate
- ModelMapper
- Swagger (OpenAPI)

---

## 📂 Project Structure

controller/
    DashboardController
    RecordController

service/
    DashboardService
    RecordService

repository/
    RecordRepository
    UserRepository
    RoleRepository

entity/
    UserEntity
    RoleEntity
    RecordEntity

dto/
    RecordDTO
    DashboardDTO

exception/
    GlobalExceptionHandler
    ResourceNotFoundException
    UnauthorizedException

util/
    AccessUtil

---

## 🔌 API Overview

### User APIs
- Create User
- Get User
- Get All Users
- Update User
- Delete User
- Update Status of the User

### Record APIs
- Create Record
- Get Records (with filters)
- Update Record
- Delete Record

### Dashboard API
- Get dashboard summary

---

## 🔍 Filtering Logic

Supports optional filters:
- recordType
- category
- startDate
- endDate

Logic:
startDate <= recordDate <= endDate

---

## ⚙️ Setup Instructions

1. Clone the repository
git clone <your-repo-url>

2. Configure PostgreSQL in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

3. Run the application
mvn spring-boot:run

4. Access Swagger UI
http://localhost:8080/swagger-ui.html

---

## 🧪 Sample Dashboard Response

{
  "totalIncome": 10000,
  "totalExpenses": 4000,
  "netBalance": 6000,
  "recentTransactions": [],
  "categorySummary": []
}

---

## 📈 Future Enhancements
- Add Spring Security (JWT authentication)
- Optimize queries using database aggregation (SUM, GROUP BY)
- Add pagination and sorting
- Add monthly/weekly trends
- Implement caching

---

## 🎯 Conclusion
This project demonstrates backend development concepts including CRUD operations, filtering, aggregation, role-based access control, and structured API design using Spring Boot.
