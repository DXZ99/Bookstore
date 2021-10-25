package edu.neu.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 *
 * @author Zhao Dongxiao
 * @create 2020-12-27 16:34
 */
public class Cart {
    //private Integer totalCount;
    //private Double totalPrice;

    //key是商品编号，value是商品信息
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    //购物车添加商品
    public void addItem(CartItem cartItem) {
        //先查看购物车中是否已经有此商品
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //之前未添加过此商品
            items.put(cartItem.getId(), cartItem);
        } else {
            //之前添加过此商品，数量加1，更新总金额
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice() * item.getCount());
        }

    }

    //购物车删除商品
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    //清空购物车
    public void clear() {
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id, Integer count) {
        //先查看购物车中是否有此商品，有则修改数量和金额
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            if (count <= 0) count = 1;
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
        }
    }
}
