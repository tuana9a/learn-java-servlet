package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.dao.BookDao;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.hibernate.HBDatabaseClient;
import com.tuana9a.models.Book;
import com.tuana9a.utils.LogUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainTests {

    @Test
    public void test() {
        LogUtils.getLogger().info("Hello World!");
        AppConfig config = AppConfig.getInstance();
        config.load();
        System.out.println(config);
    }

    public String makeUrl(String folderPath, String fileName) {
        return "/explorer.exe" + (folderPath.equals("/") ? "" : folderPath) + "/" + fileName;
    }

    //@Test
    public void test1() {
        System.out.println(makeUrl("/", "test"));
        System.out.println(makeUrl("/", "test.txt"));
        System.out.println(makeUrl("/test", "test"));
        System.out.println(makeUrl("/test", "test.txt"));
    }

    //@Test
    public void test2() throws SQLException {
        AppConfig config = AppConfig.getInstance();
        DatabaseClient databaseClient = DatabaseClient.getInstance();

        config.load();
        databaseClient.createConnection(config.DATABASE_URL, config.DATABASE_USERNAME, config.DATABASE_PASSWORD);
    }

    //@Test
    public void test3() throws SQLException, ExecutionException, InterruptedException {
        HBDatabaseClient hbDatabaseClient = HBDatabaseClient.getInstance();
        List<String> mapResources = new LinkedList<>();
        mapResources.add("Book.hbm.xml");
        hbDatabaseClient.createSessionFactory(mapResources);
        BookDao dao = BookDao.getInstance();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future<?> t1 = executorService.submit(() -> dao.findAll().forEach(System.out::println));
        Future<?> t2 = executorService.submit(() -> dao.update(new Book(1L, "book1", "pub1")));
        Future<?> t3 = executorService.submit(() -> System.out.println(dao.findByName("book1")));
//        executorService.submit(() -> dao.delete(0));
        t1.get();
        t2.get();
        t3.get();
    }

}

