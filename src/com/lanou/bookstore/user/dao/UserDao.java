package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domian.User;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserDao {

    User findWithName(String username);

    void addUser(User user);

    User findWithEmail(String email);

    User findByCode(String code);

    void updateState(String uid, boolean state);

    List<User> findAll();

    User findByUid(String uid);

    void update(User user);

    User findByName(String name);
}
