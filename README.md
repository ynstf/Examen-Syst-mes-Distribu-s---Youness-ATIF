# Distributed Systems Exam Project

## Project Overview

This project implements a microservices-based application to manage conferences and keynotes. The architecture consists of multiple Spring Boot microservices, an Angular frontend, and security based on OAuth2 and Keycloak.

**Functional Microservices:**

* **Keynote Service**: Manages keynote speakers with attributes: id, first name, last name, email, and role.
* **Conference Service**: Manages conferences with attributes: id, title, type (academic or commercial), date, duration, number of participants, and score. Supports multiple reviews for each conference (review id, date, text, stars).

**Technical Microservices:**

* **Gateway Service**: Routes requests to functional microservices using Spring Cloud Gateway.
* **Discovery Service**: Service registry using Eureka Server.
* **Config Service**: Centralized configuration using Spring Cloud Config.

**Frontend:**

* Angular web application to interact with backend services.


**Deployment:**

* Dockerized microservices and frontend using Docker Compose.

---

## Project Structure

```
Examen-Systèmes-Distribués---Youness-ATIF/
├── angular-front-app/        # Angular frontend
├── keynote-service/          # Keynote microservice
├── conference-service/       # Conference microservice
├── gateway-service/          # Spring Cloud Gateway
├── discovery-service/        # Eureka Discovery Server
├── config-service/           # Spring Cloud Config Server
├── docker-compose.yml        # Docker Compose configuration
└── README.md                 # This file
```

---

## Step-by-Step Implementation

### 1. Project Initialization

* Created a Maven multi-module project.
* Each microservice has its own Maven module.
* Angular frontend is a separate directory.

### 2. Microservices Development

#### Discovery Service

* Implemented using **Eureka Server**.
* Configured to allow microservices registration.
* Verified by accessing `http://localhost:8761`.

#### Config Service

* Implemented using **Spring Cloud Config**.
* Provides centralized configuration for all microservices.
* Config repository initialized in `config-repo_exam`.

#### Gateway Service

* Spring Cloud Gateway forwards requests to backend microservices.
* Configured routes for `keynote-service` and `conference-service`.
* Integrated with Discovery Service to dynamically route services.

#### Keynote Service

* Developed Entities, DTOs, Repositories (DAO), Services, Mappers, and REST Controllers.
* CRUD operations for managing keynote speakers.
* Tested locally and via HTTP requests.

#### Conference Service

* Developed Entities, DTOs, Repositories (DAO), Services, Mappers, REST Controllers.
* Integrated **OpenFeign client** to communicate with Keynote Service.
* Supports multiple reviews per conference.
* Tested locally and via HTTP requests.

### 3. Angular Frontend

* Created Angular project `angular-front-app`.
* Implemented components to list and manage keynotes and conferences.
* Configured services to call backend APIs through the Gateway.
* Integrated login and authentication with Keycloak.

### 4. Security with Keycloak

* Configured Keycloak server.
* Defined realms, clients, and roles.
* Integrated microservices and Angular frontend with OAuth2/OIDC.
* Ensured secure API calls and frontend routing based on roles.

### 5. Dockerization

#### Dockerfiles

* Each Spring Boot microservice has its own Dockerfile.
* Angular app uses multi-stage Dockerfile with Node.js and Nginx.

#### Docker Compose

* Created `docker-compose.yml` to run all services together.
* Ensured proper service dependencies using `depends_on`.
* Ports mapping for local testing.

### 6. Running the Application

1. Build all Spring Boot microservices:

```bash
cd keynote-service
mvn clean package -DskipTests
# repeat for other microservices
```

2. Build and run Docker containers:

```bash
docker-compose up --build
```

3. Access services:

* Discovery: `http://localhost:8761`
* Config Service: `http://localhost:9999`
* Gateway: `http://localhost:8888`
* Keynote Service: `http://localhost:9001/api/keynotes`
* Conference Service: `http://localhost:9002/api/conferences`
* Angular Frontend: `http://localhost:4200`

---

## Notes

* Ensure ports are free before running Docker Compose.
* For local development, Eureka and Config services can be run separately to speed up testing.
* Microservices communicate via service names defined in Docker Compose.
* OAuth2 login must be done via Keycloak before accessing secured endpoints.
