package edu.neu.service.impl;

import edu.neu.dao.BookDao;
import edu.neu.dao.OrderDao;
import edu.neu.dao.OrderItemDao;
import edu.neu.dao.impl.BookDaoImpl;
import edu.neu.dao.impl.OrderDaoImpl;
import edu.neu.dao.impl.OrderItemDaoImpl;
import edu.neu.pojo.*;
import edu.neu.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:36
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号要保证唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //1.创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //2.保存订单
        orderDao.saveOrder(order);
        //3.保存购物车中的每个商品为每个订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            //保存好订单项，要修改销量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //4.清空购物车
        cart.clear();
        return orderId;
    }
}
