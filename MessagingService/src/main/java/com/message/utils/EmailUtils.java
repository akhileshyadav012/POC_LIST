package com.message.utils;

import com.message.constant.MessageConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailUtils {
    private static final Logger logger = LogManager.getLogger(EmailUtils.class);
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String toEmail, String subject, String body){
        logger.info("EmailUtils - Inside sendEmail method");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);

        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        return MessageConstant.MAIL_SEND_SUCCESSFULLY;

    }
}
