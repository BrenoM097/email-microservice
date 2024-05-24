package com.br.breno.emailmicroservice.infra.sendgrid;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SendgridEmailSenderTest {
    @Mock
    private SendGrid sendGridClient;
    @Value("${email.address}")
    private String fromEmailAddress;
    @Value("${send.grid.api.key}")
    private String sendGridApiKey;
    @InjectMocks
    private SendgridEmailSender sendgridEmailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sendgridEmailSender = new SendgridEmailSender(fromEmailAddress, sendGridApiKey);
        ReflectionTestUtils.setField(sendgridEmailSender, "sendGridClient", sendGridClient);
    }

    @Test
    void testSendEmail_Success() throws IOException {
        Response response = new Response();
        response.setStatusCode(202);

        when(sendGridClient.api(any(Request.class))).thenReturn(response);

        int statusCode = sendgridEmailSender.sendEmail("ajudaromlk@gmail.com", "test subject", "test body");

        assertEquals(202, statusCode);
        verify(sendGridClient, times(1)).api(any(Request.class));
    }

    @Test
    void testSendEmail_Failure() throws IOException {
        when(sendGridClient.api(any(Request.class))).thenThrow(new RuntimeException("Sending failed"));

        assertThrows(RuntimeException.class, () -> sendgridEmailSender.sendEmail("ajudaromlk@gmail.com", "", ""));
        verify(sendGridClient, times(1)).api(any(Request.class));
    }
}