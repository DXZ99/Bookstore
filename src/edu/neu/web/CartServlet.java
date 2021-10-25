package edu.neu.web;

import com.google.gson.Gson;
import edu.neu.pojo.Book;
import edu.neu.pojo.Cart;
import edu.neu.pojo.CartItem;
import edu.neu.service.BookService;
import edu.neu.service.impl.BookServiceImpl;
import edu.neu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-27 18:11
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById得到图书信息
        Book book = bookService.queryBookById(id);
        //3.把图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4.调用Cart.addItem添加商品项
        //把购物车保存在Session中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后添加商品的名字保存到Session域中
        req.getSession().setAttribute("lastName", cartItem.getName());
        //5.重定向回原来商品所在的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 使用AJAX请求加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById得到图书信息
        Book book = bookService.queryBookById(id);
        //3.把图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4.调用Cart.addItem添加商品项
        //把购物车保存在Session中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后添加商品的名字保存到Session域中
        req.getSession().setAttribute("lastName", cartItem.getName());

        //5.返回购物车总的商品数量和最后一个添加的商品名称
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.获取购物车对象，删除购物车商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //3.删除之后，重定向回原来的购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.调用clear方法
        if (cart != null) {
            cart.clear();
        }
        //3.重定向回原来的购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改购物车中的商品数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数：商品编号、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //2.获取购物车对象，调用update方法
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        //3.重定向回原来的购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
