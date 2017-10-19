package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.user.domian.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.lanou.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        // 1,封装表单
        User formUser = CommonUtils.toBean(request.getParameterMap(), User.class);
        // 2. 补全UUId. state
        formUser.setUid(CommonUtils.uuid());
        String code = CommonUtils.uuid();
        formUser.setCode(code);

        request.getSession().setAttribute("formUser", formUser);


        // 3. 验证
        Map<String, String> map = new HashMap<>();
        if (formUser.getUsername().equals("")) {
            map.put("username", "用户名不能为空");
        } else if (formUser.getUsername().trim().length() < 2 || formUser.getUsername().trim().length() > 15) {
            map.put("username", "用户名的长度必须在 2 ~ 15 之间");
        }
        if (formUser.getPassword().equals("")) {
            map.put("password", "密码不能为空");
        } else if (formUser.getPassword().trim().length() < 2 || formUser.getUsername().trim().length() > 15) {
            map.put("password", "密码的长度必须在 2 ~ 15 之间");
        }
        if (formUser.getEmail().equals("")){
            map.put("email", "邮箱不能为空");
        }else if (!formUser.getEmail().matches("\\w+@[\\w\\d]+\\.\\w+")) {
            map.put("email", "邮箱格式错误");
        }

        if (formUser.getUsername().trim().length() < 2 || formUser.getUsername().trim().length() > 15 ||
                formUser.getPassword().trim().length() < 2 || formUser.getPassword().trim().length() > 15 ||
                !formUser.getEmail().matches("\\w+@[\\w\\d]+\\.\\w+")) {
            request.setAttribute("map", map);
            return "f:/jsps/user/regist.jsp";
        }

        // 4. 调用 service #regist()
        try {
            userService.regist(formUser);

        } catch (UserException e) {
            // 保存错误信息
            request.setAttribute("msg", e.getMessage());
            // 用于表单回显
            request.setAttribute("user", formUser);
            return "f:/jsps/user/regist.jsp";

        }


        // 5. 发邮件
        sendMail(request, formUser);

        // 6. 保存成功信息
        request.setAttribute("msg", "注册成功!");

        // 7. 转发到 msg.jsp
        return "f:/jsps/msg.jsp";
    }

    private void sendMail(HttpServletRequest request, User formUser) throws IOException, MessagingException {
        // 获得 session
        Session session = MailUtils.createSession("smtp.163.com", "xinrugao@163.com", "why1993521");
        // 创建邮件对象
        Mail mail = new Mail("xinrugao@163.com", formUser.getEmail(), "这是来自红缨网上图书城的激活邮件",
                "<a href='http://localhost:8080/bookstore/UserServlet?method=active&code=" + formUser.getCode() + "'>点击激活</a>" +
                        "或输入 http://localhost:8080/bookstore/UserServlet?method=active&code=" + formUser.getCode());
        // 发送
        MailUtils.send(session, mail);
        request.setAttribute("msg", "邮件发送成功");
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        // 获取验证码 code
        String code = (String) request.getParameter("code");
        try {
            // 使用 code 查询
            userService.active(code);
        } catch (UserException e) {
            // 存储异常信息
            request.setAttribute("msg", e.getMessage());
            return "f:/jsps/msg.jsp";
        }
        request.setAttribute("msg", "激活成功");
        return "f:/jsps/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 封装表单信息
        User formUser = CommonUtils.toBean(request.getParameterMap(), User.class);
        // 校验
        Map<String, String> map = new HashMap<>();
        if (formUser.getUsername().equals("")) {
            map.put("username", "用户名不能为空");
        }
        if (formUser.getPassword().equals("")) {
            map.put("password", "密码不能为空");
        }
        if (formUser.getPassword().equals("")|| formUser.getUsername().equals("")){
            request.setAttribute("map", map);
            return "f:/jsps/user/login.jsp";
        }

        // 调用 service方法完成登录
        try {
            User user = userService.login(formUser);

            // 保存用户信息
            request.getSession().setAttribute("formUser", user);
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("formUser",formUser);
            return "f:/jsps/user/login.jsp";
        }


        // 添加一辆购物车
        Cart cart = new Cart();
        cart.setCartItems(new HashMap<>());
        request.getSession().setAttribute("cart", cart);

        // 重定向到 index.jsp
        return "r:/index.jsp";
    }

    public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        request.getSession().removeAttribute("formUser");
        return "r:/jsps/user/login.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        // 获取user 对象
        User formUser = (User) request.getSession().getAttribute("formUser");
        // 设置参数
        user.setUid(formUser.getUid());
        user.setCode(formUser.getCode());
        user.setState(formUser.isState());
        user.setEmail(formUser.getEmail());
        // 修改
        userService.update(user);
        request.getSession().setAttribute("formUser", user);
        return "r:/jsps/user/list.jsp";
    }
}