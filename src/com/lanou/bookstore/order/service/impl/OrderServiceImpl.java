package com.lanou.bookstore.order.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.dao.OrderItemDao;
import com.lanou.bookstore.order.dao.impl.OrderDaoImpl;
import com.lanou.bookstore.order.dao.impl.OrderItemDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void addOrderItemList(List<OrderItem> orderItemList) {
        orderItemDao.addOrderItemList(orderItemList);
    }

    @Override
    public List<Order> findOrderByUid(String uid) {

        // 获取 orders
        List<Order> orders = orderDao.findOrderByUid(uid);
        // 遍历订单
        for (Order order : orders) {
            // 通过 oid 得到具体的 order 的所有 orderItem
            String oid = order.getOid();
            List<OrderItem> orderItemList = orderItemDao.findOrderItemByOid(oid);
            // 遍历 orderItems 存入 book
            for (OrderItem orderItem : orderItemList) {
                Book book = bookDao.findByBid(orderItem.getBid());
                orderItem.setBook(book);
            }
            order.setOrderItemList(orderItemList);
        }
        return orders;

    }

    @Override
    public Order findOrderByOid(String oid) {
        // 通过 oid 得到 order
        Order order = orderDao.findOrderByOid(oid);
        // 获取 orderItem
        List<OrderItem> orderItemList = orderItemDao.findOrderItemByOid(oid);
        for (OrderItem orderItem : orderItemList) {
            // 通过 bid 获取 book, 并把 book 给 orderItem
            orderItem.setBook(bookDao.findByBid(orderItem.getBid()));
        }
        order.setOrderItemList(orderItemList);
        return order;

    }

    @Override
    public void confirm(String oid) throws OrderException {
        // 获取 order
        Order order = orderDao.findOrderByOid(oid);
        // 只有在卖家发货后才能确定收货, 确定收货将 state 该为 3
        if (order.getState() == 2){
            orderDao.updateState(oid, 3);
        }
    }

    @Override
    public void updateAddressByOid(String oid, String address) {
        orderDao.updateAddressByOid(oid, address);
    }

    @Override
    public void updateState(Order order) {
        orderDao.updateState(order.getOid(), 1);
    }

    @Override
    public void delete(String oid) {
        // 不是真正的删除, 只是将其隐藏
        Order order = orderDao.findOrderByOid(oid);
        orderDao.updateDel(oid, !order.isDel());
    }

    @Override
    public List<Order> findAllByState(int i) {
        return orderDao.findAllByState(i);
    }

    @Override
    public List<OrderItem> findOrderItemsByOid(String oid) {
        return orderItemDao.findBid(oid);

    }

}
