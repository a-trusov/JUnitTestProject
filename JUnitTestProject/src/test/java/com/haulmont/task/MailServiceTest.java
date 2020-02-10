package com.haulmont.task;

import com.haulmont.example.order.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {

    @Mock
    private MailSender mailSender;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MailService mailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mailService = new MailService(mailSender, messageRepository);
    }

    @Test
    public void sendMessageShouldReturnTrue() {
        Message message = new Message(anyString(), any(), any(), anyString());
        Mockito.when(mailService.sendMessage(message)).thenReturn(true);
        boolean exists = mailService.sendMessage(message);
        assertTrue(exists);
        assertEquals(message.getStatus(), Status.DELIVERED);
    }

//    @Test
//    public void sendMessageShouldReturnFalse() {
//    }
//
//    @Test
//    public void removeMessage() {
//    }
}

//Необходимо покрыть тестами класс com.haulmont.task.MailService

//Требования:
// Обычный тест,
// мок возвращаемого значения,
// проверка вызвался ли метод,
// с правильными ли параметрами вызван метод. Adv - мок поведения

//Подсказки:
//Правильно ли подставились аргументы в mailSender.sendMessage(...)
//Правильно ли сменился статус
//статус при другом ответе от сервера
//обратились ли к MailSender
//Замокать поведение репозитория, проверить поменялся ли статус
