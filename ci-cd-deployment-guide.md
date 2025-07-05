
# CI/CD and Deployment Guide

This guide provides instructions to build, deploy, and run the Movie Ticket Booking Platform using Docker and GitHub Actions.



###  Build All Services Locally

```bash
chmod +x build-all.sh
./build-all.sh
```

> This compiles and packages all Spring Boot microservices into JARs.

---

###  Docker Compose (Multi-container App)

```bash
docker-compose build
docker-compose up
```

Services exposed:
- Gateway: http://localhost:8080
- Auth: http://localhost:8088
- Booking: http://localhost:8084
- Show: http://localhost:8082
- Discount: http://localhost:8086
- Payment: http://localhost:8087
- User: http://localhost:8081
- PostgreSQL DB: `localhost:5432`

---

## GitHub Actions CI/CD

### `.github/workflows/ci.yml`

```yaml
name: CI

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout Code
      uses: actions/checkout@v3

    - name: Set up Java
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Build All Projects
      run: ./build-all.sh

    - name: Docker Build
      run: docker-compose build

    - name: Docker Save Images (optional)
      run: docker save gateway-service > gateway-service.tar
```

> Push to `main` will trigger build and Docker image packaging.

---

## Docker Image Registry (Optional)

You can push to DockerHub or GitHub Container Registry.

```yaml
    - name: Login to DockerHub
      run: echo "${{ secrets.DOCKER_PASSWORD }}" | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin

    - name: Push Images
      run: |
        docker tag gateway-service yourdockeruser/gateway-service
        docker push yourdockeruser/gateway-service
```

---

## Health Check URLs (via API Gateway)

| Service         | Health URL                       |
|-----------------|----------------------------------|
| Auth            | `/auth/actuator/health`          |
| Booking         | `/booking/actuator/health`       |
| Show            | `/show/actuator/health`          |
| Discount        | `/discount/actuator/health`      |

Make sure `spring-boot-starter-actuator` is added to each `pom.xml`.

---

## Furhter Enhancements 

- Add Swagger/OpenAPI per service
- Monitor service health via Prometheus/Grafana
- Use `nginx` or cloud load balancer for production routing
- Store logs using ELK or CloudWatch

---



---
## Deployment Guideline (CI/CD Pipeline )
[CI/CD ](ci-cd-deployment-guide.md)
---