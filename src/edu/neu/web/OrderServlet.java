package edu.neu.web;

import edu.neu.pojo.Cart;
import edu.neu.pojo.User;
import edu.neu.service.OrderService;
import edu.neu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 19:08
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取Cart购物车对象、用户的UserId
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            //若用户未登录跳转到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        //2.调用OrderService的createOrder方法生成订单
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        //3.把订单号保存到Session域中
        req.getSession().setAttribute("orderId", orderId);
        //4.请求重定向到checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
