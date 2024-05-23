package com.br.breno.emailmicroservice.infra.sendgrid;

import com.br.breno.emailmicroservice.adapters.EmailSenderGateway;
import com.br.breno.emailmicroservice.core.exceptions.EmailServiceException;
import com.br.breno.emailmicroservice.core.exceptions.EmailServiceIOException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier("sendGridEmailSender")
public class SendgridEmailSender implements EmailSenderGateway {

    @Value("${send.grid.api.key}")
    private final String sendGridApiKey;
    @Value("${email.address}")
    private final String emailAddress;

    @Autowired
    public SendgridEmailSender(@Value("${email.address}") String emailAddress, @Value("${send.grid.api.key}") String sendGridApiKey) {
        this.emailAddress = emailAddress;
        this.sendGridApiKey = sendGridApiKey;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        Email fromEmail = new Email(emailAddress);
        Email toEmail = new Email(to);
        Content content =  new Content("text/plain", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            response.getStatusCode();
        } catch (EmailServiceIOException exception) {
            throw new EmailServiceException("Error while sending email", exception);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void teste() {

    }
}
