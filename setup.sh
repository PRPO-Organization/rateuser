#!/bin/bash
echo "Restoring Docker setup..."

# Create Dockerfile
cat > Dockerfile << 'EOF'
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM payara/micro:7.2025.2
COPY --from=build /app/target/rateuser.war /opt/payara/deployments/
EXPOSE 8080
CMD ["--deploymentDir", "/opt/payara/deployments", "--nocluster"]
EOF

echo "Dockerfile created!"

# Create docker-compose.yml if missing
if [ ! -f docker-compose.yml ]; then
    cat > docker-compose.yml << 'EOF'
version: '3.8'
services:
  rateuser-db:
    image: postgres:15
    environment:
      POSTGRES_DB: ratedb
      POSTGRES_USER: ratedb
      POSTGRES_PASSWORD: ratedb
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  rateuser-app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - rateuser-db

volumes:
  postgres_data:
EOF
    echo "docker-compose.yml created!"
fi

# Make executable
chmod +x restore-docker.sh
