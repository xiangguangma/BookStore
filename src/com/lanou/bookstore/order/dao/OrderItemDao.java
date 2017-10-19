package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public interface OrderItemDao {
    List<OrderItem> findOrderItemByOid(String oid);

    void addOrderItemList(List<OrderItem> orderItemList);

    List<OrderItem> findBid(String oid);
}
