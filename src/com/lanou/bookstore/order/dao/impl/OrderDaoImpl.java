package com.lanou.bookstore.order.dao.impl;

import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderDaoImpl implements OrderDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public void addOrder(Order order) {
        String sql = "insert into orders values(?,?,?,?,?,?,?)";
        Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), order.getUid(), order.getAddress(),false};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Order> findOrderByUid(String uid) {
        String sql = "select * from orders where del=false and uid=?";
        try {
            // 获取全部订单
            List<Order> orders = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order findOrderByOid(String oid) {
        String sql = "select * from orders where del=false and oid=?";
        try {
            return qr.query(sql, new BeanHandler<Order>(Order.class), oid);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String oid, int state) {
        String sql = "update orders set state=? where del=false and oid=?";
        Object[] params = {state, oid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateAddressByOid(String oid, String address) {
        String sql = "update orders set address=? where del=false and oid=?";
        Object[] params = {address, oid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDel(String oid, boolean b) {
        String sql ="update orders set del = ? where oid =?";
        Object[] params = {b, oid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAllByState(int i) {
        String sql = "select * from orders where state=?";
        try {
            List<Order> orders = qr.query(sql, new BeanListHandler<Order>(Order.class), i);
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
