package com.haulmont.task;

import com.haulmont.example.order.Order;
import com.haulmont.example.order.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MailServiceTest {

    // тестируемый сервис
    private MailService mailService;

    @Mock
    private MailSender mailSender;
    @Spy
    private MessageRepository messageRepository;
    @Spy
    private Person from;
    @Spy
    private Person to;

    private Message message;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mailService = new MailService(mailSender, messageRepository);
//        Mockito.doAnswer(invocation -> {
//            Person from = invocation.getArgument(0);
//            from.setEmail("from@mail");
//
//            Person to = invocation.getArgument(0);
//            to.setEmail("to@mail");
//
//            return message;
//        }).when(messageRepository).addMessage(any(),any(), anyString(), anyString());
        // не придумал как замокать без прописывания email, через doAnswer не вышло
        from.setEmail("from@mail");
        to.setEmail("to@mail");
        message = messageRepository.addMessage(from, to, "Subject", "Message");
    }

    @Test
    public void sendMessageShouldReturnDelivered() {
        Mockito.when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        boolean result = mailService.sendMessage(message);

        assertTrue(result);
        assertEquals(message.getStatus(), Status.DELIVERED);
    }

    @Test
    public void sendMessageShouldReturnError() {
        Mockito.when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString())).thenReturn(false);
        boolean result = mailService.sendMessage(message);
        assertFalse(result);
        assertEquals(message.getStatus(), Status.ERROR);
    }

    @Test
    public void testMethodCalled() {
        message.setStatus(Status.DELIVERED);
        mailService.sendMessage(message);
        Mockito.verify(mailSender, Mockito.times(1)).sendMessage(anyString(), anyString(), anyString(), anyString());
    }


    //тест падает, не понял почему...
    @Test
    public void testMethodNeverCalled() {
        message.setStatus(Status.NEW);
        mailService.sendMessage(message);
        Mockito.verify(mailSender, Mockito.never()).sendMessage(anyString(), anyString(), anyString(), anyString());
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
