package com.tuana9a;

import java.sql.SQLException;

import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.utils.LogUtils;
import org.junit.Test;

public class MainTests {

    @Test
    public void test() {
        LogUtils.getLogger().info("Hello World!");
        AppConfig config = AppConfig.getInstance();
        config.load();
        System.out.println(config);
    }

    @Test
    public void testDatabase() throws SQLException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL());
        databaseConfig.setUsername(config.DATABASE_USERNAME());
        databaseConfig.setPassword(config.DATABASE_PASSWORD());

        DatabaseClient.getInstance().createConnection(databaseConfig);
    }

}

