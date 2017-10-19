package com.lanou.bookstore.user.web.filter;

import com.lanou.bookstore.user.domian.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dllo on 17/9/25.
 */
@WebFilter(filterName = "UserFilter", urlPatterns = "/jsps/order/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        User user = (User) request.getSession().getAttribute("formUser");
        if (user == null){
            request.setAttribute("msg", "没登录, 别瞎溜达!");
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
