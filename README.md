# PayFlow – Wallet & Transaction System

A full-stack wallet application where users can log in, check balance, transfer money, and view transaction history.

I built this project to understand how secure payment systems work and how frontend and backend communicate in a real-world application.

---

## Features

- JWT authentication
- Wallet balance system
- Transfer money between users
- Transaction history
- Failed transaction handling
- Secure backend APIs
- React dashboard UI

---

## Screenshots

### Dashboard
![Dashboard](./screenshots/dashboard.png)

### Transactions
![Transactions](./screenshots/transactions.png)

### Login
![Login](./screenshots/login.png)

---

## Tech Stack

### Frontend
- React
- Axios

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication

### Database
- MySQL

---

## How it works

The frontend communicates with the Spring Boot backend using REST APIs.

The backend:
- validates JWT tokens
- manages wallet balances
- performs secure money transfers
- stores transaction history in MySQL

Transfers are handled using `@Transactional` to maintain consistency during balance updates.

---

## What I focused on

The main goal was making transfers safe and reliable.

Things I focused on:
- secure authentication using JWT
- transaction-safe balance transfers
- handling insufficient balance cases
- storing failed transactions
- frontend and backend integration
- clean API structure

---

## API Endpoints

### Auth
- `POST /auth/login`

### Wallet
- `GET /wallet/balance`

### Transfer
- `POST /transfer`

### Transactions
- `GET /transactions`

---

## Running the project locally

### Backend

1. Configure MySQL in `application.properties`
2. Run the Spring Boot application

### Frontend

```bash
cd payflow-ui
npm install
npm start
```

---

## What I learned

- JWT authentication flow
- React + Spring Boot integration
- Database transactions and consistency
- REST API design
- Error handling in frontend and backend
- How payment systems maintain balance integrity

---

## Future Improvements

- User registration
- Email-based transfers
- Better UI styling
- Docker deployment
- Cloud database hosting

---