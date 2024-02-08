package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;
import java.util.List;

public class CbsResponseData extends CbsResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4352870285915457487L;
	//Format: yyyy-MM-dd'T'HH:mm:ss.SSS' Z'
	private String dateConsumed;
	private List<String> serviceTypes;
	private String consumedBy;
	private String trackingId;
	
	public String getDateConsumed() {
		return dateConsumed;
	}
	
	public void setDateConsumed(String dateConsumed) {
		this.dateConsumed = dateConsumed;
	}
	public List<String> getServiceTypes() {
		return serviceTypes;
	}
	public void setServiceTypes(List<String> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
	public String getConsumedBy() {
		return consumedBy;
	}
	public void setConsumedBy(String consumedBy) {
		this.consumedBy = consumedBy;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	

}
