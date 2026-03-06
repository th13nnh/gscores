# G-Scores API (Spring Boot)

A high-performance RESTful API built with **Spring Boot** to manage and analyze student exam results.

---

## 🔄 System Flow
1. **Request:** Client sends an HTTP request (e.g., `GET /api/students/top10`).
2. **Controller:** Intercepts the request and maps it to a Service method.
3. **Service:** Contains business logic (e.g., calculating aggregate scores for Group A).
4. **Repository:** Executes optimized SQL via JPA/Hibernate to the MySQL database.
5. **Response:** Returns data in JSON format to the React frontend.

---

## 🏗️ Class Structure & Purpose

| Class | Purpose |
| :--- | :--- |
| **GscoresApplication** | The main entry point that starts the Spring context. |
| **StudentController** | Defines REST endpoints and handles incoming web requests. |
| **Student (Entity)** | Maps the Java object to the `students` table in MySQL. |
| **StudentRepository** | Handles database communication using Spring Data JPA. |
| **StudentService** | Orchestrates data processing and business logic. |
| **StudentSeeder** | Automatically populates the database with initial data on startup. |

---

## 🗄️ Database & Query Logic
* **Database:** MySQL 8.0
* **Key Query (Group A Top 10):**
  The system calculates the sum of Math, Physics, and Chemistry scores, sorts them in descending order, and limits the result to the top 10.

  ```sql
  SELECT *, (toan + vat_li + hoa_hoc) as total 
  FROM students 
  ORDER BY total DESC 
  LIMIT 10;