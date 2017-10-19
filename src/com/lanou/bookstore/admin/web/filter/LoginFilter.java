package com.lanou.bookstore.admin.web.filter;

import com.lanou.bookstore.admin.domain.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dllo on 17/9/25.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/adminjsps/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if (admin == null) {
            request.setAttribute("msg", "请先登录,  ^_^");
            request.getRequestDispatcher("/adminjsps/login.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
