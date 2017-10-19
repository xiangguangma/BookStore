package com.lanou.bookstore.admin.web.servlet;

import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CatrgoryServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/24.
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有的图书
        List<Book> books = adminService.findAllBook();
        // 保存到 Request中
        request.setAttribute("books", books);

        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 bid
        String bid = request.getParameter("bid");
        //  通过 bid 获取 book
        Book book = adminService.findBookByBid(bid);
        // 获取全部分类
        List<Category> categories = adminService.findAllCategory();
        // 保存转发
        request.setAttribute("book", book);
        request.setAttribute("categoris", categories);
        return "f:/adminjsps/admin/book/desc.jsp";

    }

    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有的类, 保存并转发
        List<Category> categories = adminService.findAllCategory();
        request.setAttribute("categoris", categories);
        return "f:/adminjsps/admin/book/add.jsp";

    }

    public String del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 bid
        String bid = request.getParameter("bid");
        Book book = adminService.findBookByBid(bid);
        // 调用 service  方法
        List<Book> books = adminService.deleteBookByBid(bid, book.isDel());
        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/list.jsp";

    }

    public String mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将表单内容封装到 book 中
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        // 获取 bid
        String bid = request.getParameter("bid");
        List<Book> books = adminService.updateBook(book, bid);
        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String findDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用 service  方法
        List<Book> books = adminService.findBookByDel(true);
        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/list.jsp";

    }
}