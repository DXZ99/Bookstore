package edu.neu.web;

import edu.neu.pojo.Book;
import edu.neu.pojo.Page;
import edu.neu.service.BookService;
import edu.neu.service.impl.BookServiceImpl;
import edu.neu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-22 14:25
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳到图书列表页面
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        //pageNo+1保证永远在最后一页
        pageNo += 1;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数id,图书编号
        String id = req.getParameter("id");
        //2.调用bookService.deleteBookById()，删除图书
        int i = WebUtils.parseInt(id, 0);
        bookService.deleteBookById(i);
        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        //2.调用bookService.updateBook修改图书
        bookService.updateBook(book);
        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3.保存图书到request域中
        req.setAttribute("book", book);
        //4.请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        req.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面，注意第一个斜杠表示到工程下
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo, pageSize)方法
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置分页条的url
        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
