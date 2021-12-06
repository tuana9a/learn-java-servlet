package com.tuana9a;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.dao.PersonDao;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.models.Person;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;

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

        Person result = PersonDao.getInstance().findById(1L);
        System.out.println(result);
    }

    @Test
    public void testFindBy() throws SQLException, InstantiationException, IllegalAccessException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        Person person = PersonDao.getInstance().findBy("name", "tuana9a");
        System.out.println(person);
        Assert.assertNotEquals(person, null);

        Person person1 = PersonDao.getInstance().findBy("age", "18");
        Assert.assertNotEquals(person1, null);
    }

    @Test
    public void testInsert() throws SQLException, InstantiationException, IllegalAccessException, ExecutionException, InterruptedException, TimeoutException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        long id = System.currentTimeMillis();
        Person person = new Person();
        person.setAge(100);
        person.setName("bot" + id);
        person.setId(id);
        person.setDeleted(false);
        boolean success = PersonDao.getInstance().insert(person);

        Assert.assertTrue(success);
    }

    @Test
    public void testUpdate() throws SQLException, InstantiationException, IllegalAccessException {
        AppConfig config = AppConfig.getInstance();
        config.load();

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setUrl(config.DATABASE_URL);
        databaseConfig.setUsername(config.DATABASE_USERNAME);
        databaseConfig.setPassword(config.DATABASE_PASSWORD);

        DatabaseClient.getInstance().createConnection(databaseConfig);

        long id = 1638791839823L;
        Person person = new Person();
        person.setAge(100 + 10);
        person.setName("updated");
        person.setId(id);
        person.setDeleted(false);
        boolean success = PersonDao.getInstance().update(person);
        Assert.assertTrue(success);
    }

}
