package com.lanou.bookstore.book.dao;

import com.lanou.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface BookDao {
    List<Book> findAll();

    List<Book> findByCid(String cid);

    Book findByBid(String bid);

    void addBook(Book book);

    void updateState(String bid, boolean b);

    void updateBook(Book book, String bid);

    List<Book> findLike(String bname);

    Book findBybname(String bname);

    void updateCount(String bname, int bcount);
}
