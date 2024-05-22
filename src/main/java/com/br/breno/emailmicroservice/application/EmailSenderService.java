package com.br.breno.emailmicroservice.application;

import com.br.breno.emailmicroservice.adapters.EmailSenderGateway;
import com.br.breno.emailmicroservice.core.EmailServiceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailServiceCore {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailServiceGateway) {
        this.emailSenderGateway = emailServiceGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String bodyContent) {
        this.emailSenderGateway.sendEmail(to, subject, bodyContent);
    }
}
