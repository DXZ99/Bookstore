package edu.neu.test;

import edu.neu.dao.OrderItemDao;
import edu.neu.dao.impl.OrderItemDaoImpl;
import edu.neu.pojo.OrderItem;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:20
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "PHP从入门到精通", 1, new Double(100),
                new Double(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Ruby从入门到精通", 2, new Double(100),
                new Double(200), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "React从入门到精通", 1, new Double(30),
                new Double(30), "1234567890"));
    }
}