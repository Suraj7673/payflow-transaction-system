# 💰 PayFlow – Transaction Processing System

## 🚀 Overview

A backend system inspired by real payment platforms. Supports user management, wallets, secure money transfers, and transaction history with ACID guarantees.

---

## 🛠 Tech Stack

* Java 17, Spring Boot
* JDBC (JdbcTemplate)
* MySQL
* (Optional) React for UI

---

## 🧱 Architecture

```
com.payflow
├── controller
├── service
├── repository
├── dto
├── model
```

---

## ✨ Features

* **User Management**: create user, unique email validation
* **Wallet System**: auto-create wallet, add/check balance
* **Transfers**: debit + credit with `@Transactional` (atomic)
* **History**: SUCCESS / FAILED records for every transfer
* **Validation**: insufficient balance, invalid user
* **Security (basic)**: JWT token for protected endpoints

---

## 🔐 Data Integrity

* DB constraints (UNIQUE, NOT NULL, FK)
* Transaction rollback on failure

---

## 🧪 API Endpoints

* `POST /users`
* `POST /users/add-balance`
* `POST /users/transfer` *(JWT secured)*
* `GET /users/balance/{userId}`
* `POST /auth/login` → returns JWT

---

## ▶️ Run Locally

1. Clone repo
2. Configure DB in `application.properties`
3. Run Spring Boot app
4. Use Postman to test APIs

---

## 🧠 Key Learnings

* Layered architecture
* ACID transactions with Spring
* Handling DB constraints & failures
* Building secure REST APIs

---

## 🚀 Future Improvements

* Full Spring Security config
* Better error responses (global handler)
* Concurrency control (locking)
* Swagger docs



