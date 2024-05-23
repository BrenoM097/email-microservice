package com.br.breno.emailmicroservice.core;

public interface EmailServiceCore {
    void sendEmail(String to, String subject, String bodyContent, String provider);
}
