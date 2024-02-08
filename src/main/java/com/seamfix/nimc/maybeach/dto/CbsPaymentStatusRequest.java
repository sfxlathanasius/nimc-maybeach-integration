package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbsPaymentStatusRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6471048543157193189L;

	private String esaCode;
	
	private String paymentReference;
	
	private String xaccountId;
	
	private String signature;

	private String deviceId;

	private List<String> serviceTypes;

	private List<String> paymentReferences;

}
