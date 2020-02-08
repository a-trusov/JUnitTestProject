package com.haulmont.lesson_mock.dao;

import com.haulmont.lesson_mock.model.User;

import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private List<User> users;

    public UserDaoImpl() {
        users = Arrays.asList(
                new User("guest", "GUEST"),
                new User("user", "USER"),
                new User("admin", "ADMIN")
        );
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return users.stream().
                filter(user -> user.getUsername().equals(username)).
                findAny().
                orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
