package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface OrderDao {
    void addOrder(Order order);

    List<Order> findOrderByUid(String uid);

    Order findOrderByOid(String oid);


    void updateState(String oid, int state);

    void updateAddressByOid(String oid, String address);

    void updateDel(String oid, boolean b);


    List<Order> findAllByState(int i);
}
