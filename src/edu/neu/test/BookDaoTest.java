package edu.neu.test;

import edu.neu.dao.BookDao;
import edu.neu.dao.impl.BookDaoImpl;
import edu.neu.pojo.Book;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-22 13:33
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "她改变了南宁", "周阴婷", 13.13, 130, 13, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "她改变了纽约", "凤仙花", 13.13, 130, 13, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        bookDao.queryForPageItems(8, 4).forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(70, 100));
    }

    @Test
    public void queryForPageItemsByPrice() {
        bookDao.queryForPageItemsByPrice(1, 2, 70, 100).forEach(System.out::println);
    }
}