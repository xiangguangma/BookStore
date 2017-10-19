package com.lanou.bookstore.admin.dao;

import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.order.domain.Order;

import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public interface AdminDao {

    Admin findAdmin(Admin formAdmin);

    List<Category> findAllCategory();
    void addCategory(Category category);
    void deleteCategory(String cid);
    void editCategory(String cid, String cname);


    List<Book> findAllBook(boolean b);
    Book findBookByBid(String bid);
    void updateBookState(String bid, boolean b);
    void deleteBook(String bid);
    void updateBook(Book book, String bid);
    void addBook(Book book);



    List<Order> findAllOrder();
    void updateOrderState(String oid, int i);

    List<Order> findAllOrdersByUid(String uid);

    List<Order> findAllOrdersByUidAndState(String uid, int i);

    List<Order> findAllOrdersFinished(String uid);


}
