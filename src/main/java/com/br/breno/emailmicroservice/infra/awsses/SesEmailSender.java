package com.br.breno.emailmicroservice.infra.awsses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.br.breno.emailmicroservice.adapters.EmailSenderGateway;
import com.br.breno.emailmicroservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final String FromEmailAddress;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService, @Value("${email.address}") String emailAddress) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
        this.FromEmailAddress = emailAddress;
    }
    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withSource(FromEmailAddress)
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );
        try {
            this.amazonSimpleEmailService.sendEmail(sendEmailRequest);
        } catch (AmazonSimpleEmailServiceException exception) {
            throw new EmailServiceException("Error while sending email", exception);
        }
    }
}
