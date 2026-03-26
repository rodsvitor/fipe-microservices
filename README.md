# 🚗 FIPE Vehicle Integration System

## 📌 Overview

This project implements a distributed system that integrates with the FIPE API to load, process, and expose vehicle data.

The architecture is based on **microservices**, using asynchronous processing with RabbitMQ, caching with Redis, and secure access via JWT authentication.

---

## ⚙️ Technologies

* Java + Spring Boot
* PostgreSQL
* RabbitMQ
* Redis
* Docker / Docker Compose
* JWT Authentication

---

## 🚀 Running the Project

### 1. Start infrastructure

```bash
# Run everything
docker-compose up --build -d
```

### Services

| Service     | URL / Port                                       |
| ----------- | ------------------------------------------------ |
| PostgreSQL  | localhost:5432                                   |
| RabbitMQ    | localhost:5672                                   |
| RabbitMQ UI | [http://localhost:15672](http://localhost:15672) |
| Redis       | localhost:6379                                   |

### 2. Run applications

Run both services:

* API-1 → port 8080
* API-2 → port 8081

---

## 📖 Swagger / API Documentation

All API endpoints are documented and available via **Swagger UI**:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

You can explore available endpoints, see request/response schemas, and test the API directly from the browser.

---

## 🧱 Architecture

```
Client
   ↓
API-1 (Gateway)
   ↓ (HTTP)
API-2 (Core Service)
   ↓
PostgreSQL

Async Flow:
API-1 → RabbitMQ → API-2
```

### Responsibilities

#### API-1 (Gateway)

* Exposes REST endpoints
* Handles authentication (JWT)
* Uses Redis for caching
* Communicates with API-2 via HTTP
* Sends messages to RabbitMQ

#### API-2 (Core)

* Consumes messages from RabbitMQ
* Integrates with FIPE API
* Persists data in PostgreSQL
* Exposes internal REST endpoints

---

## 🔄 Initial Data Load

Trigger initial FIPE data load:

```bash
POST /load
```

This will:

1. Fetch brands from FIPE API
2. Send them to RabbitMQ
3. API-2 processes and stores vehicle models

---

## 🔐 Authentication

### Login

```bash
POST /auth/login
```

```json
{
  "username": "admin",
  "password": "admin"
}
```

### Response

```json
{
  "token": "JWT_TOKEN"
}
```

### Use token

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 API Endpoints (API-1)

### 🔹 Get Brands

```bash
GET /vehicles/brands
```

### 🔹 Get Vehicles by Brand

```bash
GET /vehicles?brand=Fiat
```

### 🔹 Update Vehicle

```bash
PUT /vehicles/{id}
```

```json
{
  "model": "Updated Model",
  "observations": "Updated manually"
}
```

---

## ⚡ Caching (Redis)

* Brands and vehicles are cached in Redis
* Cache is invalidated on updates
* TTL configured for automatic expiration

---

## 📨 Messaging (RabbitMQ)

* Queue: `brand.queue`
* API-1 publishes brand messages
* API-2 consumes and processes asynchronously

---

## 🗄️ Database

* PostgreSQL used for persistence
* Vehicles stored with:

    * internal ID
    * FIPE code
    * brand
    * model
    * observations

---

## 🧠 Design Decisions

* **Clean Architecture**: separation of domain, application, and infrastructure layers
* **API-1 as Gateway**: centralizes authentication and caching
* **API-2 as Core Service**: handles business logic and persistence
* **Asynchronous Processing**: improves scalability using RabbitMQ
* **Redis Cache**: improves performance and reduces load on API-2

---

## 🛠️ Improvements (Future Work)

* Retry + Dead Letter Queue (DLQ)
* Pagination on endpoints
* Better error handling
* Role-based authorization
* Observability (logs/metrics)

---

## 👨‍💻 Author

Rodrigo Souza
