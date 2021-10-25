package edu.neu.test;

import edu.neu.pojo.Cart;
import edu.neu.pojo.CartItem;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-27 17:41
 */
public class CartTest {

    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(2, "计算机网络", 1, 100.0, 100.0));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.addItem(new CartItem(2, "计算机网络", 1, 100.0, 100.0));
        cart.deleteItem(1);
        cart.clear();
        cart.addItem(new CartItem(1, "HTML语言入门", 1, 20.0, 20.0));
        cart.updateCount(1, 10);
        System.out.println(cart);
    }
}