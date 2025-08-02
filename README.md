# 📘 Subscription Management API

A modular and secure backend system built with **Java 17** and **Spring Boot**, designed using the principles of **Clean Architecture** and the **Hexagonal Architecture (Ports and Adapters)** pattern.

This project serves as a foundation for any application that requires user subscriptions and billing logic. It is production-ready and structured to be maintainable, extensible, and testable.

---

## 🚀 Features

- 🔐 **Spring Security** for customizable authentication
- 📦 **Spring Data JPA** with **H2 in-memory database** for development
- 🧩 Clean modular layers: Domain, Application (Use Cases), Infrastructure
- 🔄 DTO mapping and clear separation between external interfaces and internal logic
- 📂 Domain modules: `User`, `Plan`, `Subscription`
- 📖 Ready for Swagger/OpenAPI integration
- 🧪 Test-ready architecture (JUnit support included)
- 🐳 Docker-ready (optional)
- 📜 MIT Licensed

---

## 🧠 Architecture Overview

The project follows a **hexagonal architecture**:


Each domain module (`user`, `plan`, `subscription`) has the same internal structure:
- `domain` — business models and interfaces
- `application` — use cases and DTOs
- `infrastructure` — adapters: controllers, persistence (JPA), mappers

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3+
- Spring Data JPA
- Spring Security (CSRF disabled)
- H2 Database
- Gradle
- Lombok
- MapStruct (or custom mappers)
- Swagger/OpenAPI-ready

---

## ⚙️ Getting Started

### ✅ Prerequisites

- Java 17+
- Gradle (or use the included wrapper)
- (Optional) Docker installed

---

### 🧪 Running the App Locally

```bash
# 1. Clone the repo
git clone https://github.com/your-username/subscription-management-api.git
cd subscription-management-api

# 2. Build the project
./gradlew build

# 3. Run the app
./gradlew bootRun
```
The application will start on:
📍 http://localhost:8080

## Swagger

Access to the API documentation via UI:

http://localhost:8080/swagger-ui/index.html

## 🗃️ Default Credentials
If you're using Spring Security's default setup:

Username: user

Password: Printed in console on startup (look for: Using generated security password:)

📌 To disable or customize this behavior, configure your SecurityConfig.

## 🧪 Example Endpoints (Postman)
POST /api/users — Create a new user

GET /api/plans — List all available plans

POST /api/subscriptions — Subscribe a user to a plan

All endpoints are secured by default and require Basic Authentication headers.

## ⚙️ H2 Console
Access the in-memory database at:
📍 http://localhost:8080/h2-console

Use the following config:

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (leave empty)

## 📄 License
This project is licensed under the MIT License — see the LICENSE file for details.

## 🙋‍♂️ Author
### Andrés David Quintero Caicedo
Developed as part of a technical interview process for Pragma.
Feel free to explore, test, and improve.

## 📌 Disclaimer
This project is a technical showcase and intentionally modular to reflect real-world application design patterns (DDD + Hexagonal Architecture). It can be adapted for production use with externalized configurations, security providers, and database engines.
