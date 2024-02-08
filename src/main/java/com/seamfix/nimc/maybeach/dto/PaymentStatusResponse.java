package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentStatusResponse extends MayBeachResponse {
    private static final long serialVersionUID = -8066650322961285501L;
    private PaymentStatusData data;
    private List<String> paidServiceTypes;
    private List<String> missingServiceTypes;
    private List<String> unpaidServiceTypes;
}
