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

/**
 * @author Zhao Dongxiao
 * @create 2020-12-24 11:39
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo, pageSize)方法
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置分页条的url
        page.setUrl("client/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2.调用bookService.pageByPrice(pageNo, pageSize, min, max)方法
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //设置分页条的url
        //如果有最小价格和最大价格的参数，追加到分页条的地址中
        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null) {
            url.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            url.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(url.toString());
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
