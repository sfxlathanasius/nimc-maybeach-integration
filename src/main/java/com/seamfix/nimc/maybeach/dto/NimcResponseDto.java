package com.seamfix.nimc.maybeach.dto;

public class NimcResponseDto {
	
	public static final int SUCCESS_CODE = 200;
	
	public static final int ERROR_CODE = 500;
	
	public static final int INVALID_PARAM_CODE = 400;
	
	public static final int RETURN_OBJECT_ERROR_CODE = 503;
	
	public static final int CLIENT_SUCCESS_RESPONSE_CODE = 0;

	public static final String SUCCESS_STR = "Success";
	
	public static final String ERROR_STR = "Error processing request";
	
	public static final String INVALID_CREDENTIALS = "Invalid credentials";
	
	public static final String INVALID_ENCRYPTION = "Invalid encryption";
	
	public static final String RETURN_OBJECT_ERROR_STR = "Error getting response message from unavailable returned object";
	
	public static final String DETAILS_NOT_FOUND_STR = "User details not found";
	
	private int status = 500;
	
	private String message = "Error processing request";
	
	private Object data;
	
	public NimcResponseDto() {
		
	}
	
	public NimcResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public NimcResponseDto(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
