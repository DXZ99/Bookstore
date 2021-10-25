package edu.neu.test;

import edu.neu.pojo.Book;
import edu.neu.pojo.Page;
import edu.neu.service.BookService;
import edu.neu.service.impl.BookServiceImpl;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-22 14:18
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "她改变了南宁", "周阴婷", 13.13, 130, 13, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "她改变了纽约", "凤仙花", 13.13, 130, 13, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, 2, 70, 100));
    }
}