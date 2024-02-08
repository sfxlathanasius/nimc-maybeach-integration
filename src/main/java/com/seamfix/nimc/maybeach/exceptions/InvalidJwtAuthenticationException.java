package com.seamfix.nimc.maybeach.exceptions;


public class InvalidJwtAuthenticationException extends Exception {

	private static final long serialVersionUID = -5806567554125000805L;

	public InvalidJwtAuthenticationException () {
		super();
	}

	public InvalidJwtAuthenticationException(String message) {
        super(message);
	}
}