package com.skupina1.rateuser.dbSetup;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@DataSourceDefinition(
        name = "java:app/jdbc/ratings",
        className = "org.postgresql.ds.PGSimpleDataSource",
        serverName = "db",  // Change from "localhost" to "db"
        portNumber = 5432,
        databaseName = "rateuser",
        user = "rateuser",
        password = "rateuser"
)
@Singleton
@Startup
public class DbConfig {
    // This will create the DataSource automatically on startup
}