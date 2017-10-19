package com.lanou.bookstore.admin.web.servlet;

import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dllo on 17/9/25.
 */
@WebServlet("/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        Book book = new Book();
        
        // 创建工厂
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        // 通过工厂创建解析器
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        try {
            // 解析Request
            List<FileItem> fileItems = sfu.parseRequest(request);
            // 获取 bname , 给 book 赋值 (转码)
            String bname = new String(fileItems.get(0).getString().getBytes("iso-8859-1"), "utf-8");
            book.setBname(bname);
            // 获取 文件表单
            FileItem fi2 = fileItems.get(1);

            //文件保存到 book_img 中
            String root = this.getServletContext().getRealPath("/book_img/");

            // 获取上传文件名字, 并加 uuid 防止重名
            String imgName = CommonUtils.uuid() +"_"+ fi2.getName();
            // 保存文件
            File destFile = new File(root + imgName);
            fi2.write(destFile);
            // 将文件路径赋给 book 的 image 属性
            book.setImage("book_img/"+ imgName);

            // 获取 price
            String price = fileItems.get(2).getString();
            book.setPrice(Float.parseFloat(price));

            // 获取 author
            String author = new String(fileItems.get(3).getString().getBytes("iso-8859-1"), "utf-8");
            book.setAuthor(author);

            // 获取 cid
            String cid = fileItems.get(5).getString();
            book.setCid(cid);

            // 数量
            String count = fileItems.get(4).getString();
            book.setCount(Integer.parseInt(count));

            // 添加bid
            book.setBid(CommonUtils.uuid());

            System.out.println(book);

            // 添加加到数据库
            adminService.addBook(book);
            // 调用 findAllBook 方法
            List<Book> books = adminService.findAllBook();
            request.setAttribute("books", books);
            request.setAttribute("msg", "添加成功");
            request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}