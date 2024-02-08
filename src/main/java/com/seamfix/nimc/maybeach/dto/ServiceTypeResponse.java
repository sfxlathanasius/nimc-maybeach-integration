package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;

public class ServiceTypeResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2972447968637188012L;

	private String name;
	
	private String code;
	
	private String amountInMinorUnit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAmountInMinorUnit() {
		return amountInMinorUnit;
	}

	public void setAmountInMinorUnit(String amountInMinorUnit) {
		this.amountInMinorUnit = amountInMinorUnit;
	}
	
	

}
