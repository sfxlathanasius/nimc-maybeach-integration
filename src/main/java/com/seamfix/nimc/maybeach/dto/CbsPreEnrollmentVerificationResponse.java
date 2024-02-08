/**
 * 
 */
package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CbsPreEnrollmentVerificationResponse extends MayBeachResponse {

	private static final long serialVersionUID = 5088453035314880845L;

	private boolean verified;
	private List<CbsPreEnrollmentCheckRequest> results;

	public CbsPreEnrollmentVerificationResponse() {
	}

	public CbsPreEnrollmentVerificationResponse(int code, String message) {
		super(code, message);
	}
}
