package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CbsPaymentRequest implements Serializable {

	private static final long serialVersionUID = 6010706844001662680L;
	private String dateConsumed;
	private String serviceType;
	private String customerFullName;
	private String trackingId;
	private String deviceId;
	private Map<String, String> mappedHeaders = new ConcurrentHashMap<>();
	
	
	
	public String getDateConsumed() {
		return dateConsumed;
	}
	
	public void setDateConsumed(String dateConsumed) {
		this.dateConsumed = dateConsumed;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getCustomerFullName() {
		return customerFullName;
	}
	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Map<String, String> getMappedHeaders() {
		return mappedHeaders;
	}

	public void setMappedHeaders(Map<String, String> mappedHeaders) {
		this.mappedHeaders = mappedHeaders;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
