package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.domian.User;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserService {

    void regist(User formUser) throws UserException;

    void active(String code) throws UserException;

    User login(User formUser) throws UserException;

    List<User> findAll();
    List<User> findAll(int i);

    User findByUid(String uid);

    void update(User user);

    List<User> findFinished();

}
