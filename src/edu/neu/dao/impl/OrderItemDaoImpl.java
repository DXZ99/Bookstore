package edu.neu.dao.impl;

import edu.neu.dao.OrderItemDao;
import edu.neu.pojo.OrderItem;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:14
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
