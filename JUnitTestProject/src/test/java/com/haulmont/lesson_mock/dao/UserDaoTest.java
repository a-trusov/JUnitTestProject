package com.haulmont.lesson_mock.dao;

import com.haulmont.lesson_mock.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDaoTest {

    private UserDao dao;

    @Before
    public void setUp() throws Exception {
        this.dao = new UserDaoImpl();
    }

    @Test
    public void getUserByUsernameShouldReturnTrue() throws Exception {
        User user = dao.getUserByUsername("user");

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("user");
    }

    @Test
    public void getUserByUsernameShouldNullUser() throws Exception {
        User user = dao.getUserByUsername("not_user");
        assertThat(user).isNull();
    }

    @Test
    public void findAllUsersContain() {
        List<User> allUsers = dao.findAllUsers();

        assertThat(allUsers).contains(new User("admin", "ADMIN"));
    }
}