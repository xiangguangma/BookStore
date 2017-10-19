package com.lanou.bookstore.user.service.impl;

import com.lanou.bookstore.admin.dao.AdminDao;
import com.lanou.bookstore.admin.dao.impl.AdminDaoImpl;
import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domian.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public void regist(User formUser) throws UserException {
        // 通过用户名查找
        User dbUserName = userDao.findWithName(formUser.getUsername());
        if (dbUserName != null) {
            throw new UserException("用户: " + formUser.getUsername() + "已被注册");
        }
        // 通过邮箱查找
        User dbUserEmail = userDao.findWithEmail(formUser.getEmail());
        if (dbUserEmail != null) {
            throw new UserException("邮箱: " + formUser.getEmail() + "已被注册");
        }

        // 不存在, 存入数据库
        userDao.addUser(formUser);

    }

    @Override
    public void active(String code) throws UserException {
        User dbUser = userDao.findByCode(code);
        if (dbUser == null) {
            throw new UserException("用户不存在");
        } else if (dbUser.isState()) {
            throw new UserException("你是怎么激活的?");
        }
        userDao.updateState(dbUser.getUid(), dbUser.isState());
    }

    @Override
    public User login(User formUser) throws UserException {
        // 通过username查询
        User user = userDao.findWithName(formUser.getUsername());
        // 为空抛异常
        if (user == null) {
            throw new UserException("用户不存在");
        }
        // 比较密码是否相等
        if (!formUser.getPassword().equals(user.getPassword())) {
            throw new UserException("密码错误");
        }
        // 判断激活状态
        if (!user.isState()) {
            throw new UserException("你是死的");
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            List<Order> orders = adminService.findOrderByUid(user.getUid());
            user.setOrders(orders);
        }
        return users;
    }

    @Override
    public List<User> findAll(int i) {
        List<User> users = userDao.findAll();
        for (User user : users) {
            List<Order> orders = adminService.findOrderByUidAndState(user.getUid(), i);
            user.setOrders(orders);
        }
        return users;
    }

    @Override
    public User findByUid(String uid) {
        return userDao.findByUid(uid);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findFinished() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            List<Order> orders = adminService.findOrderFinished(user.getUid());
            user.setOrders(orders);
        }
        return users;
    }


}
