package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CbsEnrollmentNotificationRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 367431051987363472L;
	private String enrollmentType;
	private double currentLocationLongitude;
	private double currentLocationLatitude;
	private String trackingId;
	private String enrollmentTime;
	private boolean preenrollmentCheckDone;
	private String enrollerLoginId;
	private String enrollerName;
	private String timeSentToBackend;
	private String nodeId;
	private String originatingCenterCode;
	private String nodeMachineTag;
	private List<String> enrollmentSubType;
	private List<String> paymentReferences;
	private String deviceId;
	private String esaCode;
	private String originatingCenterName;
}
