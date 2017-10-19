package com.lanou.bookstore.book.web.servlet;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有图书
        List<Book> list = bookService.findAll();
        request.setAttribute("list", list);
        return "f:/jsps/book/list.jsp";
    }

    /**
     *  按类查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        // 通过类名 cid  查询图书
        List<Book> list = bookService.findByCid(cid);
        request.setAttribute("list", list);
        return "f:/jsps/book/list.jsp";
    }

    /**
     *  加载图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        // 通过书的 id 号查询
        Book book = bookService.findByBid(bid);
        request.setAttribute("book", book);
        return "f:/jsps/book/desc.jsp";
    }

    /**
     *  模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bname = request.getParameter("bname");
        if (bname == null){
            return "f:/jsps/body.jsp";
        }
        List<Book> list = bookService.findLike(bname);
        request.setAttribute("list", list);
        return "f:/jsps/book/list.jsp";
    }
}