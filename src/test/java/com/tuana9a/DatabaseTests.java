package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.database.DatabaseClient;
import org.junit.Test;

import java.sql.SQLException;

public class DatabaseTests {
    @Test
    public void test() throws SQLException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);
    }
}
