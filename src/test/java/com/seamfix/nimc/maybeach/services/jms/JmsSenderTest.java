package com.seamfix.nimc.maybeach.services.jms;

import com.seamfix.nimc.maybeach.configs.AppConfig;
import com.seamfix.nimc.maybeach.dto.CbsPaymentRequest;
import com.seamfix.nimc.maybeach.dto.MayBeachResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JmsSenderTest {

    @InjectMocks
    private JmsSender underTest;

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private AppConfig appConfig;

    @BeforeEach
    void setUp() {
        underTest.init();
    }

    @Test
    void callPayloadBackup_shouldReturnTrueWhenPayloadBackupIsDisabled() {
        String id = "id";
        String requestType = "NIN-HASH";
        String requestPath = "request-path";
        Date requestTime = new Date();
        CbsPaymentRequest request = new CbsPaymentRequest();
        MayBeachResponse response = new MayBeachResponse();

        boolean calledPayloadBackup = underTest.callPayloadBackup(id, requestType, requestTime, requestTime, requestPath, request, response);
        assertTrue(calledPayloadBackup);
        verify(appConfig, atLeastOnce()).isEnablePayload();
    }

    @Test
    void callPayloadBackup() {
        String id = "id";
        String requestType = "NIN-HASH";
        String requestPath = "request-path";
        Date requestTime = new Date();
        CbsPaymentRequest request = new CbsPaymentRequest();
        MayBeachResponse response = new MayBeachResponse();

        when(appConfig.isEnablePayload()).thenReturn(true);

        boolean calledPayloadBackup = underTest.callPayloadBackup(id, requestType, requestTime, requestTime, requestPath, request, response);
        assertTrue(calledPayloadBackup);
        verify(jmsTemplate, atLeastOnce()).convertAndSend(anyString(), anyMap());
    }
}