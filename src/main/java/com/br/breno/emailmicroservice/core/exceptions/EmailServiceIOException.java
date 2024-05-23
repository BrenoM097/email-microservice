package com.br.breno.emailmicroservice.core.exceptions;

import java.io.IOException;

public class EmailServiceIOException extends IOException {
    public EmailServiceIOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
