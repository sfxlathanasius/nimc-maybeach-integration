package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;

public class CbsResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898374484684010171L;
	private int status = -1;
	private String message = "Error processing MAYBEACH request";
	private int code = -1;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public CbsResponse() {
	}
	
	public CbsResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
