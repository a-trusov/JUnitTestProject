package com.haulmont.lesson_mock.services;

import com.haulmont.lesson_mock.dao.UserDao;
import com.haulmont.lesson_mock.model.User;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkUserPresence(User user) throws Exception {
        User u = userDao.getUserByUsername(user.getUsername());

        return u != null;
    }
}
