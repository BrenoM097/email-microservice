package com.br.breno.emailmicroservice.adapters;

public interface EmailSenderGateway {
    int sendEmail(String to, String subject, String body);
}
