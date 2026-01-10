# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy dependency definitions and download dependencies (for better caching)
COPY pom.xml .
RUN mvn -B -e -q dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests


# ---------- RUNTIME STAGE ----------
FROM payara/micro:latest
RUN curl -L -o /opt/payara/lib/postgresql.jar https://jdbc.postgresql.org/download/postgresql-42.7.4.jar
# Copy PostgreSQL driver
ENV JAVA_OPTIONS="-Djavax.xml.accessExternalSchema=all"
# Copy configuration and application
COPY postboot.asadmin /opt/payara/postboot.asadmin
COPY --from=build /app/target/rateuser-1.0-SNAPSHOT.war /opt/payara/deployments/rateuser.war

# Expose application port
EXPOSE 8080

# Override any existing ENTRYPOINT and run Payara Micro explicitly
ENTRYPOINT []
CMD ["java", "-jar", "/opt/payara/payara-micro.jar", "--addlibs", "/opt/payara/lib/postgresql-42.7.4.jar", "--postbootcommandfile", "/opt/payara/postboot.asadmin", "--deploy", "/opt/payara/deployments/rateuser.war", "--contextRoot", "/"]
