#!/bin/bash

# List of services
SERVICES=(
  "booking-service"
  "show-service"
  "movie-service"
  "user-service"
  "theatre-service"
  "discount-service"
  "payment-service"
  "gateway-service"
)

# Base package path
BASE_PACKAGE_PATH="com/tech"

# Create folder structure in each service
for SERVICE in "${SERVICES[@]}"; do
  echo "📁 Setting up $SERVICE ..."

  JAVA_SRC="$SERVICE/src/main/java/$BASE_PACKAGE_PATH"
  RESOURCES="$SERVICE/src/main/resources"

  # Determine the package name by stripping "-service"
  PACKAGE_NAME="${SERVICE/-service/}"

  # Create folders
  mkdir -p "$JAVA_SRC/$PACKAGE_NAME/controller"
  mkdir -p "$JAVA_SRC/$PACKAGE_NAME/service"
  mkdir -p "$JAVA_SRC/$PACKAGE_NAME/dto"
  mkdir -p "$JAVA_SRC/$PACKAGE_NAME/model"
  mkdir -p "$JAVA_SRC/$PACKAGE_NAME/repository"
  mkdir -p "$RESOURCES"

  echo "✅ $SERVICE structure created under $JAVA_SRC/$PACKAGE_NAME/"
done

echo "🎉 All service structures initialized."
