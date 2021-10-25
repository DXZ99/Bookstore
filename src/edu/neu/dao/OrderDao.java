package edu.neu.dao;

import edu.neu.pojo.Order;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:12
 */
public interface OrderDao {
    public int saveOrder(Order order);
}
