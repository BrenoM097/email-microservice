package com.br.breno.emailmicroservice.core;

public record EmailRequest(String to, String subject, String body) {}
