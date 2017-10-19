package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface BookService {

    List<Book> findAll();

    List<Book> findByCid(String cid);

    Book findByBid(String bid);

    void addBook(Book book);

    List<Book> del(String bid) throws BookException;

    List<Book> update(Book book, String bid);

    List<Book> findLike(String bname);

    int updateCount(String bname, int count);
}
