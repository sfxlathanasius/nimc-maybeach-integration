package com.seamfix.nimc.maybeach.exceptions;

public class DeviceAuthException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110013455785435020L;

	public DeviceAuthException(){
		super("Unrecognized Device!");
	}
	
	public DeviceAuthException(String message){
		super(message);
	}

}
