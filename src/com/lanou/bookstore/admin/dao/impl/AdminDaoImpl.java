package com.lanou.bookstore.admin.dao.impl;

import com.lanou.bookstore.admin.dao.AdminDao;
import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public class AdminDaoImpl implements AdminDao {

    private QueryRunner qr = new GxQueryRunner();

    /*
        管理员的查询
     */
    @Override
    public Admin findAdmin(Admin formAdmin) {
        String sql = "select * from admin where aname=? and password=?";
        Object[] params = {formAdmin.getAname(), formAdmin.getPassword()};
        try {
            return qr.query(sql, new BeanHandler<Admin>(Admin.class), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
        分类的操作
     */

    @Override
    public List<Category> findAllCategory() {
        String sql = "select * from category";
        try {
            return qr.query(sql, new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCategory(Category category) {
        String sql = "insert into category values (?,?)";
        Object[] params = {category.getCid(), category.getCname()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(String cid) {
        String sql = "delete from category where cid=?";
        try {
            qr.update(sql, cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editCategory(String cid, String cname) {
        String sql = "update category set cname =? where cid =?";
        Object[] params = {cname, cid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
        图书的操作
     */

    @Override
    public List<Book> findAllBook(boolean b) {
        String sql = "select * from book where del = ?";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class), b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findBookByBid(String bid) {
        String sql = "select * from book where bid=?";
        try {
            return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBookState(String bid, boolean b) {
        String sql = "update book set del=? where bid=?";
        Object[] params = {b, bid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String bid) {
        String sql = "delete from book where bid=?";
        try {
            qr.update(sql, bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book, String bid) {
        String sql = "update book set bname=?, price=?, author=?,image=?, cid=? where bid=?";
        Object[] params = {book.getBname(), book.getPrice(),
                book.getAuthor(), book.getImage(),book.getCid(), bid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBook(Book book) {
        String sql = "insert into book values(?,?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(),
                book.getBname(),
                book.getPrice(),
                book.getAuthor(),
                book.getImage(),
                book.getCid(),
                book.isDel(),
                book.getCount()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
        订单的操作
     */

    @Override
    public List<Order> findAllOrder() {
        String sql = "select * from orders";
        try {
            return qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderState(String oid, int i) {
        String sql = "update orders set state=? where oid=?";
        Object[] params = {i, oid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUid(String uid) {
        String sql = "select * from orders where uid=?";
        try {
            // 获取全部订单
            return qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUidAndState(String uid, int i) {
        String sql = "select * from orders where uid=? and state =?";
        Object[] params = {uid, i};
        try {
            // 获取 用户订单中 state 为 i 的全部订单
            return qr.query(sql, new BeanListHandler<Order>(Order.class), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersFinished(String uid) {
        String sql = "select * from orders where state >= 3";
        try {
            // 获取 用户订单中 state 大于 3 的全部订单
            return qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
