package com.haulmont.task;

public class MailService {
    private MailSender mailSender;
    private MessageRepository messageRepository;

    public MailService(MailSender mailSender, MessageRepository messageRepository) {
        this.mailSender = mailSender;
        this.messageRepository = messageRepository;
    }

    public boolean sendMessage(Message message) {
        boolean success = mailSender.sendMessage(message.getFrom().getEmail(),
                message.getTo().getEmail(), message.getSubject(), message.getText());
        if (success) {
            message.setStatus(Status.DELIVERED);
            return true;
        }
        message.setStatus(Status.ERROR);
        return false;
    }

    public void removeMessage(Message message) {
        messageRepository.removeMessage(message);
        //This method removes message from database and sets status of message to REMOVED.
        // Mock this behavior(without DB of course)
    }

}
