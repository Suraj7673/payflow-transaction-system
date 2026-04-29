# PayFlow – Transaction System

## What is this?

This is a backend project where users can create accounts, add money to their wallet, and transfer money to other users.

I built this to understand how real payment systems handle transactions safely.

---

## Features

* Create user
* Auto-create wallet for each user
* Add balance
* Transfer money between users
* Check balance
* Store transaction history (success / failed)

---

## What I focused on

The main goal was to make sure money transfer is safe.

* Used transactions (`@Transactional`) so money is not lost
* Checked for insufficient balance
* Prevented duplicate users using unique email
* Stored every transfer in database

---

## Tech Used

* Java
* Spring Boot
* JDBC
* MySQL

---

## How to run

1. Clone the repo
2. Update database config in `application.properties`
3. Run the Spring Boot app
4. Use Postman to test APIs

---

## APIs

* POST /users → create user
* POST /users/add-balance
* POST /users/transfer
* GET /users/balance/{userId}

---

## What I learned

* How transactions work in backend
* Why atomic operations are important in payments
* How to structure a backend project properly
* Handling edge cases like invalid users and low balance

---

## Future improvements

* Add authentication (JWT)
* Better error handling
* Improve API responses

---
