package com.lanou.bookstore.user.web.filter;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domian.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 17/9/29.
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String email = request.getParameter("email");
        User user = userDao.findWithEmail(email);
        System.out.println(user);

        JSONObject jsonObject = JSONObject.fromObject(user);
        System.out.println(jsonObject.toString());
        response.getWriter().print(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}