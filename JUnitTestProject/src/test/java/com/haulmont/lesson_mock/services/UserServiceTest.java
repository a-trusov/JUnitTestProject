package com.haulmont.lesson_mock.services;

import com.haulmont.lesson_mock.dao.UserDao;
import com.haulmont.lesson_mock.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserDao dao;

    private UserService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.service = new UserService(dao);
    }

    @Test
    public void checkUserPresenceShouldReturnTrue() throws Exception {
        given(dao.getUserByUsername("user"))
                .willReturn(new User("user"));

        boolean userExists = service.checkUserPresence(new User("user"));

        assertThat(userExists).isTrue();

        //verify - позволяет опрделить выполнился ли какой-то метод
        verify(dao).getUserByUsername("user");
    }

    @Test
    public void checkUserPresenceShouldReturnFalse() throws Exception {
        given(dao.getUserByUsername("user"))
                .willReturn(null);

        boolean userExists = service.checkUserPresence(new User("user"));

        assertThat(userExists).isFalse();

    }

    @Test(expected = Exception.class)
    public void checkUserPresenceShouldThrowException() throws Exception {
        given(dao.getUserByUsername(anyString())).willThrow(Exception.class);

        boolean userExists = service.checkUserPresence(new User("user"));

    }

    @Test
    public void testCaptor() throws Exception {
        given(dao.getUserByUsername(anyString())).willReturn(
                new User("user")
        );

        boolean b = service.checkUserPresence(new User("user"));

        // отслеживание параметра
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(dao).getUserByUsername(captor.capture());

        String argument = captor.getValue();

        assertThat(argument).isEqualTo("user");
    }
}
