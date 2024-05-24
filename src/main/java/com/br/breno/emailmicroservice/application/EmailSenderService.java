package com.br.breno.emailmicroservice.application;

import com.br.breno.emailmicroservice.adapters.EmailSenderGateway;
import com.br.breno.emailmicroservice.core.EmailServiceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailServiceCore {

    private final EmailSenderGateway sesEmailSenderGateway;
    private final EmailSenderGateway sendGridEmailSenderGateway;

    @Autowired

    public EmailSenderService(@Qualifier("sesEmaiSender") EmailSenderGateway emailServiceGateway, @Qualifier("sendGridEmailSender") EmailSenderGateway sendGridEmailSenderGateway) {
        this.sesEmailSenderGateway = emailServiceGateway;
        this.sendGridEmailSenderGateway = sendGridEmailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String bodyContent, String provider) {
        if(provider.equalsIgnoreCase("ses")) {
            this.sesEmailSenderGateway.sendEmail(to, subject, bodyContent);
        } else if(provider.equalsIgnoreCase("sendgrid")) {
            this.sendGridEmailSenderGateway.sendEmail(to, subject, bodyContent);
        }
    }
}
