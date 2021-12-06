package com.tuana9a;

import com.tuana9a.dao.BookDao;
import com.tuana9a.hibernate.HBDatabaseClient;
import com.tuana9a.models.Book;
import org.junit.Test;

public class BookTests {

    @Test
    public void testFindAll() {
        HBDatabaseClient hbDatabaseClient = HBDatabaseClient.getInstance();
        hbDatabaseClient.createSessionFactory();
        BookDao dao = BookDao.getInstance();

        dao.findAll().forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        HBDatabaseClient hbDatabaseClient = HBDatabaseClient.getInstance();
        hbDatabaseClient.createSessionFactory();
        BookDao dao = BookDao.getInstance();

        dao.update(new Book(1L, "book1", "pub1"));
        System.out.println(dao.findById(1L));
    }

    @Test
    public void testFindByName() {
        HBDatabaseClient hbDatabaseClient = HBDatabaseClient.getInstance();
        hbDatabaseClient.createSessionFactory();
        BookDao dao = BookDao.getInstance();

        System.out.println(dao.findByName("book1"));
    }

}
