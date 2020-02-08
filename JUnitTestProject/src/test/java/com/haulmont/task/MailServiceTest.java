package com.haulmont.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class MailServiceTest {

    @Mock
    private MailSender mailSender;
    @Mock
    private MessageRepository messageRepository;

    private MailService mailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mailService = new MailService(mailSender, messageRepository);
    }

    @Test
    public void sendMessageShouldReturnTrue() {
        given(mailSender.sendMessage()).willReturn(
                new Message("from", "to", "subject", "text")
        );

        boolean exists = mailService.sendMessage(message);
        assertTrue("Message 123 :is", exists);

    }

    @Test
    public void sendMessageShouldReturnFalse() {
    }

    @Test
    public void removeMessage() {
    }
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
