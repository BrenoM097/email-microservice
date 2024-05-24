//package com.br.breno.emailmicroservice.infra.awsses;
//
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
//import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
//import com.amazonaws.services.simpleemail.model.SendEmailRequest;
//import com.amazonaws.services.simpleemail.model.SendEmailResult;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Value;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class SesEmailSenderTest {
//    @Mock
//    private AmazonSimpleEmailService amazonSimpleEmailService;
//
//    @Value("${email.address}")
//    private String fromEmailAddress = "melobreno89@gmail.com";
//
//    private final String toEmailAddress = "ajudaromlk@gmail.com";
//
//    @InjectMocks
//    private SesEmailSender sesEmailSender;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSendEmail_Success() {
//        SendEmailResult sendEmailResult = new SendEmailResult();
//        sendEmailResult.setMessageId("test-message");
//
//        when(amazonSimpleEmailService.sendEmail(any(SendEmailRequest.class))).thenReturn(sendEmailResult);
//
//        int statusCode = sesEmailSender.sendEmail(toEmailAddress, "Test Subject", "Test Body");
//
//        assertEquals(200, statusCode);
//        verify(amazonSimpleEmailService, times(1)).sendEmail(any(SendEmailRequest.class));
//    }
//
//    @Test
//    void testSendEmail_Failure() {
//        when(amazonSimpleEmailService.sendEmail(any(SendEmailRequest.class))).thenThrow(new AmazonSimpleEmailServiceException("Sending failed"));
//
//        int statusCode = sesEmailSender.sendEmail(toEmailAddress, "Test Subject", "Test Body");
//
//        assertEquals(500, statusCode);
//        verify(amazonSimpleEmailService, times(1)).sendEmail(any(SendEmailRequest.class));
//    }
//}