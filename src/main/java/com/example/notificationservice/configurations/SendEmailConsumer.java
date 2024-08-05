package com.example.notificationservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Properties;

import javax.mail.Session;
@KafkaListener(topics = "sendEmail", groupId = "emailService")
public class EmailService{
    private ObjectMapper objectMapper;
    private KafkaTemplate<String, String> kafkaTmeplate;
    private EmailUtil emailUtil;

    public EmailService(EmailUtil emailUtil,
                       KafkaTemplate<String, String> kafkaTemplate,
                       ObjectMapper objectMapper){
        this.emailUtil = emailUtil;
        this.kafkaTmeplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void handleSendEmailMessage() {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "smtp.example.com";
        String emailID = "email_me@example.com";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        emailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
    }

}
