package edu.neu.test;

import edu.neu.pojo.Cart;
import edu.neu.pojo.CartItem;
import edu.neu.service.OrderService;
import edu.neu.service.impl.OrderServiceImpl;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:49
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(2, "计算机网络", 1, 100.0, 100.0));
        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }
}