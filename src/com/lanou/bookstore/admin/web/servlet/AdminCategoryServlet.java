package com.lanou.bookstore.admin.web.servlet;

import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.admin.service.AdminException;
import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();

    /**
     * 管理员登录
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 校验输入是否为空
        Admin formAdmin = CommonUtils.toBean(request.getParameterMap(), Admin.class);
        if (formAdmin == null) {
            request.setAttribute("msg", "用户名或密码不能为空");
            return "f:/adminjsps/login.jsp";
        }
        if (formAdmin.getAname().equals("")) {
            request.setAttribute("msg", "用户名不能为空");
            return "f:/adminjsps/login.jsp";
        }
        if (formAdmin.getPassword().equals("")) {
            request.setAttribute("msg", "密码不能为空");
            return "f:/adminjsps/login.jsp";
        }
        // 校验该管理员是否存在
        Admin admin = adminService.findAdmin(formAdmin);
        if (admin == null) {
            request.setAttribute("msg", "您不是管理员, 没有权限");
            return "f:/adminjsps/login.jsp";
        }
        // 保存管理员信息
        request.getSession().setAttribute("admin", admin);
        return "f:/adminjsps/admin/index.jsp";
    }

    /**
     * 查询图书所有分类
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用 service 查询
        List<Category> list = adminService.findAllCategory();
        request.setAttribute("list", list);
        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 封装表单数据
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        // 校验是否为空
        if (category.getCname().equals("")){
            request.setAttribute("msg", "类名不能为空!");
            return "f:/adminjsps/admin/msg.jsp";
        }
        // 添加cid
        category.setCid(CommonUtils.uuid());
        // 调用 service 指行添加
        adminService.addCategroy(category);
        request.setAttribute("msg", "添加成功!");
        return "f:/adminjsps/admin/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取分类
        String cid = request.getParameter("cid");
        // 调用 service 执行删除操作
        try {
            adminService.deleteCategory(cid);
        } catch (AdminException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/adminjsps/admin/msg.jsp";
        }
        // 保存成功信息,
        request.setAttribute("msg", "删除成功!");
        return "f:/adminjsps/admin/msg.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取分类 和 表单参数
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        // 调用 service 执行编辑操作
        adminService.editCategory(cid, cname);

        // 保存成功信息,
        request.setAttribute("msg", "编辑成功!");
        return "f:/adminjsps/admin/msg.jsp";
    }
}