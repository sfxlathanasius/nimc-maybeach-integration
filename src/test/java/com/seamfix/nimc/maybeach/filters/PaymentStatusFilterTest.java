package com.seamfix.nimc.maybeach.filters;

import com.seamfix.nimc.maybeach.configs.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentStatusFilterTest {

    @InjectMocks
    private PaymentStatusFilter underTest;

    @Mock
    private AppConfig appConfig;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterChain filterChain;

    @Test
    void doFilterInternal_shouldReturnTrueWhenSkipHeaderAuthenticationIsOn() throws IOException, ServletException {
        when(appConfig.isSkipAuthenticationHeaders())
                .thenReturn(true);

        underTest.doFilterInternal(servletRequest, servletResponse, filterChain);

        verify(appConfig, atLeastOnce()).isSkipAuthenticationHeaders();
    }

    @Test
    void doFilterInternal_shouldReturnFalseWhenAuthResponseIsNotAuthorized() throws IOException, ServletException {
        underTest.doFilterInternal(servletRequest, servletResponse, filterChain);
        verify(appConfig, atLeastOnce()).isSkipAuthenticationHeaders();
    }
}