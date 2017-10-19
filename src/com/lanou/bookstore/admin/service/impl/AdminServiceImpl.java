package com.lanou.bookstore.admin.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.admin.dao.AdminDao;
import com.lanou.bookstore.admin.dao.impl.AdminDaoImpl;
import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.admin.service.AdminException;
import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.book.service.BookException;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.order.dao.OrderItemDao;
import com.lanou.bookstore.order.dao.impl.OrderItemDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public Admin findAdmin(Admin formAdmin) {
        return adminDao.findAdmin(formAdmin);
    }

    @Override
    public List<Category> findAllCategory() {
        return adminDao.findAllCategory();
    }

    @Override
    public void addCategroy(Category category) {
        adminDao.addCategory(category);
    }


    @Override
    public void deleteCategory(String cid) throws AdminException {
        List<Book> books = bookDao.findByCid(cid);
        if (books.isEmpty()) {
            adminDao.deleteCategory(cid);
        }else {
            throw new AdminException("该类中存有图书, 无法删除");
        }
    }

    @Override
    public void editCategory(String cid, String cname) {
        adminDao.editCategory(cid, cname);
    }

    @Override
    public List<Book> findAllBook() {
        return adminDao.findAllBook(false);
    }

    @Override
    public Book findBookByBid(String bid) {
        return adminDao.findBookByBid(bid);
    }

    @Override
    public List<Book> deleteBookByBid(String bid, boolean b){
        adminDao.updateBookState(bid, !b);
        return adminDao.findAllBook(b);
    }

    @Override
    public List<Book> updateBook(Book book, String bid) {
        adminDao.updateBook(book, bid);
        return adminDao.findAllBook(false);
    }

    @Override
    public void addBook(Book book) {
        adminDao.addBook(book);
    }

    @Override
    public List<Order> findAllOrder() {
        // 获取全部订单
        List<Order> orders = adminDao.findAllOrder();
        // 遍历订单
        for (Order order : orders) {
            // 通过 oid 查到所有的 ordeItem, 遍历orderItem
            List<OrderItem> orderItems = orderItemDao.findOrderItemByOid(order.getOid());
            for (OrderItem orderItem : orderItems) {
                // 通过 bid 得到 book 并将值赋给 orderItem
                orderItem.setBook(adminDao.findBookByBid(orderItem.getBid()));
            }
            // 将 orderItem 赋值给 order
            order.setOrderItemList(orderItems);
        }
        return orders;
    }

    @Override
    public void consignment(String oid) {
        adminDao.updateOrderState(oid, 2);
    }

    @Override
    public void confirmAdmin(String oid) {
        adminDao.updateOrderState(oid, 4);
    }


    @Override
    public List<Order> findOrderByUid(String uid) {
        List<Order> orders = adminDao.findAllOrdersByUid(uid);
        save(orders);
        return orders;
    }

    @Override
    public List<Order> findOrderByUidAndState(String uid, int i) {
        List<Order> orders = adminDao.findAllOrdersByUidAndState(uid, i);
        save(orders);
        return orders;
    }

    @Override
    public List<Order> findOrderFinished(String uid) {
        List<Order> orders = adminDao.findAllOrdersFinished(uid);
        save(orders);
        return orders;
    }

    @Override
    public List<Book> findBookByDel(boolean b) {
        return adminDao.findAllBook(b);
    }

    private void save(List<Order> orders) {
        for (Order order : orders) {
            // 通过 oid 得到具体的 order 的所有 orderItem
            List<OrderItem> orderItemList = orderItemDao.findOrderItemByOid(order.getOid());
            // 遍历 orderItems 存入 book
            for (OrderItem orderItem : orderItemList) {
                Book book = bookDao.findByBid(orderItem.getBid());
                orderItem.setBook(book);
            }
            order.setOrderItemList(orderItemList);
        }
    }


}
