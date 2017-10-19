package com.lanou.bookstore.book.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookException;
import com.lanou.bookstore.book.service.BookService;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByCid(String cid) {
        return bookDao.findByCid(cid);
    }

    @Override
    public Book findByBid(String bid) {
        return bookDao.findByBid(bid);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public List<Book> del(String bid) throws BookException {
        Book book = bookDao.findByBid(bid);
        if (book.isDel()){
            throw new BookException("该书已被删除");
        }
        bookDao.updateState(book.getBid(), !book.isDel());
        return bookDao.findAll();
    }

    @Override
    public List<Book> update(Book book, String bid) {
        bookDao.updateBook(book, bid);
        return bookDao.findAll();
    }

    @Override
    public List<Book> findLike(String bname) {
        return bookDao.findLike(bname);
    }

    @Override
    public int updateCount(String bname, int count) {
        // 找到书
        Book book = bookDao.findBybname(bname);
        // 修改数量, 返回剩余数量
        int bcount = book.getCount()-count;
        bookDao.updateCount(bname, bcount);
        return bcount;
    }
}
