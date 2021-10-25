package edu.neu.test;

import edu.neu.dao.OrderDao;
import edu.neu.dao.impl.OrderDaoImpl;
import edu.neu.pojo.Order;
import org.junit.Test;

import java.util.Date;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-28 18:20
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890", new Date(), new Double(100.0), 0, 1));
    }
}