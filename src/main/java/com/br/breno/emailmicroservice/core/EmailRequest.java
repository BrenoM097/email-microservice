package com.br.breno.emailmicroservice.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Email request model")
public record EmailRequest(String to, String subject, String body) {}
