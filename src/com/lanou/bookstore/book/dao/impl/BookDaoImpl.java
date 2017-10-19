package com.lanou.bookstore.book.dao.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class BookDaoImpl implements BookDao {

    private QueryRunner qr = new GxQueryRunner();
    @Override
    public List<Book> findAll() {
        String sql = "select * from book where del=false";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findByCid(String cid) {
        String sql = "select * from book where del=false and cid=?";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findByBid(String bid) {
        String sql = "select * from book where del=false and bid=?";
        try {
            return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
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

    @Override
    public void updateState(String bid, boolean b) {
        String sql = "update book set del=? where bid=?";
        Object[] params = {b, bid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateBook(Book book, String bid) {
        String sql = "update book set bname=?, price=?, author=?,image=?, cid=? where del=false and bid=?";
        Object[] params = {book.getBname(), book.getPrice(),
                book.getAuthor(), book.getImage(),book.getCid(), bid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findLike(String bname) {
        String sql = "select * from book where del =false and bname like '%"+bname+"%'";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findBybname(String bname) {
        String sql = "select * from book where del=false and bname =?";
        try {
            return qr.query(sql, new BeanHandler<Book>(Book.class),bname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCount(String bname, int bcount) {
        String sql = "update book set count=? where del=false and bname=?";
        Object[] params = {bcount, bname};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
