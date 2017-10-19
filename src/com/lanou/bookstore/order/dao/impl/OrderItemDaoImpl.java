package com.lanou.bookstore.order.dao.impl;

import com.lanou.bookstore.order.dao.OrderItemDao;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public class OrderItemDaoImpl implements OrderItemDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public List<OrderItem> findOrderItemByOid(String oid) {
        String sql = "select * from orderitem where oid=?";
        try {
            return qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderItemList(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            String sql = "insert into orderitem values(?,?,?,?,?)";
            Object[] params = {orderItem.getIid(),
                    orderItem.getCount(),
                    orderItem.getSubtotal(),
                    orderItem.getOid(),
                    orderItem.getBid()};
            try {
                qr.update(sql,params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OrderItem> findBid(String oid) {
        String sql = "select bid from orderitem where oid=?";
        try {
            List<OrderItem> orderItemList = qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
            return orderItemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
