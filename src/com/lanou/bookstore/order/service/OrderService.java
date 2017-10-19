package com.lanou.bookstore.order.service;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface OrderService {
    void addOrder(Order order);

    void addOrderItemList(List<OrderItem> orderItemList);

    List<Order> findOrderByUid(String uid);

    Order findOrderByOid(String oid);

    void confirm(String oid) throws OrderException;

    void updateAddressByOid(String oid, String address);

    void updateState(Order order);

    void delete(String oid);

    List<Order> findAllByState(int i);


    List<OrderItem> findOrderItemsByOid(String oid);
}
