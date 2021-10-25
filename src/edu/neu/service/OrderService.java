package edu.neu.service;

import edu.neu.pojo.Cart;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:36
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
