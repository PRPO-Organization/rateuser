package com.skupina1.rateuser.dbSetup;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@DataSourceDefinition(
        name = "java:app/jdbc/ratings",
        className = "org.postgresql.ds.PGSimpleDataSource",
        url = "jdbc:postgresql://localhost:5432/user_ratings",
        user = "david",
        password = "password",
        databaseName = "user_ratings",
        serverName = "localhost",
        portNumber = 5432,
        minPoolSize = 5,
        maxPoolSize = 20
)
@Singleton
@Startup
public class DbConfig {
    // This will create the DataSource automatically on startup
}