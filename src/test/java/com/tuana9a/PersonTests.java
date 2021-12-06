package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.dao.PersonDao;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.models.Person;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class PersonTests {

    @Test
    public void testFindAll() throws SQLException, InstantiationException, IllegalAccessException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        List<Person> result = PersonDao.getInstance().findAll();
        System.out.println(result);
    }

    @Test
    public void testFindById() throws SQLException, InstantiationException, IllegalAccessException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        Person result = PersonDao.getInstance().findById(1);
        System.out.println(result);
    }

    @Test
    public void testFindByName() throws SQLException, InstantiationException, IllegalAccessException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        Person result = PersonDao.getInstance().findBy("name", "tuana9a");
        System.out.println(result);

        Person result1 = PersonDao.getInstance().findBy("age", "19");
        System.out.println(result1);
    }

    @Test
    public void testUpdate() throws SQLException, InstantiationException, IllegalAccessException {
        //TODO
    }

    @Test
    public void testDelete() throws SQLException, InstantiationException, IllegalAccessException {
        //TODO
    }
}
