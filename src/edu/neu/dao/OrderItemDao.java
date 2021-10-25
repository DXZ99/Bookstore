package edu.neu.dao;

import edu.neu.pojo.OrderItem;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:12
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
