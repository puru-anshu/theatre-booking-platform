#!/bin/bash

set -e

SERVICES=(
  "gateway-service"
  "booking-service"
  "show-service"
  "movie-service"
  "user-service"
  "theatre-service"
  "discount-service"
  "payment-service"
  "auth-service"
)

echo "🔨 Building all Spring Boot services..."

for SERVICE in "${SERVICES[@]}"; do
  echo "➡️  Building $SERVICE ..."
  if [ -d "$SERVICE" ]; then
    cd "$SERVICE"
    if [ -f "mvnw" ]; then
      ./mvnw clean package -DskipTests
    else
      mvn clean package -DskipTests
    fi

    if ls target/*.jar 1> /dev/null 2>&1; then
      echo "✅ $SERVICE build successful"
    else
      echo "❌ $SERVICE build failed: No JAR found in target/"
    fi
    cd ..
  else
    echo "⚠️  Directory $SERVICE does not exist"
  fi
done

echo "🎉 All builds complete."
