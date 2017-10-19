package com.lanou.bookstore.user.dao.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domian.User;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner qr = new GxQueryRunner();
    @Override
    public User findWithName(String username) {
        String sql = "select * from tb_user where username=?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into tb_user values(?,?,?,?,?,?)";
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getCode(),user.isState()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findWithEmail(String email) {
        String sql = "select * from tb_user where email=?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByCode(String code) {
        String sql = "select * from tb_user where code=?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String uid, boolean state) {
        String sql = "update tb_user set state=? where uid=?";
        Object[] params = {!state, uid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from tb_user";
        try {
            return qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUid(String uid) {
        String sql = "select * from tb_user where uid=?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "update tb_user set username=?, password=?, email=? where uid=?";
        Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getUid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByName(String name) {
        String sql = "select * from tb_user where username=?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class), name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
