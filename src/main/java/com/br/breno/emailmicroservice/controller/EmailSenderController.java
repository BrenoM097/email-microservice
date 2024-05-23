package com.br.breno.emailmicroservice.controller;

import com.br.breno.emailmicroservice.application.EmailSenderService;
import com.br.breno.emailmicroservice.core.EmailRequest;
import com.br.breno.emailmicroservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("send")
    public ResponseEntity<String> emailSenderSes(@RequestBody EmailRequest request, @RequestParam String provider) {
        try {
            emailSenderService.sendEmail(request.to(), request.subject(), request.body(), provider);
            return ResponseEntity.ok("Email sent successfully");
        } catch (EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");
        }
    }
}
