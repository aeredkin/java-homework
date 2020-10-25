package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    public void send(String host, String to, String text) throws MessagingException {
        // we're going to use google mail to send this message
        javaMailSender.setHost(host);
        // construct the message
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        // setting message text, last parameter 'true' says that it is HTML format
        helper.setText(text, true);
        helper.setSubject("Monthly department salary report");
        // send the message
        javaMailSender.send(message);
    }
}
