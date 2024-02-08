package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentStatusData implements Serializable {
    private static final long serialVersionUID = 7249447918847005936L;
    private String paymentProvider;

    private String consumptionStatus;

    private String paymentReference;

    private ServiceType[] serviceTypes;

    private String currencyCode;

    private String consumedBy;
}
