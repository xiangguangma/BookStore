package com.lanou.bookstore.admin.service;

import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.order.domain.Order;

import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public interface AdminService {

    Admin findAdmin(Admin formAdmin);

    List<Category> findAllCategory();
    void addCategroy(Category category);
    void deleteCategory(String cid) throws AdminException;
    void editCategory(String cid, String cname);


    List<Book> findAllBook();
    Book findBookByBid(String bid);
    List<Book> deleteBookByBid(String bid, boolean b);
    List<Book> updateBook(Book book, String bid);
    void addBook(Book book);


    List<Order> findAllOrder();
    void consignment(String oid);
    void confirmAdmin(String oid);

    List<Order> findOrderByUid(String uid);

    List<Order> findOrderByUidAndState(String uid, int i);

    List<Order> findOrderFinished(String uid);

    List<Book> findBookByDel(boolean b);
}
