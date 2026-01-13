# Rating Service – User Rating API

Microservice responsible for user-to-user ratings and comments, built with Jakarta EE and deployed on Azure Kubernetes Service (AKS).

---

## Overview

The Rating Service enables users to:

- Rate other users
- Prevent self-rating
- Retrieve all ratings for a user
- Retrieve all comments for a user
- Calculate the average rating for a user

---

## Technology Stack

| Component     | Technology |
|---------------|------------|
| Language      | Jakarta EE |
| ORM           | JPA        |
| Database      | PostgreSQL |
| Container     | Docker     |
| Orchestration | Azure Kubernetes Service (AKS) |
| CI/CD         | GitHub Actions → Azure Container Registry → ArgoCD |

---

## Base URL

| Environment | URL |
|-------------|-----|
| Local       | `http://localhost:8080` |

---


