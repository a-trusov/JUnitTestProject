package com.haulmont.lesson_mock.dao;

import com.haulmont.lesson_mock.model.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username) throws Exception;

    List<User> findAllUsers();
}
