package com.lanou.bookstore.admin.web.servlet;

import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.bookstore.user.domian.User;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/25.
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();
    private UserService userService = new UserServiceImpl();
    /**
     * 查看所有订单
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有用户(封装了 order信息 )
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    /**
     * 发货
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String consignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        adminService.consignment(oid);
        // 调用 findAll
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        // 调用 service 方法修改 state 为 4
        adminService.confirmAdmin(oid);
        // 调用 findall
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String noPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有用户(封装了 order信息 )
        List<User> users = userService.findAll(0);
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    public String payed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有用户(封装了 order信息 )
        List<User> users = userService.findAll(1);
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    public String noTake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有用户(封装了 order信息 )
        List<User> users = userService.findAll(2);
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    public String finish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有用户(封装了 order信息 )
        List<User> users = userService.findFinished();
        request.setAttribute("users", users);
        return "f:/adminjsps/admin/order/list.jsp";
    }

}