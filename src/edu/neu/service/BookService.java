package edu.neu.service;

import edu.neu.pojo.Book;
import edu.neu.pojo.Page;

import java.util.List;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-22 14:00
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
